package com.t.juc;

import java.util.Queue;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.TimeUnit;

public class ProvideAndConsumer {

    public static void main(String[] args) {
        ArrayBlockingQueue<String> queue = new ArrayBlockingQueue<String>(3);
        new Thread(() -> {
          System.out.println("currentThreadName:"+Thread.currentThread().getName());
          Provide provide = new Provide(queue);
          provide.provide();
        },"thread-a").start();
        new Thread(() -> {
          System.out.println("currentThreadName:"+Thread.currentThread().getName());
          Consumer consumer = new Consumer(queue);
          consumer.consumer();
        },"thread-b").start();
    }


    static class Provide{
        ArrayBlockingQueue<String> queue;
        Provide(ArrayBlockingQueue<String> queue){
            this.queue = queue;
        }

        public void provide(){
            for (int i = 0; i < 1000; i++) {
                try {
                    String str = "p_" + i;
                    //boolean result = queue.offer(str);
                    queue.put(str);
                    //System.out.println("p--" + str + ";result:" + result);
                    System.out.println("p--" + str);
                    TimeUnit.MILLISECONDS.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    static class Consumer{
        ArrayBlockingQueue<String> queue;
        Consumer(ArrayBlockingQueue<String> queue){
            this.queue = queue;
        }

        public void consumer(){
            for (int i = 0; i < 1000; i++) {
                String str = queue.poll();
                System.out.println("consumer--" +  str);
                try {
                    TimeUnit.MILLISECONDS.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }



}
