package com.t.pattern;

/**
 * https://www.runoob.com/design-pattern/singleton-pattern.html
 *
 * 4、双检锁/双重校验锁（DCL，即 double-checked locking）
 * JDK 版本：JDK1.5 起
 *
 * 是否 Lazy 初始化：是
 *
 * 是否多线程安全：是
 */
public class Singleton04 {

    private static Singleton04 instance;

    private Singleton04() {
    }

    private Singleton04 getInstance() {
        if (instance == null) {
            synchronized (Singleton04.class) {
                if (instance == null) {
                    instance = new Singleton04();
                }
            }
        }
        return instance;
    }
}
