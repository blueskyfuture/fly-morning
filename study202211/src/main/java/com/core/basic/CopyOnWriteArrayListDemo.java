package com.core.basic;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.TimeUnit;

public class CopyOnWriteArrayListDemo {

    public static void main(String[] args) {
//        test();
        List<String> a = new ArrayList<String>();
        a.add("a");
        a.add("b");
        a.add("c");
        final CopyOnWriteArrayList<String> list = new CopyOnWriteArrayList<String>(a);
        Thread t = new Thread(new Runnable() {
            int count = -1;

            @Override
            public void run() {
                while (true) {
                    list.add(count++ + "");
                    try {
                        TimeUnit.MILLISECONDS.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
        t.setDaemon(true);
        t.start();
        try {
            TimeUnit.MILLISECONDS.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        for (String s : list) {
            System.out.println("hashCode=="+list.hashCode());
            System.out.println(s);
        }
    }

    private static void test() {
        List<String> a = new ArrayList<String>();
        a.add("a");
        a.add("b");
        a.add("c");
        final ArrayList<String> list = new ArrayList<String>(a);
        Thread t = new Thread(new Runnable() {
            int count = -1;

            @Override
            public void run() {
                while (true) {
                    list.add(count++ + "");
                }
            }
        });
        t.setDaemon(true);
        t.start();
        try {
            TimeUnit.MILLISECONDS.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        for (String s : list) {
            System.out.println(s);
        }
    }
}
