package com.myim.server.api.service;

import com.myim.server.message.bo.req.chat.single.SingleHistoryMessageReqBo;
import com.myim.server.message.bo.req.chat.single.SingleOfflineMessageReqBo;
import com.myim.server.message.bo.resp.chat.SingleHistoryMessageRespBo;
import com.myim.server.message.bo.resp.chat.SingleOfflineMessageRespBo;

public interface ChatHistoryService {
	/*************************tcp*************************************/
	//获取单聊历史消息
	SingleHistoryMessageRespBo getSingleHisMsg(SingleHistoryMessageReqBo singleMessageReqBo);

	//获取单聊离线消息
	SingleOfflineMessageRespBo getSingleOfflineMsg(SingleOfflineMessageReqBo singleMessageReqBo);
}
