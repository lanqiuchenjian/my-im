package com.myim.server.message.service.user.impl;

import com.myim.server.api.service.UserService;
import com.myim.server.message.annotation.ImProperty;
import com.myim.server.message.bo.req.ApplyFriendReqBo;
import com.myim.server.message.bo.resp.ApplyFriendRespBo;
import com.myim.server.message.annotation.ImMethod;
import com.myim.server.message.annotation.ImService;
import com.myim.server.message.service.user.UserMsgService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@ImService(value = "UserMsgService")
public class UserMsgServiceImpl implements UserMsgService {
    @Autowired
    private UserService userService;

    @Override
    @ImMethod(value = "applySingleFriend")
    public ApplyFriendRespBo applySingleFriend(@ImProperty ApplyFriendReqBo applyFriendBo) {
        return userService.applyFriend(applyFriendBo);
    }
}
