package com.myim.server.message.service.user.impl;

import com.myim.server.api.service.UserService;
import com.myim.server.message.annotation.ImProperty;
import com.myim.server.message.bo.req.user.ApplyFriendReqBo;
import com.myim.server.message.bo.req.user.FriendInfoReqBo;
import com.myim.server.message.bo.req.user.FriendListInfoReqBo;
import com.myim.server.message.bo.req.user.SearchFriendListInfoReqBo;
import com.myim.server.message.bo.resp.user.ApplyFriendRespBo;
import com.myim.server.message.annotation.ImMethod;
import com.myim.server.message.annotation.ImService;
import com.myim.server.message.bo.resp.user.FriendInfoRespBo;
import com.myim.server.message.bo.resp.user.FriendListInfoRespBo;
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

    @Override
    @ImMethod(value = "searchFriendListInfo")
    public FriendListInfoRespBo searchFriendListInfo(SearchFriendListInfoReqBo searchFriendListInfoReqBo) {
        return userService.searchFriendListInfo(searchFriendListInfoReqBo);
    }
}
