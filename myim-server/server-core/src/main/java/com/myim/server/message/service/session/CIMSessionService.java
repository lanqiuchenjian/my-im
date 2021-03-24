package com.myim.server.message.service.session;

import com.myim.server.model.CIMSession;

import java.util.List;

/*
 * 集群 session管理实现示例， 各位可以自行实现 AbstractSessionManager接口来实现自己的 session管理 服务器集群时
 * 须要将CIMSession 信息存入数据库或者redis中 等 第三方存储空间中，便于所有服务器都可以访问
 */
public interface CIMSessionService {

	void save(CIMSession session);

	Boolean isLocal(String account);

	String getHostName(String account);

	CIMSession get(String account);

	List<CIMSession> list();
	
	void remove(String account);

}
