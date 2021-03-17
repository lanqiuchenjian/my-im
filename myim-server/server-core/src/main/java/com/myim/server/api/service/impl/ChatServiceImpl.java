package com.myim.server.api.service.impl;

import com.myim.server.api.dto.resp.base.BaseResponse;
import com.myim.server.api.service.ChatService;
import com.myim.server.constant.Constant;
import com.myim.server.dao.gen.domain.ImMessage;
import com.myim.server.dao.gen.mapper.ImMessageMapper;
import com.myim.server.dao.gen.mapper.ImUserMapper;
import com.myim.server.dao.gen.mapper.ImUserSingleCategoryMapper;
import com.myim.server.dao.gen.mapper.ImUserSingleRelationMapper;
import com.myim.server.message.bo.req.chat.SingleMessageReqBo;
import com.myim.server.message.bo.resp.chat.SingleMessageRespBo;
import com.myim.server.message.service.session.CIMSessionService;
import com.myim.server.model.CIMSession;
import com.myim.server.model.Message;
import com.myim.server.redis.RedisDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

@Service
public class ChatServiceImpl implements ChatService {
    private ThreadPoolExecutor executor = new ThreadPoolExecutor(10, 50,30, TimeUnit.MINUTES, new LinkedBlockingQueue<>(1000));
    @Autowired
    private CIMSessionService cimSessionService;

    @Autowired
    private ImUserMapper imUserMapper;

    @Autowired
    private ImUserSingleCategoryMapper imUserSingleCategoryMapper;

    @Autowired
    private ImUserSingleRelationMapper imUserSingleRelationMapper;

    @Autowired
    private ImMessageMapper imMessageMapper;

    @Autowired
    private RedisDao redisDao;

    @Override
    public SingleMessageRespBo sendSingleMessage(SingleMessageReqBo singleMessageReqBo) {
        //TODO:chenjian  实现单台服务器先
        CIMSession fromSession = cimSessionService.get(singleMessageReqBo.getFromLoginName());
        CIMSession toSession = cimSessionService.get(singleMessageReqBo.getToLoginName());

        //存储消息内容
        ImMessage imMessage = saveMes(singleMessageReqBo);
        imMessageMapper.insertSelective(imMessage);

        //发送给接收方
        if (toSession != null) {
            executor.submit(() -> {
                Message msg = new Message();

                msg.setKey(singleMessageReqBo.getKey());
                msg.setAction(Constant.MES_SINGLE);
                msg.setSender(singleMessageReqBo.getFromLoginName());
                msg.setReceiver(singleMessageReqBo.getToLoginName());
                msg.setContent(singleMessageReqBo.getContent());
                msg.setTimestamp(new Date().getTime());

                toSession.write(msg);
            });
        }


        return BaseResponse.success(new SingleMessageRespBo());
    }

    private ImMessage saveMes(SingleMessageReqBo singleMessageReqBo) {
        ImMessage imMessage = new ImMessage();

        imMessage.setAction(singleMessageReqBo.getAction());
        //TODO:chenjian 成功更新为true，现在先设为true
        imMessage.setCheck(true);
        imMessage.setContent(singleMessageReqBo.getContent());
        imMessage.setFormat("string");
        imMessage.setKey(singleMessageReqBo.getKey());
        //TODO:
        imMessage.setImusersinglerelationid(null);
        imMessage.setReceiver(singleMessageReqBo.getToLoginName());
        imMessage.setSender(singleMessageReqBo.getFromLoginName());
        imMessage.setRepeat("0");
        //TODO: 0:正常 1：发送者删除 2：接收者删除
        imMessage.setStatus(false);
        imMessage.setTitle("");
        //TODO: 新增是否是离线消息，新建离线消息表，保持最后一条记录id

        return imMessage;
    }
}
