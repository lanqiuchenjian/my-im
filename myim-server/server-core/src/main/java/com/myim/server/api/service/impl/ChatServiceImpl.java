package com.myim.server.api.service.impl;

import com.myim.server.api.dto.resp.base.BaseResponse;
import com.myim.server.api.service.ChatService;
import com.myim.server.constant.Constant;
import com.myim.server.gen.domain.*;
import com.myim.server.gen.mapper.ImMessageMapper;
import com.myim.server.gen.mapper.ImUserMapper;
import com.myim.server.gen.mapper.ImUserSingleCategoryMapper;
import com.myim.server.gen.mapper.ImUserSingleRelationMapper;
import com.myim.server.message.bo.req.chat.SingleMessageReqBo;
import com.myim.server.message.bo.resp.chat.SingleMessageRespBo;
import com.myim.server.message.service.session.CIMSessionService;
import com.myim.server.model.CIMSession;
import com.myim.server.model.Message;
import com.myim.server.redis.RedisDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.concurrent.*;
import java.util.stream.Collectors;

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

        CompletableFuture completableFuture = new CompletableFuture();

        try {
            completableFuture.get(5, TimeUnit.SECONDS);
        } catch (InterruptedException | ExecutionException | TimeoutException e) {
            //TODO:chenjian 重试逻辑
            e.printStackTrace();
        }

        return BaseResponse.success(new SingleMessageRespBo());
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

        imMessage.setAction(singleMessageReqBo.getAction());
        //TODO:chenjian 成功更新为true，现在先设为true
        imMessage.setCheck(true);
        imMessage.setContent(singleMessageReqBo.getContent());
        imMessage.setFormat("string");
        imMessage.setKey(singleMessageReqBo.getKey());
        imMessage.setImusersinglerelationid(imUserSingleRelation.getId());
        imMessage.setReceiver(singleMessageReqBo.getToLoginName());
        imMessage.setSender(singleMessageReqBo.getFromLoginName());
        imMessage.setRepeat("0");
        //TODO: 0:正常 1：发送者删除 2：接收者删除 3:撤销
        imMessage.setStatus(0);
        imMessage.setTitle("");
        //TODO: 新增是否是离线消息，新建离线消息表，保持最后一条记录id

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
