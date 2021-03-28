package com.myim.server.service.offline;

import com.myim.server.BaseTest;
import com.myim.server.message.bo.req.chat.single.SingleOfflineMessageReqBo;
import com.myim.server.message.bo.resp.chat.SingleOfflineMessageRespBo;
import com.myim.server.message.service.chat.single.SingleHistoryMsgService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author cj
 */
public class OfflineTest extends BaseTest {
    @Autowired
    private SingleHistoryMsgService singleHistoryMsgService;

    @Test
    public void offlineMsgTest() {
        SingleOfflineMessageReqBo singleOfflineMessageReqBo = new SingleOfflineMessageReqBo();

        singleOfflineMessageReqBo.setImUserId(7L);
        singleOfflineMessageReqBo.setImUserLoginName("chenjian");

        SingleOfflineMessageRespBo singleOfflineMsg = singleHistoryMsgService.getSingleOfflineMsg(singleOfflineMessageReqBo);
        System.out.println(singleOfflineMsg);
    }
}
