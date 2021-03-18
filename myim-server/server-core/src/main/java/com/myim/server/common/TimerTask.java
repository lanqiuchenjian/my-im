package com.myim.server.common;

public interface TimerTask {

    void run(Timeout timeout) throws Exception;
}