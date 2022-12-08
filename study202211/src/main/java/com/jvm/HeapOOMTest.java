package com.jvm;

import java.util.ArrayList;
import java.util.List;

/**
 * https://www.bilibili.com/video/BV1Eq4y1n73i/?spm_id_from=333.788&vd_source=03f180022403e684f2ddc65d9743517f
 * 解决步骤：
 * 1、打印日志；2、获取dump文件；3、MAT分析；4、修改验证
 *配置的jvm参数：
 * -XX:+PrintGCDetails
 * -XX:+UseConcMarkSweepGC #java9后不推荐使用
 * -Xmx20m
 * -Xms20m
 * -XX:+HeapDumpOnOutOfMemoryError
 * -XX:+HeapDumpBeforeFullGC
 * -XX:+HeapDumpAfterFullGC
 * -XX:HeapDumpPath=/data/jvm/gc
 */
public class HeapOOMTest {
    static class OOMObject{}

    public static void main(String[] args) {
        List<OOMObject> list = new ArrayList<>();
        while (true){
            list.add(new OOMObject());
        }
    }

}
