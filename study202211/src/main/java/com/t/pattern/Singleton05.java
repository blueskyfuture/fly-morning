package com.t.pattern;

/**
 * 5、登记式/静态内部类
 * 是否 Lazy 初始化：是
 *
 * 是否多线程安全：是
 */
public class Singleton05 {
    private static class Holder{
        private static final Singleton05 instance = new Singleton05();
    }

    private Singleton05(){}

    public static final Singleton05 getInstance(){
        return Holder.instance;
    }

}
