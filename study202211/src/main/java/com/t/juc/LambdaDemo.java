package com.t.juc;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class LambdaDemo {

    public static void main(String[] args) {
        test04();
    }

    private static void test04() {
        List<String> list = new ArrayList<>();
        list.add("abc");
        list.replaceAll(s -> s + "efg");
        System.out.println(list);
    }


    private static void test01() {
        new Thread(() -> {
          System.out.println("currentThreadName:"+Thread.currentThread().getName());
        },"thread-a").start();
    }

    private static void test02() {
        List<String> list = new ArrayList<>();
        Collections.sort(list,(o1,o2) ->{
            if(o1.equals(o2))
                return 1;
            else
                return -1;
        });

    }

    interface ITest
    {
        int test(String string);
    }

    static void print(ITest test){
        int len = test.test("hello");
        System.out.println("len--" + len);
    }


    private static void test03() {
        print(string -> {
            System.out.println("==="+string);
            return string.length();
        });
    }

}
