package com.myim.server.common;

import com.myim.server.model.CIMSession;
import com.myim.server.model.Message;
import com.myim.server.model.SentBody;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * DefaultFuture.
 */
public class DefaultFuture extends CompletableFuture<Object> {
    private static final Map<String, CIMSession> CHANNELS = new ConcurrentHashMap<>();

    private static final Map<String, DefaultFuture> FUTURES = new ConcurrentHashMap<>();

    static {
        Thread th = new Thread(new RemotingInvocationTimeoutScan(), "DubboResponseTimeoutScanTimer");
        th.setDaemon(true);
        th.start();
    }

    // invoke key.
    private final String key;
    private final CIMSession channel;
    private final Message request;
    private final int timeout;
    private final Lock lock = new ReentrantLock();
    private final Condition done = lock.newCondition();
    private final long start = System.currentTimeMillis();
    private volatile long sent;
    private volatile SentBody response;
//    private volatile ResponseCallback callback;

    public DefaultFuture(CIMSession channel, Message request, int timeout) {
        this.channel = channel;
        this.request = request;
        this.key = request.getKey();
        System.out.println("++++++++++++" + request.getKey() + "++++++++++++" + Thread.currentThread());
        this.timeout = timeout;
        // put into waiting map.
        FUTURES.put(key, this);
        CHANNELS.put(key, channel);
    }

    public static DefaultFuture getFuture(String key) {
        return FUTURES.get(key);
    }

    public static boolean hasFuture(CIMSession channel) {
        return CHANNELS.containsValue(channel);
    }

    public static void sent(CIMSession channel, Message request) {
        DefaultFuture future = FUTURES.get(request.getKey());
        System.out.println(request.getId());
        if (future != null) {
            System.out.println("key................");
            future.doSent();
        }
    }

    public static void received(CIMSession channel, SentBody response) {
        try {
            DefaultFuture future = FUTURES.remove(response.getKey());
            if (future != null) {
                future.doReceived(response);
            } else {
                System.out.println("The timeout response finally returned at "
                        + (new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS").format(new Date()))
                        + ", response " + response
                        + (channel == null ? "" : ", channel: " + channel.getLocation()
                        + " -> " + channel.getRemoteAddress()));
            }
        } finally {
            CHANNELS.remove(response.getKey());
        }
    }

    public Object get() {
        return get(timeout);
    }

    public Object get(int timeout)  {
        if (timeout <= 0) {
            timeout = 1000 * 10;
        }
        if (!isDone()) {
            long start = System.currentTimeMillis();
            lock.lock();
            try {
                while (!isDone()) {
                    done.await(timeout, TimeUnit.MILLISECONDS);
                    if (isDone() || System.currentTimeMillis() - start > timeout) {
                        break;
                    }
                }
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            } finally {
                lock.unlock();
            }

            if (!isDone()) {
//                throw new TimeoutException(sent > 0, channel, getTimeoutMessage(false));
            }
        }
        return returnFromResponse();
    }

    public void cancel() {
        SentBody errorResult = new SentBody();
        errorResult.setKey(key);
        errorResult.put("errorMsg", "request future has been canceled.");
        response = errorResult;
        FUTURES.remove(key);
        CHANNELS.remove(key);
    }

    public boolean isDone() {
        return response != null;
    }

    private Object returnFromResponse(){
        SentBody res = response;
        if (res == null) {
            throw new IllegalStateException("response cannot be null");
        }
        if (res.get("status").equals("success")) {
            return res.get("reply");
        }
        if (res.get("status").equals("client_timeout") || res.get("status").equals("server_timeout")) {
//            throw new TimeoutException(res.getStatus() == Response.SERVER_TIMEOUT, channel, res.getErrorMessage());
            System.out.println("timeout");
        }
//        throw new RemotingException(channel, res.getErrorMessage());
        throw new RuntimeException(res.get("errorMsg"));
    }

    private String getKey() {
        return key;
    }

    private CIMSession getChannel() {
        return channel;
    }

    private boolean isSent() {
        return sent > 0;
    }

    public Message getRequest() {
        return request;
    }

    private int getTimeout() {
        return timeout;
    }

    private long getStartTimestamp() {
        return start;
    }

    private void doSent() {
        sent = System.currentTimeMillis();
    }

    private void doReceived(SentBody res) {
        lock.lock();
        try {
            response = res;
            done.signal();
        } finally {
            lock.unlock();
        }
//        if (callback != null) {
//            invokeCallback(callback);
//        }
    }

    private String getTimeoutMessage(boolean scan) {
        long nowTimestamp = System.currentTimeMillis();
        return (sent > 0 ? "Waiting server-side response timeout" : "Sending request timeout in client-side")
                + (scan ? " by scan timer" : "") + ". start time: "
                + (new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS").format(new Date(start))) + ", end time: "
                + (new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS").format(new Date())) + ","
                + (sent > 0 ? " client elapsed: " + (sent - start)
                + " ms, server elapsed: " + (nowTimestamp - sent)
                : " elapsed: " + (nowTimestamp - start)) + " ms, timeout: "
                + timeout + " ms, request: " + request + ", channel: " + channel.getLocation()
                + " -> " + channel.getRemoteAddress();
    }

    private static class RemotingInvocationTimeoutScan implements Runnable {
        public void run() {
            while (true) {
                try {
                    for (DefaultFuture future : FUTURES.values()) {
                        if (future == null || future.isDone()) {
                            continue;
                        }
                        if (System.currentTimeMillis() - future.getStartTimestamp() > future.getTimeout()) {
                            // create exception response.
                            SentBody timeoutResponse = new SentBody();
                            timeoutResponse.setKey(future.getKey());
                            // set timeout status.
                            timeoutResponse.put("status", future.isSent() ? "client_timeout" : "server_timeout");
                            timeoutResponse.put("errorMsg", future.getTimeoutMessage(true));
                            // handle response.
                            DefaultFuture.received(future.getChannel(), timeoutResponse);
                        }
                    }
                    Thread.sleep(30);
                } catch (Throwable e) {
                    System.out.println("Exception when scan the timeout invocation of remoting." + e);
                }
            }
        }
    }
}
