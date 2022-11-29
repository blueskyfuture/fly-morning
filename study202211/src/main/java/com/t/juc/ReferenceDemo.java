package com.t.juc;

import java.lang.ref.PhantomReference;
import java.lang.ref.Reference;
import java.lang.ref.ReferenceQueue;
import java.lang.ref.SoftReference;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class ReferenceDemo {

    public static void main(String[] args) {
        phantomReference();
    }

    private static void phantomReference() {
        MyObject myObject = new MyObject();
        ReferenceQueue<MyObject> referenceQueue = new ReferenceQueue<>();
        PhantomReference<MyObject> phantomReference = new PhantomReference<>(myObject,referenceQueue);

//        System.out.println(phantomReference.get());
        List<byte[]> list = new ArrayList<>();
        new Thread(() -> {
          System.out.println("currentThreadName:"+Thread.currentThread().getName());
            for (int i = 1; i <=12 ; i++) {
                list.add(new byte[1*1024*1024]);
                try {
                    TimeUnit.MILLISECONDS.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(phantomReference.get() + "\t" + "list add ok");
            }
        },"thread-a").start();

        new Thread(() -> {
          System.out.println("currentThreadName:"+Thread.currentThread().getName());
          while (true){
              Reference<? extends MyObject> poll = referenceQueue.poll();
              if(poll != null){
                  System.out.println("------phantomReference has add in queue----");
                  break;
              }
              try {
                  TimeUnit.MILLISECONDS.sleep(500);
              } catch (InterruptedException e) {
                  e.printStackTrace();
              }
          }
        },"thread-b").start();
    }

    /**
     * 虚引用
     */
    

    /**
     * 弱引用
     * 不管jvm内存空间够不够，都会回收该对象的内存空间
     */
    private static void weakReference() {
        WeakReference<MyObject> myObject = new WeakReference<>(new MyObject());
        System.out.println("gc before :" + myObject.get());

        System.gc();

        try {
            TimeUnit.MILLISECONDS.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("gc enough:" + myObject.get());

    }

    /**
     * 软引用
     * 内存空间不够时就进行回收
     */
    private static void softReference() {
        SoftReference<MyObject> myObject = new SoftReference<>(new MyObject());
        System.out.println("gc before :" + myObject.get());

        System.gc();

        try {
            TimeUnit.MILLISECONDS.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("gc enough:" + myObject.get());

        try{
            byte[] bytes = new byte[20 * 1024 * 1024];//20M
        }catch (Exception ex){
            ex.printStackTrace();
        }finally {
            System.out.println("gc not enough:" + myObject.get());
        }
    }

    /**
     * 强引用
     * 死活不放
     */
    private static void strongReference() {
        MyObject myObject = new MyObject();
        System.out.println("gc before :" + myObject);

        myObject = null;
        System.gc();

        try {
            TimeUnit.MILLISECONDS.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("gc after:" + myObject);
    }
}
