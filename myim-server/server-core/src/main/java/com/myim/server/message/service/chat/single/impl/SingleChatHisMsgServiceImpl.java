package com.myim.server.message.service.chat.single.impl;

import com.myim.server.api.service.ChatHistoryService;
import com.myim.server.message.annotation.ImMethod;
import com.myim.server.message.annotation.ImService;
import com.myim.server.message.bo.req.chat.single.SingleHistoryMessageReqBo;
import com.myim.server.message.bo.resp.chat.SingleHistoryMessageRespBo;
import com.myim.server.message.service.chat.single.SingleHistoryMsgService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@ImService(value = "SingleChatHisMsgServiceImpl")
public class SingleChatHisMsgServiceImpl implements SingleHistoryMsgService {
    @Autowired
    private ChatHistoryService chatHistroyService;

    @Override
    @ImMethod(value = "sendSingleMessage")
    public SingleHistoryMessageRespBo getSingleHisMsg(SingleHistoryMessageReqBo singleMessageReqBo) {
        return null;
    }
}
