package com.myim.server.message.bo.resp.chat;

import com.myim.common.basepojo.BaseResponse;
import lombok.Data;

@Data
public class SingleWebrtcMessageRespBo extends BaseResponse{
    private Object message;
}
