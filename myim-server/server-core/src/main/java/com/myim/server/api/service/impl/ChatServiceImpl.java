package com.myim.server.api.service.impl;

import com.myim.server.api.dto.resp.base.BaseResponse;
import com.myim.server.api.service.ChatService;
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
    private RedisDao redisDao;

    @Override
    public SingleMessageRespBo sendSingleMessage(SingleMessageReqBo singleMessageReqBo) {
        //TODO:chenjian  实现单台服务器先
        CIMSession fromSession = cimSessionService.get(singleMessageReqBo.getFromLoginName());
        CIMSession toSession = cimSessionService.get(singleMessageReqBo.getToLoginName());

        //存储消息内容
        //发送给接收方
        executor.submit(() -> {
            Message msg = new Message();

            msg.setKey(singleMessageReqBo.getKey());
            msg.setSender(singleMessageReqBo.getFromLoginName());
            msg.setReceiver(singleMessageReqBo.getToLoginName());
            msg.setContent(singleMessageReqBo.getContent());

            toSession.write(msg);
        });

        return BaseResponse.success(new SingleMessageRespBo());
    }
}
