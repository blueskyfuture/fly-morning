package com.t.pattern;


/**
 * 3、饿汉式
 * 是否 Lazy 初始化：否
 *
 * 是否多线程安全：是
 *
 * 实现难度：易
 *
 * 描述：这种方式比较常用，但容易产生垃圾对象。
 */
public class Singleton03 {
    private static Singleton03 instance = new Singleton03();

    private Singleton03(){}

    public static Singleton03 getInstance(){
        return instance;
    }

    public void showMessage(){
        System.out.println("helloworld");
    }
}
