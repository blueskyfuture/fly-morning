package com.t.juc;

import java.util.function.BiConsumer;
import java.util.function.BiFunction;
import java.util.function.Function;

public class BiConsumerDemo {


    public static void main(String[] args) {

        // Math.pow(a1, a2) returns Double
        BiFunction<Integer, Integer, Double> func1 = (a1, a2) -> Math.pow(a1, a2);

        // takes Double, returns String
        Function<Double, String> func2 = (input) -> "Result : " + String.valueOf(input);

        String result = func1.andThen(func2).apply(2, 4);

        System.out.println(result);
        test();



    }
    public static void test() {

        math(1, 1, (x, y) -> System.out.println(x + y));   // 2
        math(1, 1, (x, y) -> System.out.println(x - y));   // 0
        math(1, 1, (x, y) -> System.out.println(x * y));   // 1
        math(1, 1, (x, y) -> System.out.println(x / y));   // 1
        math1(1, 1, (x, y) -> System.out.println("x/y="+(x / y)), (a,b) -> System.out.println(a+" b:"+b));   // 1

    }
    static <Integer> void math1(Integer a1, Integer a2, BiConsumer<Integer, Integer> c,BiConsumer<Integer, Integer> c1) {
        //c.accept(a1, a2);
        c.andThen(c1).accept(a1,a2);
    }

    static <Integer> void math(Integer a1, Integer a2, BiConsumer<Integer, Integer> c) {
        c.accept(a1, a2);
    }

    private static void extracted1() {
        addTwo(1, 2, (x, y) -> System.out.println(x + y));          // 3
        addTwo("Node", ".js", (x, y) -> System.out.println(x + y)); // Node.js
    }

    static <T> void addTwo(T a1, T a2, BiConsumer<T, T> c) {
        c.accept(a1, a2);
    }

    private static void extracted() {
        BiConsumer<Integer, Integer> addTwo = (x, y) -> System.out.println(x + y);
        addTwo.accept(1, 2);    // 3
    }
}
