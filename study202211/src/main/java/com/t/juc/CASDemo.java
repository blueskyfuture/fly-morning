package com.t.juc;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

public class CASDemo {


    public static void main(String[] args) {
        AtomicInteger i = new AtomicInteger();
        new Thread(() -> {
            System.out.println("currentThreadName:" + Thread.currentThread().getName());
            while (true) {
                int i1 = i.incrementAndGet();
                try {
                    TimeUnit.MILLISECONDS.sleep(200);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("currentThreadName:" + Thread.currentThread().getName() + ";i1==" + i1);
            }
        },"thread-a").start();

        new Thread(() -> {
          System.out.println("currentThreadName:"+Thread.currentThread().getName());
          while (true) {
              int i2 = i.incrementAndGet();
              try {
                  TimeUnit.MILLISECONDS.sleep(200);
              } catch (InterruptedException e) {
                  e.printStackTrace();
              }
              System.out.println("currentThreadName:" + Thread.currentThread().getName() + ";i2==" + i2);
          }
        },"thread-b").start();

    }

}
