package com.myim.server.message.bo.req.chat.single;

import com.myim.server.message.bo.BaseRequestBo;
import lombok.Data;

@Data
public class SingleOfflineMessageReqBo extends BaseRequestBo {
    //消息发送者
    private Long imUserId;
    private String imUserLoginName;
}
