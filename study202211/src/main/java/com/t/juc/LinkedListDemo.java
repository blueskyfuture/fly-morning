package com.t.juc;

import java.util.LinkedList;

public class LinkedListDemo {
    //给定一个LinkedList集合
    static LinkedList<Integer> list = new LinkedList<>();

    public static void main(String[] args) {
        extracted();
    }

    private static void extracted() {
        //保证其输出为由小到大
        addList(8);
        addList(10);
        addList(7);
        addList(6);
        addList(11);
        System.out.println(list);
    }


    private static void extracted1() {
        //保证其输出为由小到大
        list.add(8);
        list.add(10);
        list.add(7);
        list.add(6);
        list.add(11);
        System.out.println(list);
    }

    static void addList(int i) {
        int j=0;
        for (Integer integer : list) {
            if (integer>i) {
                break;
            }
            j++;
        }
        list.add(j, i);
    }
}
