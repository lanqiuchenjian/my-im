package com.myim.server.api.dto.req;

import com.myim.common.basepojo.BaseRequest;
import lombok.Data;

@Data
public class UserRegisterReqDto extends BaseRequest {
    private String phone;

    private String loginName;

    private String loginPassword;
}
