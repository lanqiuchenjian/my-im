package com.myim.server.message.service.user;

import com.myim.server.message.bo.req.user.ApplyFriendReqBo;
import com.myim.server.message.bo.req.user.FriendInfoReqBo;
import com.myim.server.message.bo.req.user.FriendListInfoReqBo;
import com.myim.server.message.bo.req.user.SearchFriendListInfoReqBo;
import com.myim.server.message.bo.resp.user.ApplyFriendRespBo;
import com.myim.server.message.bo.resp.user.FriendInfoRespBo;
import com.myim.server.message.bo.resp.user.FriendListInfoRespBo;

public interface UserMsgService {
    ApplyFriendRespBo applySingleFriend(ApplyFriendReqBo applyFriendBo);

    FriendInfoRespBo friendInfo(FriendInfoReqBo friendInfoReqBo);

    FriendListInfoRespBo friendListInfo(FriendListInfoReqBo friendListInfoReqBo);

    FriendListInfoRespBo searchFriendListInfo(SearchFriendListInfoReqBo searchFriendListInfoReqBo);
}
