package com.myim.server.message.bo.req.chat.single;

import com.myim.server.message.bo.BaseRequestBo;
import lombok.Data;

@Data
public class SingleWebrtcMessageReqBo extends BaseRequestBo {
    private String message;
    private String toImUserName;
}
