package com.myim.server.message.service.chat.single;

import com.myim.server.message.bo.req.chat.SingleMessageReqBo;
import com.myim.server.message.bo.resp.chat.SingleMessageRespBo;
import com.myim.server.message.service.chat.ChatMsgService;

public interface SingleChatMsgService extends ChatMsgService {
    SingleMessageRespBo sendSingleMessage(SingleMessageReqBo applyFriendBo);
}
