package com.jvm;

import java.util.HashMap;
import java.util.Map;

/**
 * 参考https://heapdump.cn/article/3100493
 */
public class HeapOOM {
//    static final int SIZE=10*1024*1024;
    static final int SIZE = 100;
    public static void main(String[] a) {
//        int[] i = new int[SIZE];
        test();
    }

    /**
     * 内存泄漏举例
     */
    private static void test() {
        Map m = new HashMap();
        while (true)
            for (int i = 0; i < 10000; i++)
                if (!m.containsKey(new Key(i)))
                    m.put(new Key(i), "Number:" + i);
    }

    static class Key {
        Integer id;

        Key(Integer id) {
            this.id = id;
        }

//        @Override
//        public int hashCode() {
//            return id.hashCode();
//        }

        @Override
        public boolean equals(Object o) {
            boolean response = false;
            if (o instanceof Key) {
                response = (((Key)o).id).equals(this.id);
            }
            return response;
        }
    }
}
