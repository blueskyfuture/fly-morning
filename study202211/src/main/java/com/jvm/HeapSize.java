package com.jvm;

import java.util.concurrent.TimeUnit;

/**
 * https://pdai.tech/md/java/jvm/java-jvm-struct.html
 * 将 -Xmx 和 -Xms 两个参数配置为相同的值，其目的是为了能够在垃圾回收机制清理完堆区后不再需要重新分隔计算堆的大小，从而提高性能默认情况下，
 * 初始堆内存大小为：电脑内存大小/64默认情况下，最大堆内存大小为：电脑内存大小/4可以通过代码获取到我们的设置值
 *
 */
public class HeapSize {
    public static void main(String[] args) {
        //返回 JVM 堆大小
        long initalMemory = Runtime.getRuntime().totalMemory() / 1024 /1024;
        //返回 JVM 堆的最大内存
        long maxMemory = Runtime.getRuntime().maxMemory() / 1024 /1024;

        System.out.println("-Xms : "+initalMemory + "M");
        System.out.println("-Xmx : "+maxMemory + "M");

        System.out.println("系统内存大小：" + initalMemory * 64 / 1024 + "G");
        System.out.println("系统内存大小：" + maxMemory * 4 / 1024 + "G");
        try {
            TimeUnit.MINUTES.sleep(10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
