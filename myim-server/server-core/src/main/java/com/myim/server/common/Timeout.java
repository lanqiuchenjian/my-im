package com.myim.server.common;

public interface Timeout {
    Timer timer();

    TimerTask task();

    boolean isExpired();

    boolean isCancelled();

    boolean cancel();
}