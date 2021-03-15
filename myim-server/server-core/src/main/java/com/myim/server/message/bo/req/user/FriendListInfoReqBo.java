package com.myim.server.message.bo.req.user;

import com.myim.server.message.bo.BaseRequestBo;
import lombok.Data;

@Data
public class FriendListInfoReqBo extends BaseRequestBo {
    private Long imUserId;
    private Long imUserCategoryId;
}
