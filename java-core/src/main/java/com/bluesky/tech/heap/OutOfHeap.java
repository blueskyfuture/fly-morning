package com.bluesky.tech.heap;

import java.nio.ByteBuffer;
import sun.misc.Unsafe;

/**
 * 堆外内存
 * https://www.jianshu.com/p/17e72bb01bf1
 */
public class OutOfHeap {

    /**
     * 然而Unsafe类的构造器是私有的，报错。
     * 而且，allocateMemory方法也不是静态的，不能通过Unsafe.allocateMemory调用。
     */
    public void test() {
//        Unsafe unsafe = new Unsafe();
//        unsafe.allocateMemory(1024);
    }

    public static void main(String[] args) {
//        extracted();
        ByteBuffer buffer = ByteBuffer.allocateDirect(10 * 1024 * 1024);
    }



    private static void extracted() {
        Unsafe unsafe = Unsafe.getUnsafe();
        unsafe.allocateMemory(1024);
        unsafe.reallocateMemory(1024, 1024);
        unsafe.freeMemory(1024);
    }

}
