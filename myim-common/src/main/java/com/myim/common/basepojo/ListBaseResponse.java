package com.myim.common.basepojo;

import com.myim.common.enumm.CodeMsgEnum;
import lombok.Data;

import java.util.List;

@Data
public class ListBaseResponse<T> {
    private String status;

    private String code;

    private String msg;

    private String serviceType;

    private List<T> data;

    public static <T extends ListBaseResponse> T success(T t) {
        t.setStatus(CodeMsgEnum.SUCCESS.getStatus());
        t.setCode(CodeMsgEnum.SUCCESS.getCode());
        t.setMsg(CodeMsgEnum.SUCCESS.getMsg());
        return t;
    }

    public static <T extends ListBaseResponse> T fail(T t, CodeMsgEnum codeMsgEnum) {
        t.setStatus(codeMsgEnum.getStatus());
        t.setCode(codeMsgEnum.getCode());
        t.setMsg(codeMsgEnum.getMsg());
        return t;
    }
}
