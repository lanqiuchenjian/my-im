package com.myim.server.future;

import org.junit.Test;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

public class FutureTest {
    @Test
    public void completableTest() throws ExecutionException, InterruptedException {
        CompletableFuture<String> completableFuture = new CompletableFuture<>();

        new Thread(() -> {
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            completableFuture.complete("xxx");
        }).start();

        System.out.println("waiting..............");
        String s = completableFuture.get();
        System.out.println(s);
    }
}
