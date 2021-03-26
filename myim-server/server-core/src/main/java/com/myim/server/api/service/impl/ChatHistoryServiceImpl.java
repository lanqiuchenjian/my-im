package com.myim.server.api.service.impl;

import com.myim.common.basepojo.ListBaseResponse;
import com.myim.server.api.service.ChatHistoryService;
import com.myim.server.api.service.UserService;
import com.myim.server.common.BeanCommon;
import com.myim.server.dao.ext.mapper.ImMessageExtMapper;
import com.myim.server.dao.gen.domain.ImMessage;
import com.myim.server.dao.gen.domain.ImUserSingleRelation;
import com.myim.server.message.bo.req.chat.single.SingleHistoryMessageReqBo;
import com.myim.server.message.bo.resp.chat.MessageBo;
import com.myim.server.message.bo.resp.chat.SingleHistoryMessageRespBo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@Service
public class ChatHistoryServiceImpl implements ChatHistoryService {
    @Autowired
    private UserService userService;

    @Autowired
    private ImMessageExtMapper imMessageExtMapper;

    @Override
    public SingleHistoryMessageRespBo getSingleHisMsg(SingleHistoryMessageReqBo singleMessageReqBo) {
        SingleHistoryMessageRespBo singleHistoryMessageRespBo = new SingleHistoryMessageRespBo();
        singleHistoryMessageRespBo.setServiceType("getSingleHisMsg");

        List<MessageBo> data = new ArrayList<>();

        ImUserSingleRelation from = userService.getRelationByImUserIAndCategoryId(singleMessageReqBo.getToImUserId(), singleMessageReqBo.getImUserCategoryId());
        ImUserSingleRelation to = userService.getRelationByFromAndToId(singleMessageReqBo.getToImUserId(), singleMessageReqBo.getFromImUserId());

        Long imUserSingleRelationId = singleMessageReqBo.getFromImUserId() > singleMessageReqBo.getToImUserId() ? to.getId() : from.getId();

//        int count = imMessageExtMapper.getCount(imUserSingleRelationId);
//        Long page = singleMessageReqBo.getPage();
        Long size = singleMessageReqBo.getSize();

        List<ImMessage> hisMesByPageWithIdDesc = imMessageExtMapper.getHisMesByPageWithIdDesc(singleMessageReqBo.getLastId(), size, imUserSingleRelationId);

        hisMesByPageWithIdDesc.forEach(h -> {
            MessageBo bo = new MessageBo();
            BeanCommon.copyFromTo(h, bo, false);
            data.add(bo);
        });

//        data.sort(Comparator.comparing(MessageBo::getId));
        singleHistoryMessageRespBo.setData(data);
        return ListBaseResponse.success(singleHistoryMessageRespBo);
    }
}
