package com.myim.server.message.service.chat.single;

import com.myim.server.message.bo.req.chat.single.SingleWebrtcReqBo;
import com.myim.server.message.bo.resp.chat.SingleWebrtcRespBo;
import com.myim.server.message.service.chat.ChatMsgService;

import java.io.IOException;

public interface SingleChatWebrtcService extends ChatMsgService {
    SingleWebrtcRespBo webrtcCreate(SingleWebrtcReqBo singleWebrtcReqBo) throws IOException;
}
