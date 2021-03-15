package com.myim.server.message.bo.req.chat;

import com.myim.server.message.bo.BaseRequestBo;
import lombok.Data;

@Data
public class SingleMessageReqBo extends BaseRequestBo {
    //消息发送者
    private Long fromImUserId;
    private String fromLoginName;
    //消息接收者
    private Long toImUserId;
    private String toLoginName;
    //发送的内容JSON表示
    private String content;
}
