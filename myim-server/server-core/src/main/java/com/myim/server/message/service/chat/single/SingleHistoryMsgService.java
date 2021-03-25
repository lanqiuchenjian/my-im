package com.myim.server.message.service.chat.single;

import com.myim.server.message.bo.req.chat.single.SingleHistoryMessageReqBo;
import com.myim.server.message.bo.resp.chat.SingleHistoryMessageRespBo;
import com.myim.server.message.service.chat.HistoryMsgService;

public interface SingleHistoryMsgService extends HistoryMsgService{
    SingleHistoryMessageRespBo getSingleHisMsg(SingleHistoryMessageReqBo singleMessageReqBo);
}
