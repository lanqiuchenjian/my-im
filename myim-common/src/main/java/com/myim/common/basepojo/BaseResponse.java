package com.myim.common.basepojo;

import com.myim.common.enumm.CodeMsgEnum;
import lombok.Data;

@Data
public class BaseResponse {
    private String status;

    private String code;

    private String msg;

    private String serviceType;

    public static <T extends BaseResponse> T success(T t) {
        t.setStatus(CodeMsgEnum.SUCCESS.getStatus());
        t.setCode(CodeMsgEnum.SUCCESS.getCode());
        t.setMsg(CodeMsgEnum.SUCCESS.getMsg());
        return t;
    }

    public static <T extends BaseResponse> T fail(T t, CodeMsgEnum codeMsgEnum) {
        t.setStatus(codeMsgEnum.getStatus());
        t.setCode(codeMsgEnum.getCode());
        t.setMsg(codeMsgEnum.getMsg());
        return t;
    }
}
