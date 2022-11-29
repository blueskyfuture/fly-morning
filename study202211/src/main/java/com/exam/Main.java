package com.exam;

import java.util.Arrays;

/**
 * 集度算法，两个有序数组合并
 */
public class Main {
    public static void main(String[] args) {
        //Scanner in = new Scanner(System.in);
        //int a = in.nextInt();
        //System.out.println(a);
        System.out.println("Hello World!");

        // 合并两个有序数组为一个有序数组
        int[] src1 = {1,4,6,15};
        int[] src2 = {2,3,7,10};
        // int[] dst = {1,2,3,4,6};
        int[] result =  Main.mergeTwoArr(src1,src2);
        System.out.println(Arrays.toString(result));
    }

    public void merge(int[] nums1, int m, int[] nums2, int n) {
        //合并后最后一个数的索引为m+n-1
        int i = m-1;int j = n-1;int k = m+n-1;
        while(i >= 0 && j >= 0){
            //将两个数组中从最后一位开始比较，较大的先插入
            //当j先等于0时，说明nums2的数字已经全部复制到nums1中，此时合并完成(说明nums1中最小的元素比nums2小)
            //当i先等于0时，说明nums1中原来的所有数字已经复制完毕，此时进入下面的循环(说明nums1中最小的元素比nums2大)
            nums1[k--] = nums1[i] > nums2[j] ? nums1[i--] : nums2[j--];
        }
        while(j >= 0){
            nums1[k--] = nums2[j--];
        }
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
            if(arr2Index >= arr2Len -1){
                arr[index] = num1;
                index++;
            }else{
                for(int j = arr2Index; j < arr2Len; j++){
                    int num2 = arr2[j];
                    if(num1 < num2){
                        arr[index] = num1;
                        arr2Index = j;
                        index ++;
                        break;
                    }else{
                        arr[index] = num2;
                        arr2Index = j;
                        index ++;
                    }
                }
                if(arr2Index >= arr2Len -1){
                    arr[index] = num1;
                    index++;
                }

            }

        }

        return arr;
    }
}
