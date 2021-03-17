package com.myim.server.exception.user;

import com.myim.server.enumm.CodeMsgEnum;
import com.myim.server.exception.BaseException;
import lombok.Data;

@Data
public class UserExistedException extends BaseException {
    public UserExistedException() {
        super();
    }

    public UserExistedException(CodeMsgEnum codeMsgEnum) {
        super(codeMsgEnum);
    }

    public UserExistedException(CodeMsgEnum codeMsgEnum, Throwable cause) {
        super(codeMsgEnum, cause);
    }
}
