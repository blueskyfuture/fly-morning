package com.t.juc;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 现场操作资源类，依次打印10次A，打印5次B，打印10次C；循环5轮
 */
public class ReentranceLockDemo {

    public static void main(String[] args) {
        Lock lock = new ReentrantLock();
        Worker worker = new Worker();
        new Thread(() -> {
            System.out.println("currentThreadName:"+Thread.currentThread().getName());
            for (int i = 0; i < 5; i++) {
                worker.printA();
            }

            System.out.println("currentThreadName end:"+Thread.currentThread().getName());
        },"thread-a").start();

        new Thread(() -> {
            System.out.println("currentThreadName:"+Thread.currentThread().getName());
            for (int i = 0; i < 5; i++) {
                worker.printB();
            }
            System.out.println("currentThreadName end:"+Thread.currentThread().getName());
        },"thread-a").start();

        new Thread(() -> {
            System.out.println("currentThreadName:"+Thread.currentThread().getName());
            for (int i = 0; i < 5; i++) {
                worker.printC();
            }
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
class Worker{
    Lock lock = new ReentrantLock();
    Condition conditionA = lock.newCondition();
    Condition conditionB = lock.newCondition();
    Condition conditionC = lock.newCondition();
    int flag = 1;

    public void printA(){
        lock.lock();
        try{
            while (flag != 1) {
                conditionA.await();
            }
            for (int i = 1; i <= 10; i++) {
                System.out.println("A_" + i);
            }
            flag = 2;
            conditionB.signal();
        }catch(Exception ex){
            ex.printStackTrace();
        }finally{
            lock.unlock();
        }

    }

    public void printB(){
        lock.lock();
        try{
            while (flag != 2) {
                conditionB.await();
            }
            for (int i = 1; i <= 5; i++) {
                System.out.println("B_" + i);
            }
            flag = 3;
            conditionC.signal();
        }catch(Exception ex){
            ex.printStackTrace();
        }finally{
            lock.unlock();
        }
    }

    public void printC(){
        lock.lock();
        try{
            while (flag != 3){
                conditionC.await();
            }
            for (int i = 1; i <= 10; i++) {
                System.out.println("C_" + i);
            }
            flag = 1;
            conditionA.signal();
        }catch(Exception ex){
            ex.printStackTrace();
        }finally{
            lock.unlock();
        }
    }


}
