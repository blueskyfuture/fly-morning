package com.t.juc;

public class MyObject {

    @Override
    protected void finalize() throws Throwable {
        System.out.println("MyObject finalize");
    }
}
