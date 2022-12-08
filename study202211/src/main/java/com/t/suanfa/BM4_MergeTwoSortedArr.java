package com.t.suanfa;

import com.exam.Main;
import java.util.Arrays;

/**
 * 描述
 * 输入两个递增的链表，单个链表的长度为n，合并这两个链表并使新链表中的节点仍然是递增排序的。
 * 数据范围： 0 \le n \le 10000≤n≤1000，-1000 \le 节点值 \le 1000−1000≤节点值≤1000
 * 要求：空间复杂度 O(1)O(1)，时间复杂度 O(n)O(n)
 *
 * 如输入{1,3,5},{2,4,6}时，合并后的链表为{1,2,3,4,5,6}，所以对应的输出为{1,2,3,4,5,6}，转换过程如下图所示：
 */
public class BM4_MergeTwoSortedArr {
    public static void main(String[] args) {
        // 合并两个有序数组为一个有序数组
        int[] src1 = {1,4,6,9,11,15};
        int[] src2 = {2,3,7,10};
        // int[] dst = {1,2,3,4,6};
        int[] result =  BM4_MergeTwoSortedArr.merge4(src1,src1.length,src2,src2.length);
        System.out.println(Arrays.toString(result));
    }

    private static int[] merge4(int[] a, int i, int[] b, int j) {
        int[] c = new int[i+j];
        int k = 0;
        int i1 = 0;
        int j1 = 0;
        while (i1 < i && j1 < j){
            c[k++] = a[i1] < b[j1] ? a[i1++] : b[j1++];
        }
        while(i1 < i)
            c[k++] = a[i1++];
        while(j1 < j)
            c[k++] = b[j1++];
        return c;
    }

    private static int[] merge3(int[] src1, int length01, int[] src2, int length02) {
        int[] result = new int[length01 + length02];
        int index = length01 + length02 -1;
        int index01 = length01 - 1;
        int index02 = length02 - 1;
        while (index01 >= 0 && index02 >= 0){
            result[index--] = src1[index01] > src2[index02] ? src1[index01--] : src2[index02--];
        }
        while (index01 >= 0)
            result[index--] = src1[index01--];
        while (index02 >= 0)
            result[index--] =src2[index02--];
        return result;
    }









    private static int[] merge2(int[] src1, int len1, int[] src2, int len2) {
        int[] result = new int[len1 + len2];
        int index01 = len1-1;
        int index02 = len2-1;
        int index = len1 + len2 - 1;
        while (index01 >= 0 && index02 >= 0){
            result[index--] = src1[index01] > src2[index02] ? src1[index01--] : src2[index02--];
        }
        while (index01 >=0)
            result[index--] = src1[index01--];
        while (index02 >=0)
            result[index--] = src2[index02--];
        return result;
    }


    public static int[] merge1(int[] nums1, int m, int[] nums2, int n) {
        int[] result = new int[m+n];
        //合并后最后一个数的索引为m+n-1
        int i = m-1;int j = n-1;int k = m+n-1;
        while(i >= 0 && j >= 0){
            //将两个数组中从最后一位开始比较，较大的先插入
            //当j先等于0时，说明nums2的数字已经全部复制到nums1中，此时合并完成(说明nums1中最小的元素比nums2小)
            //当i先等于0时，说明nums1中原来的所有数字已经复制完毕，此时进入下面的循环(说明nums1中最小的元素比nums2大)
            result[k--] = nums1[i] > nums2[j] ? nums1[i--] : nums2[j--];
        }
        while(j >= 0){
            result[k--] = nums2[j--];
        }
        while(i >= 0){
            result[k--] = nums1[i--];
        }
        return result;
    }



}
