package com.myim.server.api.dto.resp.base;

import lombok.Data;

@Data
public class BaseResponse {
    private String status;

    private String code;

    private String msg;

    public static <T extends BaseResponse> T success(T t) {
        t.setStatus("success");
        t.setCode("000000");
        t.setMsg("");
        return t;
    }

    public static <T extends BaseResponse> T fail(T t, String code, String msg) {
        t.setStatus("fail");
        t.setCode(code);
        t.setMsg(msg);
        return t;
    }
}
