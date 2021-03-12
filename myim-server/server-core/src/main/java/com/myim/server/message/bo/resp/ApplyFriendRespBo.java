package com.myim.server.message.bo.resp;

import com.myim.server.api.dto.resp.base.BaseResponse;
import lombok.Data;

@Data
public class ApplyFriendRespBo extends BaseResponse{
    private Long fromImUserId;
    private Long toImUserId;
}
