package com.myim.server.api.service.impl;

import com.myim.common.basepojo.BaseResponse;
import com.myim.server.api.service.ChatService;
import com.myim.server.common.DefaultFuture;
import com.myim.server.constant.Constant;
import com.myim.server.dao.gen.domain.*;
import com.myim.server.dao.gen.mapper.ImMessageMapper;
import com.myim.server.dao.gen.mapper.ImUserSingleCategoryMapper;
import com.myim.server.dao.gen.mapper.ImUserSingleRelationMapper;
import com.myim.server.message.bo.req.chat.SingleMessageReqBo;
import com.myim.server.message.bo.resp.chat.SingleMessageRespBo;
import com.myim.server.message.service.session.CIMSessionService;
import com.myim.server.model.CIMSession;
import com.myim.server.model.Message;
import com.myim.server.mq.MqInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

@Service
public class ChatServiceImpl implements ChatService {
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
    private MqInstance mqInstance;

    @Override
    public SingleMessageRespBo sendSingleMessage(SingleMessageReqBo singleMessageReqBo) {
        Boolean local = cimSessionService.isLocal(singleMessageReqBo.getToLoginName());
        String toLoginName = singleMessageReqBo.getToLoginName();

        if (local) {
            CIMSession toSession = cimSessionService.get(toLoginName);
            doSendMsg(singleMessageReqBo, toSession);
        }else {
            //发送一条mq消息
            String hostName = cimSessionService.getHostName(toLoginName);
            mqInstance.sendMsg(singleMessageReqBo, hostName);
        }
        return BaseResponse.success(new SingleMessageRespBo());
    }

    private void doSendMsg(SingleMessageReqBo singleMessageReqBo, CIMSession toSession) {
        //存储消息内容
        ImMessage imMessage = saveMes(singleMessageReqBo);
        imMessageMapper.insertSelective(imMessage);

        //发送给接收方
        Message msg = new Message();
        msg.setKey(singleMessageReqBo.getKey());
        msg.setAction(Constant.MES_SINGLE);
        msg.setSender(singleMessageReqBo.getFromLoginName());
        msg.setReceiver(singleMessageReqBo.getToLoginName());
        msg.setContent(singleMessageReqBo.getContent());
        msg.setTimestamp(singleMessageReqBo.getTimestamp());

        if (toSession != null) {
            executor.submit(() -> toSession.write(msg));
            //等待接收方响应，异常处理
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
        //TODO:chenjian 成功更新为true，现在先设为true
        imMessage.setmCheck(true);
        imMessage.setContent(singleMessageReqBo.getContent());
        imMessage.setmFormat("string");
        imMessage.setmKey(singleMessageReqBo.getKey());
        imMessage.setImUserSingleRelationId(imUserSingleRelation.getId());
        imMessage.setReceiver(singleMessageReqBo.getToLoginName());
        imMessage.setSender(singleMessageReqBo.getFromLoginName());
        imMessage.setmRepeat("0");
        //TODO: 0:正常 1：发送者删除 2：接收者删除 3:撤销
        imMessage.setmStatus(0);
        imMessage.setTitle("");
        //TODO: 新增是否是离线消息，新建离线消息表，保持最后一条记录id
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
