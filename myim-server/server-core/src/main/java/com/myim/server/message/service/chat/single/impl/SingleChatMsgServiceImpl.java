package com.myim.server.message.service.chat.single.impl;

import com.myim.server.api.service.ChatService;
import com.myim.server.message.annotation.ImMethod;
import com.myim.server.message.annotation.ImService;
import com.myim.server.message.bo.req.chat.single.SingleMessageReqBo;
import com.myim.server.message.bo.resp.chat.SingleMessageRespBo;
import com.myim.server.message.service.chat.single.SingleChatMsgService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
@ImService(value = "SingleChatServiceImpl")
public class SingleChatMsgServiceImpl implements SingleChatMsgService {
    @Autowired
    private ChatService chatService;

    @Override
    @ImMethod(value = "sendSingleMessage")
    public SingleMessageRespBo sendSingleMessage(SingleMessageReqBo singleMessageReqBo) throws IOException {
        return chatService.sendSingleMessage(singleMessageReqBo);
    }
}
