package com.myim.server.enumm;

public enum CodeMsgEnum {
    /********************成功0开头***************************/
    SUCCESS("success", "000000", "操作成功"),



    /********************失败4开头***************************/
    SYSTEM_ERROR("fail", "400000", "系统异常，请稍后重试"),
    USER_OR_PASSWORD_ERROR("fail", "400001", "用户名或者密码错误");



    private String status;
    private String code;
    private String msg;

    CodeMsgEnum(String status, String code, String msg) {
        this.status = status;
        this.code = code;
        this.msg = msg;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
