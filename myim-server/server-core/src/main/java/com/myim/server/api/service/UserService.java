/*
 * Copyright 2013-2019 Xia Jun(3979434@qq.com).
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 ***************************************************************************************
 *                                                                                     *
 *                        Website : http://www.farsunset.com                           *
 *                                                                                     *
 ***************************************************************************************
 */
package com.myim.server.api.service;

import com.myim.server.api.dto.req.UserLoginReqDto;
import com.myim.server.api.dto.req.UserRegisterReqDto;
import com.myim.server.api.dto.resp.UserLoginRespDto;
import com.myim.server.api.dto.resp.UserRegisterRespDto;
import com.myim.server.message.bo.req.user.ApplyFriendReqBo;
import com.myim.server.message.bo.req.user.FriendInfoReqBo;
import com.myim.server.message.bo.req.user.FriendListInfoReqBo;
import com.myim.server.message.bo.req.user.SearchFriendListInfoReqBo;
import com.myim.server.message.bo.resp.user.ApplyFriendRespBo;
import com.myim.server.message.bo.resp.user.FriendInfoRespBo;
import com.myim.server.message.bo.resp.user.FriendListInfoRespBo;

public interface UserService {
	/*************************http*************************************/
	UserRegisterRespDto register(UserRegisterReqDto registerReqDto);

	UserLoginRespDto login(UserLoginReqDto userLoginReqDto);

	/*************************tcp*************************************/
	ApplyFriendRespBo applyFriend(ApplyFriendReqBo applyFriendBo);

	FriendInfoRespBo friendInfo(FriendInfoReqBo friendInfoReqBo);

	FriendListInfoRespBo friendListInfo(FriendListInfoReqBo friendListInfoReqBo);

	FriendListInfoRespBo searchFriendListInfo(SearchFriendListInfoReqBo searchFriendListInfoReqBo);
}
