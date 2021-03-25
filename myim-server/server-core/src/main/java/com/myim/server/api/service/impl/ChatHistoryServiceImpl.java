package com.myim.server.api.service.impl;

import com.myim.server.api.service.ChatHistoryService;
import com.myim.server.dao.gen.domain.ImMessageExample;
import com.myim.server.dao.gen.mapper.ImMessageMapper;
import com.myim.server.dao.gen.mapper.ImUserSingleCategoryMapper;
import com.myim.server.dao.gen.mapper.ImUserSingleRelationMapper;
import com.myim.server.message.bo.req.chat.single.SingleHistoryMessageReqBo;
import com.myim.server.message.bo.resp.chat.SingleHistoryMessageRespBo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ChatHistoryServiceImpl implements ChatHistoryService {
    @Autowired
    private ImUserSingleCategoryMapper imUserSingleCategoryMapper;

    @Autowired
    private ImUserSingleRelationMapper imUserSingleRelationMapper;

    @Autowired
    private ImMessageMapper imMessageMapper;

    @Override
    public SingleHistoryMessageRespBo getSingleHisMsg(SingleHistoryMessageReqBo singleMessageReqBo) {
        Long page = singleMessageReqBo.getPage();

        //返回最新10条
        if (page == 0) {
            imMessageMapper.getHisMesByPage();
        }
        return null;
    }
}
