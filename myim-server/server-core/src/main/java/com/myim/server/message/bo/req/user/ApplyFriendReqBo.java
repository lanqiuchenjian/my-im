package com.myim.server.message.bo.req.user;

import com.myim.server.message.bo.BaseRequestBo;
import lombok.Data;

@Data
public class ApplyFriendReqBo extends BaseRequestBo {
    private Long fromImUserId;
    private Long toImUserId;
}
