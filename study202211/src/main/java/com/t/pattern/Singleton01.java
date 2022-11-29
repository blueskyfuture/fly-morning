package com.t.pattern;

/**
 * https://www.runoob.com/design-pattern/singleton-pattern.html
 * 1、懒汉式，线程不安全
 * 是否 Lazy 初始化：是
 *
 * 是否多线程安全：否
 */
public class Singleton01 {
    private static Singleton01 instance;
    private Singleton01(){}
    public static Singleton01 getInstance(){
        if(instance == null){
            instance = new Singleton01();
        }
        return instance;
    }

    public void showMessage(){
        System.out.println("helloworld--Singleton01");
    }
}
