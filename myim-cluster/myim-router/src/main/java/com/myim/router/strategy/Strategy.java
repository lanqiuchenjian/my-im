package com.myim.router.strategy;

import com.myim.router.bo.ImServerInfo;
import org.apache.catalina.util.ServerInfo;

import java.util.List;
import java.util.Map;

/**
 * @author cj
 */
public interface Strategy {
    ImServerInfo doStrategy(Map<String, List<ImServerInfo>> serverInfos, StrategyEnum strategyEnum);
}
