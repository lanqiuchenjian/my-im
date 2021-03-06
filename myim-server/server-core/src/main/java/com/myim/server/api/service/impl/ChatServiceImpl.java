package com.myim.server.api.service.impl;

import com.google.gson.Gson;
import com.myim.common.basepojo.BaseResponse;
import com.myim.common.constant.Constant;
import com.myim.server.api.service.ChatService;
import com.myim.server.common.DefaultFuture;
import com.myim.server.common.RobotUtils;
import com.myim.server.dao.gen.domain.*;
import com.myim.server.dao.gen.mapper.ImMessageMapper;
import com.myim.server.dao.gen.mapper.ImOfflineMessageMapper;
import com.myim.server.dao.gen.mapper.ImUserSingleCategoryMapper;
import com.myim.server.dao.gen.mapper.ImUserSingleRelationMapper;
import com.myim.server.message.bo.req.chat.single.SingleMessageReqBo;
import com.myim.server.message.bo.req.chat.single.SingleWebrtcMessageReqBo;
import com.myim.server.message.bo.req.chat.single.SingleWebrtcReqBo;
import com.myim.server.message.bo.resp.chat.SingleMessageRespBo;
import com.myim.server.message.bo.resp.chat.SingleWebrtcMessageRespBo;
import com.myim.server.message.bo.resp.chat.SingleWebrtcRespBo;
import com.myim.server.message.service.session.CIMSessionService;
import com.myim.server.model.CIMSession;
import com.myim.server.model.Message;
import com.myim.server.mq.MqInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.URLDecoder;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

@Service
public class ChatServiceImpl implements ChatService {
    private AtomicInteger nums = new AtomicInteger(0);

    private ThreadPoolExecutor executor = new ThreadPoolExecutor(10, 50,30, TimeUnit.MINUTES, new LinkedBlockingQueue<>(1000));
    @Autowired
    private CIMSessionService cimSessionService;

    @Autowired
    private ImUserSingleCategoryMapper imUserSingleCategoryMapper;

    @Autowired
    private ImUserSingleRelationMapper imUserSingleRelationMapper;

    @Autowired
    private ImMessageMapper imMessageMapper;

    @Autowired
    private ImOfflineMessageMapper imOfflineMessageMapper;

    @Autowired
    private MqInstance mqInstance;

    @Override
    public SingleMessageRespBo sendSingleMessage(SingleMessageReqBo singleMessageReqBo) throws IOException {
        Boolean local = cimSessionService.isLocal(singleMessageReqBo.getToLoginName());
        String toLoginName = singleMessageReqBo.getToLoginName();

        if (local) {
            CIMSession toSession = cimSessionService.get(toLoginName);
            doSendMsg(singleMessageReqBo, toSession);
        }else {
            String hostName = cimSessionService.getHostName(toLoginName);
            //?????????
            if (singleMessageReqBo.getContent().startsWith("@AI@")) {
                //{"message": "ok",
                // "audio": "http://0.0.0.0:5000/audio/e2c43fb5e4a7ae6bba2a4f110f2ed719.mp3",
                // "plugin": "",
                // "code": 0,
                // "resp": "\u4e0a\u6d77\u5c0f\u96e8\uff0c\u5fae\u98ce\u62c2\u9762\uff0c\u5439\u5439\u633a\u8212\u670d\u5462\u3002\u9884\u8ba116\u2103~19\u2103\uff0c\u6b64\u523b16\u5ea6\u3002\u7a7a\u6c14\u4e0d\u9519\uff0c\u597d\u5f00\u5fc3\u3002\u5e26\u4e0a\u5c0f\u82b1\u4f1e\uff0c\u4e0d\u8981\u8ba9\u5c0f\u96e8\u6b3a\u8d1f\u4e86\u54e6^_^\n\u660e\u65e5\u5929\u6c14\u5c0f\u96e8\uff0c16\u2103~21\u2103\uff0c\u4e2d\u7ea7\u98ce\uff0c\u98ce\u529b2\u7ea7\u3002"}
                CIMSession fromSession = cimSessionService.get(singleMessageReqBo.getFromLoginName());

                Map map = RobotUtils.answerWithAiRobot(singleMessageReqBo.getContent().substring(4));

                Message msg = new Message();
                msg.setKey(singleMessageReqBo.getKey());
                msg.setAction(Constant.MES_SINGLE);
                msg.setSender("@AI@");
                msg.setReceiver(singleMessageReqBo.getFromLoginName());
                msg.setContent(URLDecoder.decode(map.get("resp").toString(),"UTF-8"));
                msg.setTimestamp(new Date().getTime());
                String audio = ((String) map.get("audio")).replace("0.0.0.0", "47.110.41.97");
                msg.setExtra(audio);

                fromSession.write(msg);
            }

            //???????????????????????????mq??????
            else if (hostName != null && !hostName.trim().equalsIgnoreCase("")) {
                mqInstance.sendMsg(singleMessageReqBo, hostName);
            } else {
                //?????????????????????????????????????????????????????????????????????id????????????
                ImMessage imMessage = saveMes(singleMessageReqBo);
                imMessage.setTitle("offline");
                imMessageMapper.insertSelective(imMessage);

                //????????????????????????????????????????????????
                ImOfflineMessageExample imOfflineMessageExample = new ImOfflineMessageExample();

                imOfflineMessageExample.createCriteria()
                        .andIsOfflineEqualTo(true)
                        .andFromImUserIdEqualTo(singleMessageReqBo.getFromImUserId())
                        .andToImUserIdEqualTo(singleMessageReqBo.getToImUserId());

                List<ImOfflineMessage> imOfflineMessages = imOfflineMessageMapper.selectByExample(imOfflineMessageExample);
                //???????????????????????????????????????,???????????????count??????
                if (imOfflineMessages == null || imOfflineMessages.size() == 0) {
                    ImOfflineMessage imOfflineMessage = new ImOfflineMessage();

                    imOfflineMessage.setFromImUserId(singleMessageReqBo.getFromImUserId());
                    imOfflineMessage.setFromImUserLoginName(singleMessageReqBo.getFromLoginName());
                    imOfflineMessage.setToImUserId(singleMessageReqBo.getToImUserId());
                    imOfflineMessage.setToImUserLoginName(singleMessageReqBo.getToLoginName());
                    imOfflineMessage.setIsOffline(true);
                    imOfflineMessage.setOfflineMesCount(1L);
                    imOfflineMessage.setImMessageId(imMessage.getId());

                    imOfflineMessageMapper.insertSelective(imOfflineMessage);
                } else {
                    ImOfflineMessage iolm = imOfflineMessages.get(0);

                    Long count = iolm.getOfflineMesCount();
                    Long offlineId = iolm.getId();

                    ImOfflineMessage imOfflineMessage = new ImOfflineMessage();

                    imOfflineMessage.setId(offlineId);
                    imOfflineMessage.setOfflineMesCount(1L + count);

                    imOfflineMessageMapper.updateByPrimaryKeySelective(imOfflineMessage);
                }
            }
        }
        return BaseResponse.success(new SingleMessageRespBo());
    }

    @Override
    public SingleWebrtcRespBo webrtcCreate(SingleWebrtcReqBo singleWebrtcReqBo) {
        SingleWebrtcRespBo singleWebrtcRespBo = new SingleWebrtcRespBo();

        int andIncrement = nums.getAndIncrement();

        if (andIncrement == 0) {
            singleWebrtcRespBo.setAct("created");
        } else if (andIncrement >= 1) {
//            CIMSession fromSession = cimSessionService.get(singleMessageReqBo.getFromLoginName());
//
//            Map map = RobotUtils.answerWithAiRobot(singleMessageReqBo.getContent().substring(4));

            Message msg = new Message();
            msg.setAction("join");

            cimSessionService.get("chenjian").write(msg);
            cimSessionService.get("linhaiyi").write(msg);

        }

        singleWebrtcRespBo.setServiceType("webrtcCreate");
        return BaseResponse.success(singleWebrtcRespBo);
    }

    @Override
    public SingleWebrtcMessageRespBo webrtcMessage(SingleWebrtcMessageReqBo singleWebrtcMessageReqBo) {
        SingleWebrtcMessageRespBo singleWebrtcMessageRespBo = new SingleWebrtcMessageRespBo();

        String message = singleWebrtcMessageReqBo.getMessage();

        Map map = null;
        try {
            map = new Gson().fromJson(message, Map.class);
        } catch (Exception ignored) {}


        CIMSession cimSession = cimSessionService.get(singleWebrtcMessageReqBo.getToImUserName());

        if (message.equals("bye"))
            nums.decrementAndGet();

        if (cimSession != null) {
            Message msg = new Message();
            msg.setContent(message);
            msg.setAction("message");

            if (map != null) {
                msg.setFormat("obj");
            } else {
                msg.setFormat("str");
            }

            cimSession.write(msg);
        }


        return BaseResponse.success(singleWebrtcMessageRespBo);
    }

    private void doSendMsg(SingleMessageReqBo singleMessageReqBo, CIMSession toSession) {
        //??????????????????
        ImMessage imMessage = saveMes(singleMessageReqBo);
        imMessageMapper.insertSelective(imMessage);

        //??????????????????
        Message msg = new Message();
        msg.setKey(singleMessageReqBo.getKey());
        msg.setAction(Constant.MES_SINGLE);
        msg.setSender(singleMessageReqBo.getFromLoginName());
        msg.setReceiver(singleMessageReqBo.getToLoginName());
        msg.setContent(singleMessageReqBo.getContent());
        msg.setTimestamp(singleMessageReqBo.getTimestamp());

        if (toSession != null) {
            executor.submit(() -> toSession.write(msg));
            //????????????????????????????????????
            new DefaultFuture(toSession, msg, 1500).get();
        }
    }

    private ImMessage saveMes(SingleMessageReqBo singleMessageReqBo) {
        Long fromImUserId = singleMessageReqBo.getFromImUserId();
        Long toImUserId = singleMessageReqBo.getToImUserId();

        ImUserSingleRelation imUserSingleRelation;
        if (fromImUserId > toImUserId) {
            imUserSingleRelation = getImUserRelation(fromImUserId, toImUserId);
        } else {
            imUserSingleRelation = getImUserRelation(toImUserId, fromImUserId);
        }

        ImMessage imMessage = new ImMessage();

        imMessage.setmAction(singleMessageReqBo.getAction());
        //TODO:chenjian ???????????????true??????????????????true
        imMessage.setmCheck(true);
        imMessage.setContent(singleMessageReqBo.getContent());
        imMessage.setmFormat("string");
        imMessage.setmKey(singleMessageReqBo.getKey());
        imMessage.setImUserSingleRelationId(imUserSingleRelation.getId());
        imMessage.setReceiver(singleMessageReqBo.getToLoginName());
        imMessage.setSender(singleMessageReqBo.getFromLoginName());
        imMessage.setmRepeat("0");
        //TODO: 0:?????? 1?????????????????? 2?????????????????? 3:??????
        imMessage.setmStatus(0);
        imMessage.setTitle("online");
        //TODO: ??????????????????????????????????????????????????????????????????????????????id
        imMessage.setmTimestamp(new Date(singleMessageReqBo.getTimestamp()));
        return imMessage;
    }

    private ImUserSingleRelation getImUserRelation(Long fromImUserId, Long toImUserId) {
        ImUserSingleCategoryExample imUserSingleCategoryExample = new ImUserSingleCategoryExample();
        imUserSingleCategoryExample.createCriteria().andImUserIdEqualTo(toImUserId);

        List<ImUserSingleCategory> imUserSingleCategories = imUserSingleCategoryMapper.selectByExample(imUserSingleCategoryExample);
        List<Long> ids = imUserSingleCategories.stream().map(ImUserSingleCategory::getId).collect(Collectors.toList());

        ImUserSingleRelationExample imUserSingleRelationExample = new ImUserSingleRelationExample();
        imUserSingleRelationExample.createCriteria()
                .andImUserSingleCategoryIdIn(ids)
                .andImUserIdEqualTo(fromImUserId);

       return imUserSingleRelationMapper.selectByExample(imUserSingleRelationExample).get(0);
    }
}
