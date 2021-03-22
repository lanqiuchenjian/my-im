package com.myim.server.api.dto.resp;

import com.myim.common.basepojo.BaseResponse;
import lombok.Data;

@Data
public class UserRegisterRespDto extends BaseResponse{
    private Long registerImUserId;
    private Long singleCategoryId;
}
