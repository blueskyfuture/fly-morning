package com.jvm;

/**
 * 字节码理解
 * https://pdai.tech/md/java/jvm/java-jvm-class.html
 *
 * javap -verbose -p Main.class
 */
public class Test01 {
    private int m;

    public int inc() {
        return m + 1;
    }
}




/*
//Main.java
public class Main {

    private int m;

    public int inc() {
        return m + 1;
    }
}
 */