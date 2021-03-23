package com.myim.server.mq;

import com.myim.server.BaseTest;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class RocketMqTest extends BaseTest {
    @Autowired
    private MqInstance mqInstance;

    @Test
    public void producerStringTest() throws InterruptedException {
        boolean ni_hao_a = mqInstance.sendMsg(null, null);
        System.out.println(ni_hao_a);
        Thread.sleep(1000 * 500);
    }
}
