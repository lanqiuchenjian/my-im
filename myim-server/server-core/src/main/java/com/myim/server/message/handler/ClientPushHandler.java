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
package com.myim.server.message.handler;

import com.myim.server.handler.CIMRequestHandler;
import com.myim.server.model.CIMSession;
import com.myim.server.model.Message;
import com.myim.server.model.ReplyBody;
import com.myim.server.model.SentBody;
import com.myim.server.message.push.CIMMessagePusher;
import com.myim.server.message.service.CIMSessionService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;
import java.util.Objects;


/*
 * 客户端推送消息
 */

@Component
public class ClientPushHandler implements CIMRequestHandler {

	private final Logger logger = LoggerFactory.getLogger(ClientPushHandler.class);

	@Resource
	private CIMSessionService cimSessionService;

	@Resource
	private CIMMessagePusher defaultMessagePusher;
	
	@Override
	public void process(CIMSession newSession, SentBody body) {

		ReplyBody reply = new ReplyBody();
		reply.setKey(body.getKey());
		reply.setCode(HttpStatus.OK.value());
		reply.setTimestamp(System.currentTimeMillis());

		//实现broadcast功能
		try {

			String content = body.get("content");

			// 获取所有的连接
			String fromAccount = body.get("fromAccount");
			String action = body.get("action");

			CIMSession fromSession = cimSessionService.get(fromAccount);
			List<CIMSession> list = cimSessionService.list();
			//刪除自己发送的消息
			list.remove(fromSession);

			list.forEach(s -> {
				Message m = new Message();

				m.setAction(action);
				m.setContent(content);
				m.setSender(fromAccount);
				m.setReceiver(s.getAccount());

				defaultMessagePusher.push(m);
			});
		} catch (Exception exception) {
			reply.setCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
			logger.error("Bind has error",exception);
		}
	 
		newSession.write(reply);
	}

	private boolean fromOtherDevice(CIMSession oldSession ,CIMSession newSession) {
			
			return !Objects.equals(oldSession.getDeviceId(), newSession.getDeviceId());
	}
}
