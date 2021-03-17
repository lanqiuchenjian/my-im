package com.myim.server.common;

import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author cj
 */
public class ResponseFuture {
    private static Map<String, CompletableFuture<Object>> map = new ConcurrentHashMap<>();

    private ResponseFuture(){}

    public static void putFutureTask(String key, CompletableFuture<Object> task) {
        map.put(key, task);
    }

    public static CompletableFuture<Object> getFutureTask(String key) {
        return map.remove(key);
    }

    //定时清除
}
