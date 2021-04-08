package com.myim.server.message.service.chat.single.impl;

import com.myim.server.api.service.ChatService;
import com.myim.server.message.annotation.ImMethod;
import com.myim.server.message.annotation.ImService;
import com.myim.server.message.bo.req.chat.single.SingleMessageReqBo;
import com.myim.server.message.bo.req.chat.single.SingleWebrtcMessageReqBo;
import com.myim.server.message.bo.req.chat.single.SingleWebrtcReqBo;
import com.myim.server.message.bo.resp.chat.SingleMessageRespBo;
import com.myim.server.message.bo.resp.chat.SingleWebrtcMessageRespBo;
import com.myim.server.message.bo.resp.chat.SingleWebrtcRespBo;
import com.myim.server.message.service.chat.single.SingleChatMsgService;
import com.myim.server.message.service.chat.single.SingleChatWebrtcService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
@ImService(value = "SingleChatWebrtcServiceImpl")
public class SingleChatWebrtcServiceImpl implements SingleChatWebrtcService {
    @Autowired
    private ChatService chatService;

    @Override
    @ImMethod(value = "webrtcCreate")
    public SingleWebrtcRespBo webrtcCreate(SingleWebrtcReqBo singleWebrtcReqBo) {
        return chatService.webrtcCreate(singleWebrtcReqBo);
    }

    @Override
    @ImMethod(value = "webrtcMessage")
    public SingleWebrtcMessageRespBo webrtcMessage(SingleWebrtcMessageReqBo singleWebrtcMessageReqBo) throws IOException {
        return chatService.webrtcMessage(singleWebrtcMessageReqBo);
    }
}
