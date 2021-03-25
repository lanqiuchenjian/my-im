package com.myim.server.message.bo.req.chat.single;

import com.myim.server.message.bo.BaseRequestBo;
import lombok.Data;

@Data
public class SingleHistoryMessageReqBo extends BaseRequestBo {
    //消息发送者
    private Long fromImUserId;
    private String fromLoginName;
    //消息接收者
    private Long toImUserId;
    private String toLoginName;

    private String action;

    //默认值为0，取最新10条
    //每页10条记录
    private Long page = 0L;
    private Long size = 10L;
}
