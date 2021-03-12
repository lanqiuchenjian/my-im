package com.myim.server.message;

import java.lang.reflect.Method;
import java.util.concurrent.ConcurrentHashMap;

public class MsgServiceBeanFactory {
    private static ConcurrentHashMap<String, Object> msgServiceBeanFactroy = new ConcurrentHashMap<>();
    private static ConcurrentHashMap<String, Method> msgMethodBeanFactroy = new ConcurrentHashMap<>();

    private MsgServiceBeanFactory(){}

    public static ConcurrentHashMap<String, Object> getMsgServiceBeanFactory() {
        return msgServiceBeanFactroy;
    }

    public static Object getMsgServiceBean(String key) {
        return msgServiceBeanFactroy.get(key);
    }

    public static void setMsgServiceBean(String key, Object bean) {
        msgServiceBeanFactroy.put(key, bean);
    }

    public static Object getMsgMethodBean(String key) {
        return msgMethodBeanFactroy.get(key);
    }

    public static void setMsgMethodBean(String key, Method bean) {
        msgMethodBeanFactroy.put(key, bean);
    }
}
