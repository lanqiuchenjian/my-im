package com.myim.common.exception.system;

import com.myim.common.enumm.CodeMsgEnum;
import com.myim.common.exception.BaseException;

public class PushMessageException extends BaseException {
    public PushMessageException() {
        super();
    }

    public PushMessageException(CodeMsgEnum codeMsgEnum) {
        super(codeMsgEnum);
    }

    public PushMessageException(CodeMsgEnum codeMsgEnum, Throwable cause) {
        super(codeMsgEnum, cause);
    }
}
