package com.t.suanfa;

import java.util.Arrays;

import static com.t.suanfa.BM1_NodeReserve.getNodeList;
import static com.t.suanfa.BM1_NodeReserve.printNodeList;

/**
 * https://leetcode.cn/problems/median-of-two-sorted-arrays
 4. 寻找两个正序数组的中位数
 给定两个大小分别为 m 和 n 的正序（从小到大）数组 nums1 和 nums2。请你找出并返回这两个正序数组的 中位数 。

 算法的时间复杂度应该为 O(log (m+n)) 。



 示例 1：

 输入：nums1 = [1,3], nums2 = [2]
 输出：2.00000
 解释：合并数组 = [1,2,3] ，中位数 2
 示例 2：

 输入：nums1 = [1,2], nums2 = [3,4]
 输出：2.50000
 解释：合并数组 = [1,2,3,4] ，中位数 (2 + 3) / 2 = 2.5
 */
public class Leecode4_MedianOfTwoSortedArrays {
    public static void main(String[] args) {
        int[] num1 = {1,2};
        int[] num2 = {3,4};
        double result = findMedianSortedArrays5(num1,num2);
        System.out.println("---------result:"+result);
    }

    private static double findMedianSortedArrays5(int[] num1, int[] num2) {
        int n1 = num1.length;
        int n2 = num2.length;
        if(n1>n2)
            findMedianSortedArrays5(num2,num1);
        int k = (n1 + n2 + 1)/2;
        int left = 0;
        int right = n1;
        while (left < right){
            int m1 = left + (right - left)/2;
            int m2 = k - m1;
            if(num1[m1] < num2[m2-1]){
                left = m1 + 1;
            }else
                right = m1;
        }
        int m1 = left;
        int m2 = k - m1;
        int c1 = Math.max(m1<=0?Integer.MIN_VALUE:num1[m1-1],
                m2<=0?Integer.MIN_VALUE:num2[m2-1]);
        if((n1+n2)%2==1)
            return c1;
        int c2 = Math.min(m1>=n1?Integer.MAX_VALUE:num1[m1],m2>=n2?Integer.MAX_VALUE:num2[m2]);

        return (c1+c2)*0.5;
    }

    private static double findMedianSortedArrays4(int[] num1, int[] num2) {
        int n1 = num1.length;
        int n2 = num2.length;
        if(n1 > n2)
            findMedianSortedArrays4(num2,num1);
        int left = 0;
        int right = n1;
        int k = (n1 + n2 + 1)/2;
        while (left < right){
            //int m1 = left + n1/2;
            int m1 = left + (right - left)/2;
            int m2 = k-m1;
            if(num1[m1] < num2[m2-1])
                left = m1 +1;
            else
                right = m1;
        }
        int m1 = left;
        int m2 = k - left;
        //int c1 = Math.min()
//        int c1 = Math.max(m1<0?Integer.MIN_VALUE:num1[m1-1],
//                m2<0?Integer.MIN_VALUE:num2[m2-1]);
        int c1 = Math.max(m1<=0?Integer.MIN_VALUE:num1[m1-1],
                m2<=0?Integer.MIN_VALUE:num2[m2-1]);
        if((n1+n2)%2 == 1)
            return c1;
//        int c2 = Math.min(m1<0?Integer.MAX_VALUE:num1[m1],
//                m2<0?Integer.MAX_VALUE:num2[m2]);
        int c2 = Math.min(m1>=n1?Integer.MAX_VALUE:num1[m1],
                m2>=n2?Integer.MAX_VALUE:num2[m2]);
        return (c1+c2)*0.5;
    }

    /**
     * 自己测试，写的有问题
     * @param num1
     * @param num2
     * @return
     */
    private static double findMedianSortedArrays3(int[] num1, int[] num2) {
        int n1 = num1.length;
        int n2 = num2.length;
        if(n1 > n2)
            findMedianSortedArrays3(num2,num1);
        int left = 0;
        int right = n1;
        int k = (n1 + n2 + 1)/2;
        while (left < right){
            int m1 = n1/2;
            int m2 = k-n1/2;
            if(num1[m1] < num2[m2])
                left = m1 +1;
            else
                right = n1/2;
        }
        int m1 = left;
        int m2 = k - left;
        //int c1 = Math.min()
        int c1 = Math.max(m1<0?Integer.MIN_VALUE:num1[m1-1],m2<0?Integer.MIN_VALUE:num2[m2-1]);
        if((n1+n2)%2 == 1)
            return c1;
        int c2 = Math.min(m1<0?Integer.MAX_VALUE:num1[m1],m2<0?Integer.MAX_VALUE:num2[m2]);
        if((n1+n2)%2 == 0)
            return (c1+c2)*0.5;
        return -999;
    }

    public static double findMedianSortedArrays2(int[] nums1, int[] nums2) {
        int n1 = nums1.length;
        int n2 = nums2.length;
        if (n1>n2)
            return findMedianSortedArrays2(nums2, nums1);
        int k = (n1 + n2 + 1)/2;
        int left = 0;
        int right = n1;
        while(left < right){
            int m1 = left +(right - left)/2;
            int m2 = k - m1;
            if (nums1[m1] < nums2[m2-1])
                left = m1 + 1;
            else
                right = m1;
        }
        int m1 = left;
        int m2 = k - left;
        int c1 = Math.max(m1 <= 0 ? Integer.MIN_VALUE : nums1[m1-1],
                m2 <= 0 ? Integer.MIN_VALUE : nums2[m2-1]);
        if ((n1 + n2) % 2 == 1)
            return c1;
        int c2 = Math.min( m1 >= n1 ? Integer.MAX_VALUE :nums1[m1],
                m2 >= n2 ? Integer.MAX_VALUE : nums2[m2]);
        return (c1 + c2) * 0.5;

    }

    public static double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int len1 = nums1.length;
        int len2 = nums2.length;
        int total = len1 + len2;
        int[] arr = new int[total];
        System.arraycopy(nums1,0,arr,0,len1);
        System.arraycopy(nums2,0,arr,len1,len2);
        System.out.println(Arrays.toString(arr));
        Arrays.sort(arr);
        int sum = 0;
        double result = 0;
        if(total % 2 == 0){
            sum = arr[total/2] + arr[total/2 -1];
            result = sum/2.0;
        }else{
            sum = arr[total/2];
            result = sum/1.0;
        }
        return result;
    }


}
