package com.jvm;

import java.util.concurrent.TimeUnit;

public class SynchronizedDemo {
    private int a = 0;

    public synchronized void writer() {     // 1
        a++;                                // 2
        System.out.println("writer...a=="+a);
    }                                       // 3

    public synchronized void reader() {    // 4
        int i = a;                         // 5
        System.out.println("reader a==" + a);
        System.out.println("reader i==" + i);
    }

    public static void main(String[] args) {
        SynchronizedDemo demo = new SynchronizedDemo();
        new Thread(() -> {
          System.out.println("currentThreadName:"+Thread.currentThread().getName());
          try {
              TimeUnit.MILLISECONDS.sleep(1000);
          } catch (InterruptedException e) {
              e.printStackTrace();
          }
          demo.writer();
            System.out.println("currentThreadName end:"+Thread.currentThread().getName());
        },"thread-a").start();
        new Thread(() -> {
          System.out.println("currentThreadName:"+Thread.currentThread().getName());
          try {
              TimeUnit.MILLISECONDS.sleep(1500);
          } catch (InterruptedException e) {
              e.printStackTrace();
          }
          demo.reader();
            System.out.println("currentThreadName end:"+Thread.currentThread().getName());
        },"thread-b").start();
        try {
            TimeUnit.MILLISECONDS.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
