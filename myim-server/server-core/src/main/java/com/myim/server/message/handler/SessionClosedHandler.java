package com.myim.server.message.handler;

/*
 * 断开连接，清除session
 * 
 */

import com.myim.server.handler.CIMRequestHandler;
import com.myim.server.message.service.session.CIMSessionService;
import org.springframework.stereotype.Component;
import com.myim.server.constant.CIMConstant;
import com.myim.server.model.CIMSession;
import com.myim.server.model.SentBody;

import javax.annotation.Resource;
import java.util.Objects;

@Component
public class SessionClosedHandler implements CIMRequestHandler {

	@Resource
	private CIMSessionService cimSessionService;
	
	@Override
	public void process(CIMSession ios, SentBody message) {
		Object quietly = ios.getAttribute(CIMConstant.KEY_QUIETLY_CLOSE);
		if (Objects.equals(quietly, true)) {
			return;
		}

		Object account = ios.getAttribute(CIMConstant.KEY_ACCOUNT);
		if (account == null) {
			return;
		}

		cimSessionService.remove(account.toString());
	}

}
