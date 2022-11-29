package com.t.juc;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;

public class CompletableFutureUseDemo {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        future3();
    }

    private static void future3() {
        ExecutorService executorService = Executors.newFixedThreadPool(3);
        try{
            CompletableFuture.supplyAsync(()->{
                        System.out.println(Thread.currentThread().getName() + "----come in");
                        int result = ThreadLocalRandom.current().nextInt(10);
                        try {
                            TimeUnit.MILLISECONDS.sleep(3000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        System.out.println("----result:" + result);
                        if( result > 2 ){
                            int i = 10 / 0;
                        }
                        return result;
                    }, executorService
            ).whenComplete((v,e) -> {
                if(e == null){
                    System.out.println("-----future finish:" + v);
                }
            }).exceptionally(e -> {
                System.out.println("exception：" + e.getCause() + "\t" + e.getMessage());
                return null;
            });

            System.out.println(Thread.currentThread().getName() + " doing other thing");
        }catch(Exception ex){
            ex.printStackTrace();
        }finally{
            executorService.shutdown();
        }
    }

    private static void future2() {
        CompletableFuture.supplyAsync(()->{
                    System.out.println(Thread.currentThread().getName() + "----come in");
                    int result = ThreadLocalRandom.current().nextInt(10);
                    try {
                        TimeUnit.MILLISECONDS.sleep(3000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println("----1秒钟后出结果：" + result);
                    return result;
                }
        ).whenComplete((v,e) -> {
            if(e == null){
                System.out.println("-----计算完成：" + v);
            }
        }).exceptionally(e -> {
            System.out.println("异常情况：" + e.getCause() + "\t" + e.getMessage());
           return null;
        });

        System.out.println(Thread.currentThread().getName() + "线程先去忙其他任务了");

        //避免main线程退出
        try {
            TimeUnit.MILLISECONDS.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private static void future1() throws InterruptedException, ExecutionException {
        CompletableFuture<Integer> completableFuture = CompletableFuture.supplyAsync(()->{
                    System.out.println(Thread.currentThread().getName() + "----come in");
                    int result = ThreadLocalRandom.current().nextInt(10);
                    try {
                        TimeUnit.MILLISECONDS.sleep(3000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println("----1秒钟后出结果：" + result);
                    return result;
                }
        );
        System.out.println(Thread.currentThread().getName() + "线程先去忙其他任务了");
        System.out.println(completableFuture.get());
    }

}
