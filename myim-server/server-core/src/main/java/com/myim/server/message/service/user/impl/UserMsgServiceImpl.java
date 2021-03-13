package com.myim.server.message.service.user.impl;

import com.myim.server.api.service.UserService;
import com.myim.server.message.annotation.ImProperty;
import com.myim.server.message.bo.req.ApplyFriendReqBo;
import com.myim.server.message.bo.req.FriendInfoReqBo;
import com.myim.server.message.bo.req.FriendListInfoReqBo;
import com.myim.server.message.bo.resp.ApplyFriendRespBo;
import com.myim.server.message.annotation.ImMethod;
import com.myim.server.message.annotation.ImService;
import com.myim.server.message.bo.resp.FriendInfoRespBo;
import com.myim.server.message.bo.resp.FriendListInfoRespBo;
import com.myim.server.message.service.user.UserMsgService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

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

    @Override
    @ImMethod(value = "friendInfo")
    public FriendInfoRespBo friendInfo(FriendInfoReqBo friendInfoReqBo) {
        return userService.friendInfo(friendInfoReqBo);
    }

    @Override
    @ImMethod(value = "friendListInfo")
    public FriendListInfoRespBo friendListInfo(FriendListInfoReqBo friendListInfoReqBo) {
        return userService.friendListInfo(friendListInfoReqBo);
    }
}
