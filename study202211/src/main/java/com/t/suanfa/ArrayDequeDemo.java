package com.t.suanfa;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Queue;

public class ArrayDequeDemo {
    public static void main(String[] args) {
        Queue<String> queue = new ArrayDeque<String>();
        queue.add("1");
        queue.add("2");
        queue.add("3");

        System.out.println(queue.poll());
        System.out.println(queue.poll());
        System.out.println(queue.poll());
        System.out.println(queue.poll());
        System.out.println("-------------------------");
        Deque<String> deque = new ArrayDeque<String>();
        deque.add("1");
        deque.add("2");
        deque.add("3");

        System.out.println(deque.pollFirst());
        System.out.println(deque.pollLast());
        System.out.println(deque.poll());
    }
}
