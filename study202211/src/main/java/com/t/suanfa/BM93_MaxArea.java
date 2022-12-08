package com.t.suanfa;

/**
 * 给定一个数组height，长度为n，每个数代表坐标轴中的一个点的高度，height[i]是在第i点的高度，请问，从中选2个高度与x轴组成的容器最多能容纳多少水
 * 1.你不能倾斜容器
 * 2.当n小于2时，视为不能形成容器，请返回0
 * 3.数据保证能容纳最多的水不会超过整形范围，即不会超过231-1
 *
 * 数据范围:
 * 0<=height.length<=10^50<=height.length<=10
 * 5
 *
 * 0<=height[i]<=10^40<=height[i]<=10
 * 4
 * 如输入的height为[1,7,3,2,4,5,8,2,7]，
 */
public class BM93_MaxArea {

    public static void main(String[] args) {
        //int[] arr = {1,7,3,2,4,5,8,2,7};
        int[] arr = {1,10,3,2,4,5,20,2,7};
        int area = maxArea2(arr);
        System.out.println("area:" + area);
    }

    private static int maxArea2(int[] arr) {
        if(arr.length < 2)
            return 0;

        int left = 0;
        int right = arr.length - 1;
        int result = 0;
        while (left < right){
            int cur = Math.min(arr[left],arr[right]) * (right - left);
            result = cur > result ? cur : result;
            if(arr[left] > arr[right])
                right --;
            else
                left ++;
        }
        return result;
    }
















    private static int maxArea1(int[] arr) {
        if(arr.length < 2)
            return 0;
        int result = 0;
        int left = 0;
        int right = arr.length - 1;
        while (left < right){
            int cur = Math.min(arr[left], arr[right]) * (right - left);
            if(cur > result)
                result = cur;

            if(arr[left] > arr[right])
                right --;
            else
                left++;
        }
        return result;
    }


    public static int maxArea (int[] height) {
        //排除不能形成容器的情况
        if(height.length < 2)
            return 0;
        int res = 0;
        //双指针左右界
        int left = 0;
        int right = height.length - 1;
        //共同遍历完所有的数组
        while(left < right){
            //计算区域水容量
            int capacity = Math.min(height[left], height[right]) * (right - left);
            //维护最大值
            res = Math.max(res, capacity);
            //优先舍弃较短的边
            if(height[left] < height[right])
                left++;
            else
                right--;
        }
        return res;
    }


}
