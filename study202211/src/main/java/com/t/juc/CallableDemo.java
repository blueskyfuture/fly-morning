package com.t.juc;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;
import java.util.concurrent.TimeUnit;

class MyTask implements Callable<Integer>{

    @Override
    public Integer call() throws Exception {
        System.out.println("mytask come in...");
        try {
            TimeUnit.MILLISECONDS.sleep(4000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return 1024;
    }
}

public class CallableDemo {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        long begin = System.currentTimeMillis();
        FutureTask<Integer> future = new FutureTask<>(new MyTask());
        new Thread(future).start();
        System.out.println("main do task");
        Integer result = future.get();
        System.out.println("result:" + result);
        long end = System.currentTimeMillis();
        System.out.println("cost time:" + (end-begin));
    }


}
