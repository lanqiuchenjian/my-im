package com.myim.common.exception.user;

import com.myim.common.enumm.CodeMsgEnum;
import com.myim.common.exception.BaseException;
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
