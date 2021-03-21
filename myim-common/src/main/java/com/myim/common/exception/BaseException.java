package com.myim.common.exception;

import com.myim.common.enumm.CodeMsgEnum;
import lombok.Data;

@Data
public class BaseException extends RuntimeException {
    private CodeMsgEnum codeMsgEnum;

    public BaseException() {
        super();
    }

    public BaseException(CodeMsgEnum codeMsgEnum) {
        super(codeMsgEnum.getMsg());
        this.codeMsgEnum = codeMsgEnum;
    }

    public BaseException(CodeMsgEnum codeMsgEnum, Throwable cause) {
        super(codeMsgEnum.getMsg(), cause);
        this.codeMsgEnum = codeMsgEnum;
    }
}
