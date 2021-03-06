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
package com.myim.server.message.service.session.impl;


import com.google.gson.Gson;
import com.myim.server.message.repository.SessionRepository;
import com.myim.server.message.service.session.CIMSessionService;
import com.myim.server.redis.RedisDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import com.myim.server.handler.CIMNioSocketAcceptor;
import com.myim.server.model.CIMSession;

import javax.annotation.Resource;
import java.util.List;


@Service
public class CIMSessionServiceImpl implements CIMSessionService {
	@Autowired
	private RedisDao redisDao;

 	@Resource
 	private CIMNioSocketAcceptor nioSocketAcceptor;

 	@Resource
	private SessionRepository sessionRepository;

	@Override
	public void save(CIMSession session) {
		sessionRepository.save(session);
		//机器挂掉，新连接在某台机器上需要覆盖原来的地址
		redisDao.setKey(session.getAccount(), session.getHost());
	}

	@Override
	public Boolean isLocal(String account) {
		return sessionRepository.get(account) != null;
	}

	@Override
	public String getHostName(String account) {
		return (String)redisDao.getKey(account);
	}

	/*
	 *
	 * @param account 用户id
	 * @return
	 */
	@Override
	public CIMSession get(String account) {
		 CIMSession session = sessionRepository.get(account);
		 if (session != null){
			 session.setSession(nioSocketAcceptor.getManagedSession(session.getNid()));
		 }

		 return session;
	}
 
	@Override
	public void remove(String account) {
		sessionRepository.remove(account);
		redisDao.deleteKey(account);
	}

	@Override
	public List<CIMSession> list() {
		return sessionRepository.findAll();
	}
}
