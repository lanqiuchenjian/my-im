package com.myim.router.strategy;

import com.myim.router.bo.ImServerInfo;
import org.apache.catalina.util.ServerInfo;

import java.util.Map;

/**
 * @author cj
 */
public interface Strategy {
    ServerInfo doStrategy(Map<String, ImServerInfo> serverInfos, StrategyEnum strategyEnum);
}
