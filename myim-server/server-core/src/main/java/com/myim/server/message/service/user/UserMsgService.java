package com.myim.server.message.service.user;

import com.myim.server.message.bo.req.ApplyFriendReqBo;
import com.myim.server.message.bo.req.FriendInfoReqBo;
import com.myim.server.message.bo.req.FriendListInfoReqBo;
import com.myim.server.message.bo.resp.ApplyFriendRespBo;
import com.myim.server.message.bo.resp.FriendInfoRespBo;
import com.myim.server.message.bo.resp.FriendListInfoRespBo;
import com.myim.server.model.ReplyBody;

import java.util.List;

public interface UserMsgService {
    ApplyFriendRespBo applySingleFriend(ApplyFriendReqBo applyFriendBo);

    FriendInfoRespBo friendInfo(FriendInfoReqBo friendInfoReqBo);

    FriendListInfoRespBo friendListInfo(FriendListInfoReqBo friendListInfoReqBo);
}
