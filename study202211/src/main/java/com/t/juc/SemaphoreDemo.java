package com.t.juc;

import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

public class SemaphoreDemo {

    /**
     * 抢车位
     * @param args
     */
    public static void main(String[] args) {
        Semaphore semaphore = new Semaphore(3);
        for (int i = 0; i < 6; i++) {
            new Thread(() -> {
              System.out.println("currentThreadName:"+Thread.currentThread().getName());
              Car car = new Car(semaphore);
              car.getLoc();
            },"thread-" + i ).start();

        }

    }

    static class Car{
        Semaphore semaphore;
        Car(Semaphore semaphore){
            this.semaphore = semaphore;
        }

        public void getLoc(){
            System.out.println(Thread.currentThread().getName());
            try {
                semaphore.acquire(1);
                    System.out.println(Thread.currentThread().getName()  + "ok----");
                    TimeUnit.MILLISECONDS.sleep(10* 1000);
            } catch (Exception e) {
                e.printStackTrace();
            }finally {
                System.out.println(Thread.currentThread().getName() + " quit----");
                semaphore.release();
            }
        }
    }

}
