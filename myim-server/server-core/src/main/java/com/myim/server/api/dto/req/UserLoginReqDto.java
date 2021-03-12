package com.myim.server.api.dto.req;

import com.myim.server.api.dto.req.base.BaseRequest;
import lombok.Data;

@Data
public class UserLoginReqDto extends BaseRequest {
    private String loginName;

    private String loginPassword;
}
