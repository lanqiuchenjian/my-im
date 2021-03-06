package com.myim.server.config;

import com.myim.common.constant.Constant;
import com.myim.server.handler.CIMNioSocketAcceptor;
import com.myim.server.handler.CIMRequestHandler;
import com.myim.server.message.handler.BindHandler;
import com.myim.server.message.handler.ClientPushHandler;
import com.myim.server.message.handler.SessionClosedHandler;
import com.myim.server.model.CIMSession;
import com.myim.server.model.SentBody;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.event.ApplicationStartedEvent;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.Resource;
import java.util.HashMap;

@Configuration
public class CIMConfig implements CIMRequestHandler, ApplicationListener<ApplicationStartedEvent> {

	@Resource
	private ApplicationContext applicationContext;

	private final HashMap<String,CIMRequestHandler> appHandlerMap = new HashMap<>();


	@Bean(destroyMethod = "destroy")
	public CIMNioSocketAcceptor getNioSocketAcceptor(@Value("${cim.app.port}") int port,
													 @Value("${cim.websocket.port}") int websocketPort) {

		return new CIMNioSocketAcceptor.Builder()
				.setAppPort(port)
				.setWebsocketPort(websocketPort)
				.setOuterRequestHandler(this)
				.build();

	}

	@Override
	public void process(CIMSession session, SentBody body) {
		
        CIMRequestHandler handler = appHandlerMap.get(body.get("type"));
		
		if(handler == null) {return ;}
		
		handler.process(session, body);
		
	}
	/*
	 * springboot启动完成之后再启动cim服务的，避免服务正在重启时，客户端会立即开始连接导致意外异常发生.
	 */
	@Override
	public void onApplicationEvent(ApplicationStartedEvent applicationStartedEvent) {

		appHandlerMap.put(Constant.CLIENT_BIND, applicationContext.getBean(BindHandler.class));
		appHandlerMap.put(Constant.CLIENT_CLOSED, applicationContext.getBean(SessionClosedHandler.class));
		appHandlerMap.put(Constant.CLIENT_PUSH, applicationContext.getBean(ClientPushHandler.class));

		applicationContext.getBean(CIMNioSocketAcceptor.class).bind();
	}
}