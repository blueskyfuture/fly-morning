package com.bluesky.tech.juc;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.TimeUnit;

public class ArrayBlockingQueueDemo {
    public static void main(String[] args) {

        test02();

    }



    private static void test02() {
        ArrayBlockingQueue<String> blockingQueue = new ArrayBlockingQueue<>(3);

        new Thread(()->{
            try {
                for (int i = 0; i < 10; i++) {
                    boolean offerRes = blockingQueue.offer(i + "" + i, 1, TimeUnit.SECONDS);
                    System.out.println(Thread.currentThread().getName() +"-> 插入【"+i+i+"】成功" + "--offerRes:" + offerRes);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        },"t1").start();

        new Thread(()->{
            try {
                for (int i = 0; i < 10; i++) {
                    TimeUnit.SECONDS.sleep(2);

                    String take = blockingQueue.take();
                    System.out.println(Thread.currentThread().getName() +"---> 取出【"+take+"】成功");
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        },"t2").start();
    }

    private static void test01() {
        ArrayBlockingQueue<String> blockingQueue = new ArrayBlockingQueue<>(3);

        new Thread(()->{
            try {
                for (int i = 0; i < 10; i++) {
                    boolean offerRes = blockingQueue.offer(i + "" + i, 1, TimeUnit.SECONDS);
                    System.out.println(Thread.currentThread().getName() +"-> 插入【"+i+i+"】成功" + "--offerRes:" + offerRes);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        },"t1").start();

        new Thread(()->{
            try {
                for (int i = 0; i < 10; i++) {
                    TimeUnit.SECONDS.sleep(2);

                    String take = blockingQueue.poll(1,TimeUnit.SECONDS);
                    System.out.println(Thread.currentThread().getName() +"---> 取出【"+take+"】成功");
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        },"t2").start();
    }
}
