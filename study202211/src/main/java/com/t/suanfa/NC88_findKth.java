package com.t.suanfa;

import java.util.PriorityQueue;

/**
 * 描述
 * 有一个整数数组，请你根据快速排序的思路，找出数组中第 k 大的数。
 *
 * 给定一个整数数组 a ,同时给定它的大小n和要找的 k ，请返回第 k 大的数(包括重复的元素，不用去重)，保证答案存在。
 * 要求：时间复杂度 O(nlogn)O(nlogn)，空间复杂度 O(1)O(1)
 * 数据范围：0\le n \le 10000≤n≤1000， 1 \le K \le n1≤K≤n，数组中每个元素满足 0 \le val \le 100000000≤val≤10000000
 * 示例1
 * 输入：
 * [1,3,5,2,2],5,3
 *
 * 返回值：
 * 2
 *
 * 示例2
 * 输入：
 * [10,10,9,9,8,7,5,6,4,3,4,2],12,3
 *
 * 返回值：
 * 9
 *
 * 说明：
 * 去重后的第3大是8，但本题要求包含重复的元素，不用去重，所以输出9
 */
public class NC88_findKth {

    public static void main(String[] args) {
        //int[] arr = {11,3,4,5,7,1,2,9,0,1};
        int[] arr = {22,1,10,10,9,9,8,7,5,6,4,3,4,2};
        NC88_findKth test = new NC88_findKth();
        int kth = test.findKth2(arr, arr.length, 6);
        System.out.println(kth);
    }

    private int findKth2(int[] arr, int length, int i) {
        PriorityQueue<Integer> queue = new PriorityQueue<>(3);
        for (int ele : arr) {
            if(i>queue.size())
                queue.add(ele);
            else {
                if(ele > queue.peek()){
                    queue.poll();
                    queue.add(ele);
                }
            }

        }
        System.out.println(queue.peek());
        return queue.peek();
    }
















    private int findKth1(int[] arr, int length, int i) {
        PriorityQueue<Integer> queue = new PriorityQueue<>();
        for (int a : arr) {
            //if(queue.size() <= i){
            if(queue.size() < i){
                queue.add(a);
            }else {
                if(a > queue.peek()){
                    queue.poll();
                    queue.add(a);
                }
            }
        }
        int re = queue.peek();
        return re;
    }


    public int findKth(int[] a, int n, int K){
        // 暂存K个较大的值，优先队列默认是自然排序（升序），队头元素（根）是堆内的最小元素，也就是小根堆
        PriorityQueue<Integer> queue = new PriorityQueue<>(K);
        // 遍历每一个元素，调整小根堆
        for (int num : a) {
            // 对于小根堆来说，只要没满就可以加入（不需要比较）；如果满了，才判断是否需要替换第一个元素
            if (queue.size() < K) {
                queue.add(num);
            } else {
                // 在小根堆内，存储着K个较大的元素，根是这K个中最小的，如果出现比根还要大的元素，说明可以替换根
                if (num > queue.peek()) {
                    queue.poll(); // 高个中挑矮个，矮个淘汰
                    queue.add(num);
                }
            }
        }
        return queue.isEmpty() ? 0 : queue.peek();
    }

}
