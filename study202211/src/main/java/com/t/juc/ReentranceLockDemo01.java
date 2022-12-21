package com.t.juc;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 现场操作资源类，依次打印10次A，打印5次B，打印10次C；循环5轮
 */
public class ReentranceLockDemo01 {

    public static void main(String[] args) {
        Lock lock = new ReentrantLock();
        Worker01 worker = new Worker01(lock);
        new Thread(() -> {
          System.out.println("currentThreadName:"+Thread.currentThread().getName());
          worker.printA();
            System.out.println("currentThreadName end:"+Thread.currentThread().getName());
        },"thread-a").start();

        new Thread(() -> {
            System.out.println("currentThreadName:"+Thread.currentThread().getName());
            worker.printB();
            System.out.println("currentThreadName end:"+Thread.currentThread().getName());
        },"thread-a").start();

        new Thread(() -> {
            System.out.println("currentThreadName:"+Thread.currentThread().getName());
            worker.printC();
            System.out.println("currentThreadName end:"+Thread.currentThread().getName());
        },"thread-a").start();
        try {
            TimeUnit.MILLISECONDS.sleep(50*1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


}

/**
 * 资源类
 */
class Worker01{
    Lock lock;
    Condition conditionA;
    Condition conditionB;
    Condition conditionC;
    int flag = 1;

    Worker01(Lock lock){
        this.lock = lock;
         conditionA = lock.newCondition();
         conditionB = lock.newCondition();
         conditionC = lock.newCondition();
    }

    public void printA(){
        while (flag == 1){
            for (int i = 1; i <= 10; i++) {
                System.out.println("A_" + i);
            }
            flag = 2;
            try {
                conditionA.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            conditionB.signal();
        }
    }

    public void printB() {
        while (flag == 2) {
            for (int i = 1; i <= 5; i++) {
                System.out.println("B_" + i);
            }
            flag = 3;
            try {
                conditionB.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            conditionC.signal();
        }
    }

        public void printC(){
            while (flag == 3){
                for (int i = 1; i <= 10; i++) {
                    System.out.println("C_" + i);
                }
                flag = 1;
                try {
                    conditionC.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                conditionA.signal();
            }
    }


}
