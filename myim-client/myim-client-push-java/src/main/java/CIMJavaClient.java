import com.myim.client.CIMEventBroadcastReceiver;
import com.myim.client.CIMEventListener;
import com.myim.client.constant.CIMConstant;
import com.myim.client.push.CIMPushManager;
import com.myim.client.model.Message;
import com.myim.client.model.ReplyBody;
import com.myim.client.model.SentBody;

import java.util.Scanner;

public class CIMJavaClient implements CIMEventListener {
 	public static void startup() {
		/*
		 * 第一步 设置运行时参数
		 */
		CIMPushManager.setClientVersion("1.0.0");


		/*
		 * 第二步 设置全局的事件监听器
		 */
		CIMEventBroadcastReceiver.getInstance().setGlobalCIMEventListener(new CIMJavaClient());

		/*
		 * 第三步 连接到服务器
		 */
		CIMPushManager.connect("127.0.0.1", 23456);

		while (true) {
			Scanner scanner = new Scanner(System.in);
			while (scanner.hasNext()) {
				SentBody sentBody = new SentBody();

				sentBody.setKey(CIMConstant.RequestKey.CLIENT_PUSH);
				sentBody.put("fromAccount", "10000");
				String next = scanner.next();
				String[] split = next.split(":");
				sentBody.put("action", split[0]);
				System.out.println(next);
				sentBody.put("content", split[1]);

				CIMPushManager.sendRequest(sentBody);
			}
		}
		 
	}

	@Override
	public void onConnectionClosed() {
		System.out.println("onConnectionClosed");
		/*
		 * 在此可以将事件分发到各个监听了CIMEventBroadcastReceiver的地方
		 * 第一步 连接到服务器 在需要监听事件的类调用CIMListenerManager.registerMessageListener(listener);
		 * 第二部 在此调用CIMListenerManager.notifyOnConnectionClosed()
		 */
	}



	@Override
	public void onConnectFailed() {
		System.out.println("onConnectionFailed");
		/*
		 * 在此可以将事件分发到各个监听了CIMEventBroadcastReceiver的地方
		 * 第一步 连接到服务器 在需要监听事件的类调用CIMListenerManager.registerMessageListener(listener);
		 * 第二部 在此调用CIMListenerManager.notifyOnConnectionFailed(e)
		 */
	}

	@Override
	public void onConnectFinished(boolean hasAutoBind) {
		System.out.println("onConnectFinished");
		if(!hasAutoBind){
			CIMPushManager.bindAccount("10000");
		}
		/*
		 * 在此可以将事件分发到各个监听了CIMEventBroadcastReceiver的地方
		 * 第一步 连接到服务器 在需要监听事件的类调用CIMListenerManager.registerMessageListener(listener);
		 * 第二部 在此调用CIMListenerManager.onConnectFinished(hasAutoBind)
		 */
	}

	@Override
	public void onMessageReceived(Message message) {
		System.out.println(message.toString());
		/*
		 * 在此可以将事件分发到各个监听了CIMEventBroadcastReceiver的地方
		 * 第一步 连接到服务器 在需要监听事件的类调用CIMListenerManager.registerMessageListener(listener);
		 * 第二部 在此调用CIMListenerManager.notifyOnMessageReceived(message)
		 */
	}

	
	@Override
	public void onReplyReceived(ReplyBody replybody) {
		System.out.println(replybody.toString());
		/*
		 * 在此可以将事件分发到各个监听了CIMEventBroadcastReceiver的地方
		 * 第一步 连接到服务器 在需要监听事件的类调用CIMListenerManager.registerMessageListener(listener);
		 * 第二部 在此调用CIMListenerManager.notifyOnReplyReceived(replybody)
		 */
	}

	@Override
	public void onSendFinished(SentBody sentBody) {

	}
	@Override
	public int getEventDispatchOrder() {
		return 1;
	}

	public static void main(String[] a){
		startup();
	}




}
