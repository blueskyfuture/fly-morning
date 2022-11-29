package com.t.pattern;

/**
 * https://www.runoob.com/design-pattern/singleton-pattern.html
 * 2、懒汉式，线程安全
 * 是否 Lazy 初始化：是
 *
 * 是否多线程安全：是
 */
public class Singleton02 {

    private static Singleton02 instance;

    private Singleton02() {
    }

    public static synchronized Singleton02 getInstance() {
        if (instance == null) {
            instance = new Singleton02();
        }
        return instance;
    }

    public void showMessage(){
        System.out.println("helloworld--Singleton01_1");
    }
}
