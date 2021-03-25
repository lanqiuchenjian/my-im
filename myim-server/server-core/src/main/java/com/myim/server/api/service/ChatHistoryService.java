package com.myim.server.api.service;

import com.myim.server.message.bo.req.chat.single.SingleHistoryMessageReqBo;
import com.myim.server.message.bo.resp.chat.SingleHistoryMessageRespBo;

public interface ChatHistoryService {
	/*************************tcp*************************************/
	//发送单聊消息
	SingleHistoryMessageRespBo getSingleHisMsg(SingleHistoryMessageReqBo singleMessageReqBo);
}
