package com.exam;
import java.util.*;

/**
 * 集度算法，两个有序数组合并
 * 面试时做的，调试有问题
 */
public class Main_1 {
    public static void main(String[] args) {
        //Scanner in = new Scanner(System.in);
        //int a = in.nextInt();
        //System.out.println(a);
        System.out.println("Hello World!");

        // 合并两个有序数组为一个有序数组
        int[] src1 = {1,4,6};
        int[] src2 = {2,3};
        // int[] dst = {1,2,3,4,6};
        int[] result =  Main_1.mergeTwoArr(src1,src2);
        System.out.println(Arrays.toString(result));
    }

    private static int[] mergeTwoArr(int[] arr1, int[] arr2){
        int arr1Len = arr1.length;
        int arr2Len = arr2.length;
        int totalLen = arr1Len + arr2Len;
        int[] arr = new int[totalLen];
        int index = 0;
        int arr2Index = 0;
        for(int i = 0; i < arr1Len; i++){
            int num1 = arr1[i];
            for(int j = arr2Index; j < arr2Len; j++){
                int num2 = arr2[j];
                if(num1 < num2){
                    arr[index] = num1;
                    arr2Index = j;
                    index ++;
                    break;
                }else{
                    arr[index] = num2;
                    index ++;
                    arr2Index = j;
                }

            }
        }

        return arr;
    }
}