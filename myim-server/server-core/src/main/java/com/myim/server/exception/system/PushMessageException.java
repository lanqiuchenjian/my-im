package com.myim.server.exception.system;

import com.myim.server.enumm.CodeMsgEnum;
import com.myim.server.exception.BaseException;

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
