package com.myim.router.strategy;

import lombok.Getter;

/**
 * @author cj
 */
@Getter
public enum StrategyEnum {
    RANDOM, ROUND_ROBIN, HASH, CONSISTENCE_HASH
}
