package com.myim.server.api.dto.req;

import com.myim.common.basepojo.BaseRequest;
import lombok.Data;

@Data
public class UserLoginReqDto extends BaseRequest {
    private String loginName;

    private String loginPassword;
}
