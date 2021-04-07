package com.myim.server.message.handler;

import com.google.gson.Gson;
import com.myim.server.common.DefaultFuture;
import com.myim.common.constant.Constant;
import com.myim.common.enumm.CodeMsgEnum;
import com.myim.common.exception.BaseException;
import com.myim.common.exception.system.PushMessageException;
import com.myim.server.handler.CIMRequestHandler;
import com.myim.server.message.MsgServiceBeanFactory;
import com.myim.server.message.push.CIMMessagePusher;
import com.myim.server.message.service.session.CIMSessionService;
import com.myim.server.model.CIMSession;
import com.myim.server.model.Message;
import com.myim.server.model.ReplyBody;
import com.myim.server.model.SentBody;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.List;


/*
 * 客户端推送消息
 */

@Component
public class ClientPushHandler implements CIMRequestHandler {

    private final Logger logger = LoggerFactory.getLogger(ClientPushHandler.class);

    @Resource
    private CIMSessionService cimSessionService;

    @Resource
    private CIMMessagePusher defaultMessagePusher;

    @Override
    public void process(CIMSession newSession, SentBody body) {
        String action = body.get("action");

        if (action.equals("reply")) {
            //处理等待事件
            doReply(newSession, body);
        } else {
            doRequest(newSession, body, action);
        }
    }

    private void doReply(CIMSession cimSession, SentBody body) {
        DefaultFuture.received(cimSession, body);
    }

    private void doRequest(CIMSession newSession, SentBody body, String action) {
        ReplyBody reply = new ReplyBody();
        reply.setKey(body.getKey());
        reply.put("type", body.get("type"));
        reply.setCode(HttpStatus.OK.value());
        reply.setTimestamp(System.currentTimeMillis());

        Object object = null;
        try {
            String type = action.split(":")[0];

            if (type.equalsIgnoreCase(Constant.MES_BROADCAST)) {
                logger.info(Constant.MES_BROADCAST + ":", body.getData());
                broadcast(body);
            } else if (type.equalsIgnoreCase(Constant.MES_USER)) {
                logger.info(Constant.MES_USER + ":", body.getData());
                object = user(body, action);
            } else if (type.equalsIgnoreCase(Constant.MES_SINGLE)) {
                logger.info(Constant.MES_SINGLE + ":", body.getData());
                object = user(body, action);
            }else if (type.equalsIgnoreCase(Constant.MES_SINGLE_HIS)){
                logger.info(Constant.MES_SINGLE_HIS + ":", body.getData());
                object = user(body, action);
            } else if (type.equalsIgnoreCase(Constant.MES_WEB_RTC)){
                logger.info(Constant.MES_WEB_RTC + ":", body.getData());
                object = user(body, action);
            } else {
                throw new PushMessageException(CodeMsgEnum.MESSAGE_TYPE_ERROR);
            }
        } catch (Exception exception) {
            if (exception instanceof BaseException) {
                BaseException e = (BaseException) exception;
                reply.setCode(e.getCodeMsgEnum().getCode());
                reply.setMessage(e.getCodeMsgEnum().getMsg());
            }
//            logger.error("push has error", exception);
        }

        reply.put("content", new Gson().toJson(object));
        newSession.write(reply);
    }

    private Object user(SentBody body, String action) {
        String service = action.split(":")[1];
        String method = action.split(":")[2];

        Object msgServiceBean = MsgServiceBeanFactory.getMsgServiceBean(service);
        Method msgMethodBean = (Method) MsgServiceBeanFactory.getMsgMethodBean(method);

        Parameter[] parameters = msgMethodBean.getParameters();
        try {
            Object[] objects = new Object[parameters.length];
            for (int i = 0; i < parameters.length; i++) {
                objects[i] = setField(body, parameters[i].getType(), parameters[i].getType().newInstance());
            }

            return msgMethodBean.invoke(msgServiceBean, objects);
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
//            logger.error("push error", e);
            throw new PushMessageException(CodeMsgEnum.SYSTEM_ERROR);
        }
    }

    private Object setField(SentBody body, Class pCls, Object obj) throws IllegalAccessException {
        Field[] declaredFields = pCls.getDeclaredFields();
        for (Field declaredField : declaredFields) {
            String name = declaredField.getName();
            declaredField.setAccessible(true);

            if (name.equals("key")) {
                declaredField.set(obj, body.getKey());
                continue;
            }

            if (name.equals("timestamp")) {
                declaredField.set(obj, body.getTimestamp());
                continue;
            }

            String value = body.getData().get(name);
            if (value != null) {
                Object to = getToBean(declaredField.getType(), value);
                declaredField.set(obj, to);
            }
        }

        Class<?> superclass = pCls.getSuperclass();
        if (superclass != null) {
            setField(body, superclass, obj);
        }
        return obj;
    }

    //TODO:先处理简单请求类型，复杂的参考springmvc解析对象
    //TODO:考虑更改sentBody的Map结构，直接传入json
    private Object getToBean(Class<?> type, String from) {
        if (type.isAssignableFrom(Integer.class))
            return Integer.valueOf(from);

        if (type.isAssignableFrom(Long.class))
            return Long.valueOf(from);

        if (type.isAssignableFrom(Double.class))
            return Double.valueOf(from);

        if (type.isAssignableFrom(Float.class))
            return Float.valueOf(from);

        return from;
    }

    private void broadcast(SentBody body) {
        String content = body.get("content");

        // 获取所有的连接
        String fromAccount = body.get("fromAccount");
        String action = body.get("action");

        CIMSession fromSession = cimSessionService.get(fromAccount);
        List<CIMSession> list = cimSessionService.list();
        //刪除自己发送的消息
        list.remove(fromSession);

        list.forEach(s -> {
            Message m = new Message();

            m.setAction(action);
            m.setContent(content);
            m.setSender(fromAccount);
            m.setReceiver(s.getAccount());

            defaultMessagePusher.push(m);
        });
    }
}
