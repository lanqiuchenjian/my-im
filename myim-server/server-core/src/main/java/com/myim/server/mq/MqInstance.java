package com.myim.server.mq;

import com.myim.server.message.bo.req.chat.single.SingleMessageReqBo;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class MqInstance {
    @Autowired
    private RocketMQTemplate rocketMQTemplate;

    @Value("${myim.rocketmq.msg-topic}")
    private String springTopic;

    public boolean sendMsg(SingleMessageReqBo msg, String ipTag) {
        try{
            rocketMQTemplate.convertAndSend(springTopic + ":" + ipTag, msg);
        }catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }
}
