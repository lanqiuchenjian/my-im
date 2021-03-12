package com.myim.server.message.service.user;

import com.myim.server.message.bo.req.ApplyFriendReqBo;
import com.myim.server.message.bo.resp.ApplyFriendRespBo;
import com.myim.server.model.ReplyBody;

public interface UserMsgService {
    ApplyFriendRespBo applySingleFriend(ApplyFriendReqBo applyFriendBo);
}
