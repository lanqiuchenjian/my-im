package com.myim.common.exception.user;

import com.myim.common.enumm.CodeMsgEnum;
import com.myim.common.exception.BaseException;
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
