package com.myim.server.message.bo.req;

import com.myim.server.message.bo.BaseRequestBo;
import lombok.Data;

@Data
public class FriendInfoReqBo extends BaseRequestBo {
    private Long fromImUserId;
    private Long toImUserId;
    private String categoryName;
}
