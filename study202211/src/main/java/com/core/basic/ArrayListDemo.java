package com.core.basic;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

public class ArrayListDemo {
    public static void main(String[] args){
        test01();
    }

    private static void test01() {
//        Collections.synchronizedList()
        List<Integer> list01 = new ArrayList<Integer>();

        List<String> list = new ArrayList<String>();
        //CopyOnWriteArrayList<String> list = new CopyOnWriteArrayList<String>();
        list.add("a");
        list.add("b");
        list.add("c");
        list.add("d");
        list.add("e");
        boolean a = list.contains("a");
        Iterator iterator = list.iterator();
        while(iterator.hasNext()){
            String str = (String) iterator.next();
            if(str.equals("b")){
                list.remove(str);
            }else{
                System.out.println(str);
            }
        }
    }

    private static void extracted() {
        List<String> list = new ArrayList<String>();
        //CopyOnWriteArrayList<String> list = new CopyOnWriteArrayList<String>();
        list.add("a");
        list.add("a");
        list.add("b");
        System.out.println("list.size="+list.size());
        Iterator iterator = list.iterator();
        while(iterator.hasNext()){
            String str = (String) iterator.next();
            list.remove(str);
        }
    }
}
