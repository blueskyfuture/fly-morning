package com.jvm;


import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

public class Demo {

    public static void main(String[] args) {
        int i = 3;
        int j = 4;
        int result = i + j;
        System.out.println("result==" + result);
        new Thread(() -> {
            int a = 0;
            Map<Integer,String> map = new HashMap<>();
            while (a<10000) {
                a++;
                map.put(a,"a="+a);
                System.out.println("currentThreadName:" + Thread.currentThread().getName() + "===a==" + a);
                try {
                    TimeUnit.MILLISECONDS.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        },"thread-a").start();
    }

}
