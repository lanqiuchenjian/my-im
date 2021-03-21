package com.myim.router.strategy.impl;

import com.myim.router.bo.ImServerInfo;
import com.myim.router.strategy.Strategy;
import com.myim.router.strategy.StrategyEnum;
import org.apache.catalina.util.ServerInfo;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * @author cj
 */
@Component(value = "HASH")
public class HashingImpl implements Strategy {
    @Override
    public ServerInfo doStrategy(Map<String, ImServerInfo> serverInfos, StrategyEnum strategyEnum) {
        return null;
    }
}
