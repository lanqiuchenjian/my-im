package com.myim.cluster.dto.response;

import com.myim.common.basepojo.BaseResponse;
import lombok.Data;

/**
 * @author cj
 */
@Data
public class ImServerRespDto extends BaseResponse{
    private String host;
    private String port;
}
