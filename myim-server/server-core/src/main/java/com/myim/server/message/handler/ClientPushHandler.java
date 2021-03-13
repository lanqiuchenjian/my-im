/*
 * Copyright 2013-2019 Xia Jun(3979434@qq.com).
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 ***************************************************************************************
 *                                                                                     *
 *                        Website : http://www.farsunset.com                           *
 *                                                                                     *
 ***************************************************************************************
 */
package com.myim.server.message.handler;

import com.google.gson.Gson;
import com.myim.server.enumm.CodeMsgEnum;
import com.myim.server.exception.BaseException;
import com.myim.server.exception.system.PushMessageException;
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
import java.util.Arrays;
import java.util.List;
import java.util.Objects;


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

        ReplyBody reply = new ReplyBody();
        reply.setKey(body.getKey());
        reply.setCode(HttpStatus.OK.value());
        reply.setTimestamp(System.currentTimeMillis());

        Object object = null;
        try {
            String action = body.get("action");
            String type = action.split(":")[0];

            if (type.equalsIgnoreCase("broadcast"))
                broadcast(body);

            if (type.equalsIgnoreCase("user"))
                object = user(body, action);

            broadcast(body);
        } catch (Exception exception) {
            if (exception instanceof BaseException) {
                BaseException e = (BaseException) exception;
                reply.setCode(e.getCodeMsgEnum().getCode());
                reply.setMessage(e.getCodeMsgEnum().getMsg());
            }
            logger.error("push has error", exception);
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
            logger.error("push error", e);
            throw new PushMessageException(CodeMsgEnum.SYSTEM_ERROR);
        }
    }

    private Object setField(SentBody body, Class pCls, Object obj) throws IllegalAccessException {
        Field[] declaredFields = pCls.getDeclaredFields();
        for (int j = 0; j < declaredFields.length; j++) {
            declaredFields[j].setAccessible(true);
            String value = body.getData().get(declaredFields[j].getName());
            Object to = getToBean(declaredFields[j].getType(), value);
            declaredFields[j].set(obj, to);
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

    private boolean fromOtherDevice(CIMSession oldSession, CIMSession newSession) {
        return !Objects.equals(oldSession.getDeviceId(), newSession.getDeviceId());
    }
}
