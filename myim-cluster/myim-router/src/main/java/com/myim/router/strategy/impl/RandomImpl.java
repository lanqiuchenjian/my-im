package com.myim.router.strategy.impl;

import com.myim.router.bo.ImServerInfo;
import com.myim.router.strategy.Strategy;
import com.myim.router.strategy.StrategyEnum;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author cj
 */
@Component(value = "RANDOM")
public class RandomImpl implements Strategy {
    AtomicInteger i = new AtomicInteger(0);
    @Override
    public ImServerInfo doStrategy(Map<String, List<ImServerInfo>> serverInfos, StrategyEnum strategyEnum) {
        ImServerInfo imServerInfo = serverInfos.get("imServer").get(i.getAndIncrement() % serverInfos.size());
        System.out.println("i................" + i);
        System.out.println(serverInfos.size());
        return imServerInfo;
    }
}
