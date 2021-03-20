package com.myim.server.message.bo.req.chat;

import com.myim.server.message.bo.BaseRequestBo;
import lombok.Data;

import java.util.Date;

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
    private String action;
    private Long timestamp;

    //发送的处理类型
    private String type;
}
