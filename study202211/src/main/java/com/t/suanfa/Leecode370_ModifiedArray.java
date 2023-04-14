package com.t.suanfa;

import java.util.Arrays;

/**
 * 参考:https://labuladong.gitee.io/algo/di-yi-zhan-da78c/shou-ba-sh-48c1d/xiao-er-me-c304e/
 * leecode370：区间加法
 * 假设有一个长度为你的数组，初始情况下所有的数字均为0，进行k个更新操作
 * 每个操作表示为一个三元组:[startIndex,endIndex,inc],即对数组中从startIndex到endIndex增加inc
 * 请返回k次操作后的数组
 * 示例:len=5,update={{1,3,2},{2,4,3},{0,2,-2}}
 *
 *
 */
public class Leecode370_ModifiedArray {
    public static void main(String[] args) {
        int len = 5;
        int[][] update = {{1,3,2},{2,4,3},{0,2,-2}};
        NumArray370 test = new NumArray370(len,update);
        int[] arr = test.getArr();
        System.out.println("arr==" + Arrays.toString(arr));
    }
}

class NumArray370{
    int[] arr;

    public NumArray370(int len,int[][] update){
        arr = new int[len];
        int[] diffArr = new int[len];
        for (int i = 0; i < update.length; i++) {
            int[] cur = update[i];
            int begin = cur[0];
            int end = cur[1];
            int num = cur[2];
            diffArr[begin] = diffArr[begin] + num;
            if(end + 1 < len){
                diffArr[end + 1] = diffArr[end + 1] - num;
            }
        }
        //第一个赋值
        arr[0] = diffArr[0];
        for (int i = 1; i < len; i++) {
            arr[i] = arr[i-1] + diffArr[i];
        }
    }

    public int[] getArr(){
        return arr;
    }
}
