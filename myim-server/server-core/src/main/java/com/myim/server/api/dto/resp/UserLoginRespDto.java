package com.myim.server.api.dto.resp;

import com.myim.common.basepojo.BaseResponse;
import lombok.Data;

import java.util.List;

@Data
public class UserLoginRespDto extends BaseResponse {
    private Long registerImUserId;
    private List<Long> singleCategoryIdList;
}
