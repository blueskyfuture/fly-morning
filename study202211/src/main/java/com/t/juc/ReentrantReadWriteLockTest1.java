package com.t.juc;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class ReentrantReadWriteLockTest1 {

    public static void main(String[] args) {
        ReentrantReadWriteLock  readWriteLock = new ReentrantReadWriteLock();
        ReentrantReadWriteLock.ReadLock readLock = readWriteLock.readLock();
        ReentrantReadWriteLock.WriteLock writeLock = readWriteLock.writeLock();
        sleep(2);
        new Thread(() -> {
          System.out.println("currentThreadName:"+Thread.currentThread().getName());
          readLock.lock();
          System.out.println("currentThreadName read lock suc:"+Thread.currentThread().getName());
            sleep(500);
          readLock.unlock();
            System.out.println("currentThreadName release read lock :"+Thread.currentThread().getName());
        },"thread-a").start();
        sleep(2);
        new Thread(() -> {
          System.out.println("currentThreadName:"+Thread.currentThread().getName());
            readLock.lock();
            System.out.println("currentThreadName read lock suc:"+Thread.currentThread().getName());
            sleep(1000);
            readLock.unlock();
            System.out.println("currentThreadName release read lock :"+Thread.currentThread().getName());
        },"thread-b").start();
        sleep(2);
        new Thread(() -> {
          System.out.println("currentThreadName:"+Thread.currentThread().getName());
            writeLock.lock();
            System.out.println("currentThreadName write lock suc:"+Thread.currentThread().getName());
            sleep(2);
            writeLock.unlock();
            System.out.println("currentThreadName release write lock :"+Thread.currentThread().getName());
        },"thread-c").start();
        sleep(2);
        new Thread(() -> {
            System.out.println("currentThreadName:"+Thread.currentThread().getName());
            readLock.lock();
            System.out.println("currentThreadName read lock suc:"+Thread.currentThread().getName());
            sleep(3);
            readLock.unlock();
            System.out.println("currentThreadName release read lock :"+Thread.currentThread().getName());
        },"thread-d").start();
        sleep(100);
    }

    private static void sleep(int secNum) {
        try {
            TimeUnit.SECONDS.sleep(secNum);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
