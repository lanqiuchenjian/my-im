package com.myim.server.common;

import java.util.Set;
import java.util.concurrent.TimeUnit;

public interface Timer {

    Timeout newTimeout(TimerTask task, long delay, TimeUnit unit);

    Set<Timeout> stop();

    boolean isStop();
}