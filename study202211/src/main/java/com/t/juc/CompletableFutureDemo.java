package com.t.juc;

import org.junit.Assert;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CountDownLatch;

public class CompletableFutureDemo {
    public void testThenComposeAsync() throws Exception {
        System.out.println("begin===");
        // Composing CompletableFuture is complete
        CompletableFuture<String> cf1 = CompletableFuture.completedFuture("one");

        // Composing function returns a CompletableFuture executed asynchronously
        CountDownLatch cdl = new CountDownLatch(1);
        CompletableFuture<String> cf2 = cf1.thenCompose(str -> CompletableFuture.supplyAsync(() -> {
            while (true) {
                System.out.println("cf2 while...");
                try {
                    Thread.sleep(1000);
                    cdl.await();
                    break;
                }
                catch (InterruptedException e) {
                }
            }
            return str + ", two";
        }));

        // Ensure returned CompletableFuture completes after call to thenCompose
        // This guarantees that any premature internal completion will be
        // detected
        System.out.println("begin sleep===");
        Thread.sleep(5000);
        System.out.println("end sleep===");
        cdl.countDown();

        String val = cf2.get();
        System.out.println("val=="+ val);
        Assert.assertNotNull(val);
        Assert.assertEquals(val, "one, two");
    }

    public static void main(String[] args)throws Exception {
        CompletableFutureDemo demo = new CompletableFutureDemo();
        demo.testThenComposeAsync();

    }
}
