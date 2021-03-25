package com.myim.server.mq;

import com.google.gson.Gson;
import com.myim.server.api.service.ChatService;
import com.myim.server.message.bo.req.chat.single.SingleMessageReqBo;
import org.apache.rocketmq.common.message.MessageExt;
import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RocketMQMessageListener(topic = "${myim.rocketmq.msg-topic}", consumerGroup = "myim-consumer-group1",  selectorExpression = "server127")
public class MqListenr implements RocketMQListener<MessageExt> {
    @Autowired
    private ChatService chatService;

    @Override
    public void onMessage(MessageExt message) {
        SingleMessageReqBo msg = new Gson().fromJson(new String(message.getBody()), SingleMessageReqBo.class);
        System.out.printf("------- ConsumerWithReplyBytes received: %s \n", msg);
        new Thread(() -> chatService.sendSingleMessage(msg)).start();
    }
}