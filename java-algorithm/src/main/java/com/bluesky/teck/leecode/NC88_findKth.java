package com.bluesky.teck.leecode;

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
 * 复制
 * 返回值：
 * 2
 * 复制
 * 示例2
 * 输入：
 * [10,10,9,9,8,7,5,6,4,3,4,2],12,3
 * 复制
 * 返回值：
 * 9
 * 复制
 * 说明：
 * 去重后的第3大是8，但本题要求包含重复的元素，不用去重，所以输出9
 */
public class NC88_findKth {

    public static void main(String[] args) {
        int[] arr = {11,3,4,5,7,1,2,9,0,1};
        NC88_findKth test = new NC88_findKth();
        int kth = test.findKth01(arr, arr.length, 9);
        System.out.println(kth);
    }

    public int findKth(int[] a, int n, int K) {
        // write code here
        int l = 0; int r = n-1;
        return quik01(a, l, r, K);

    }

    //k计数从1开始,坐标第一个数为1
    private int quik(int[] a, int l, int r, int k) {
        //int position = find(a, l , r, k);
        int position = find01(a, l , r);
        if(position == k-1) {
            return a[position];
        } else if(position > k-1) {
            return quik(a, l, position -1, k);
        } else {
            return quik(a, position+1, r, k);
        }
    }

    private int find(int[] a, int l, int r, int k) {
        int i = l;
        int j = r;
        int key = a[i];
        while(i < j) {
            while(i<j && a[j] <= key) j--;
            a[i] = a[j] ;
            while(i<j && a[i] >= key) i++;
            a[j] = a[i];
        }
        a[j] = key;
        return j;
    }

    public int findKth01(int[] a,int n,int k){
        int l = 0,r = n-1;
        int i = quik01(a, l, r, k);
        return i;
    }

    //k计数从0开始,坐标第一个数为0
    private int quik01(int[] a, int l, int r, int k){
        int pos = find01(a, l, r);
        if(pos == k){
            return a[pos];
        }
        if(pos > k){
            return quik01(a, l, pos - 1,k);
        }
        if(pos < k){
            return quik01(a, pos+1, r,k);
        }
        return -99;
    }

    private int find01(int[] a, int l, int r){
        int left = l;
        int right = r;
        int key = a[left];
        while (left < right){
            while (left<right && a[right]>=key){
                right --;
            }
            if(left<right){
                a[left] = a[right];
                left++;
            }
            while (left<right && a[left]<=key){
                left++;
            }
            if(left<right){
                a[right] = a[left];
                right--;
            }

        }
        a[left]=key;
        return left;
    }
}
