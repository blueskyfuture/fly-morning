package com.bluesky.tech.basic;

/**
 * https://pdai.tech/md/java/thread/java-thread-x-key-final.html
 */
public class FinalDemo {

    public static void main(String[] args) {
        test01();
        test02();
    }

    private static void test01() {
        byte b1=1;
        byte b2=3;
        int re = b1 + b2;
        System.out.println("re==" + re);
//        byte b3=b1+b2;//当程序执行到这一行的时候会出错，因为b1、b2可以自动转换成int类型的变量，运算时java虚拟机对它进行了转换，结果导致把一个int赋值给byte-----出错

    }


    /**
     * https://www.cnblogs.com/tangjiang-code/p/7881805.html
     */
    private static void test02() {
        final byte b1=1;
        final byte b2=3;

        byte b3 = b1 + b2;//不会出错  即最终的变量和属性不能被修改。
        System.out.println(b3);
        System.out.println("b3=" + b3);
    }

    private static void test03() {
        final byte b1=1;
        //b2 = 5;//报错
        final String str = "abc";
        //str = "def";//报错

    }


}
