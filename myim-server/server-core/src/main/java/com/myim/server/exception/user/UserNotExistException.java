package com.myim.server.exception.user;

import com.myim.server.enumm.CodeMsgEnum;
import com.myim.server.exception.BaseException;
import lombok.Data;

@Data
public class UserNotExistException extends BaseException {
    public UserNotExistException() {
        super();
    }

    public UserNotExistException(CodeMsgEnum codeMsgEnum) {
        super(codeMsgEnum);
    }

    public UserNotExistException(CodeMsgEnum codeMsgEnum, Throwable cause) {
        super(codeMsgEnum, cause);
    }
}
