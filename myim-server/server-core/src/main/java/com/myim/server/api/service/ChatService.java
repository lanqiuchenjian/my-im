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

import com.myim.server.message.bo.req.chat.single.SingleMessageReqBo;
import com.myim.server.message.bo.req.chat.single.SingleWebrtcMessageReqBo;
import com.myim.server.message.bo.req.chat.single.SingleWebrtcReqBo;
import com.myim.server.message.bo.resp.chat.SingleMessageRespBo;
import com.myim.server.message.bo.resp.chat.SingleWebrtcMessageRespBo;
import com.myim.server.message.bo.resp.chat.SingleWebrtcRespBo;

import java.io.IOException;

public interface ChatService {
	/*************************tcp*************************************/
	//发送单聊消息
	SingleMessageRespBo sendSingleMessage(SingleMessageReqBo singleMessageReqBo) throws IOException;


	SingleWebrtcRespBo webrtcCreate(SingleWebrtcReqBo singleWebrtcReqBo);

	SingleWebrtcMessageRespBo webrtcMessage(SingleWebrtcMessageReqBo singleWebrtcMessageReqBo);
}
