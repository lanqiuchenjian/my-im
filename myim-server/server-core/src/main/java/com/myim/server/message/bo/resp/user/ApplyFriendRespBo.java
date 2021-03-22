package com.myim.server.message.bo.resp.user;

import com.myim.common.basepojo.BaseResponse;
import lombok.Data;

@Data
public class ApplyFriendRespBo extends BaseResponse{
    private Long fromImUserId;
    private Long toImUserId;
}
