package com.t.suanfa;

import java.util.Arrays;

public class QuickSort {

    public static void main(String[] args) {
        //int[] arr = new int[]{90,80,30,40,60,50,70};
        int[] arr = new int[]{25,90,65,80,30,93,40,60,55,50,70,25};
        System.out.println("array====" + Arrays.toString(arr));
        //quickSort(arr,0,arr.length-1);
//        quickSort2(arr,0,arr.length-1);
        quickSort8(arr, 0, arr.length -1 );
        System.out.println("array after quick sort====" + Arrays.toString(arr));
    }

    private static void quickSort8(int[] arr, int L, int R) {
        if (L >= R)
            return;
        int left = L;
        int right = R;
        int pivot = arr[L];
        while (left < right){
            while (left < right && pivot <= arr[right]) {
                right--;
            }
            if (left < right)
                arr[left] = arr[right];
            while (left < right && pivot >= arr[left])
                left++;
            if (left < right)
                arr[right] = arr[left];
            if (left >= right)
                arr[left] = pivot;
      }
      quickSort8(arr,L,right-1);
        quickSort8(arr,right+1,R);
    }



    private static void quickSort7(int[] arr, int L, int R) {
        if(L>=R)
            return;
        int left = L;
        int right = R;
        //
        int pivot = arr[L];
        while (left < right){
            while (left < right && pivot <= arr[right])
                right--;
            if(left < right)
                //arr[right] = arr[left];
                arr[left] = arr[right];
            while (left < right && pivot >=arr[left])
                left++;
            if(left < right)
                //arr[left] = arr[right];
                arr[right] = arr[left];
            if(left >= right)
                //
                arr[left] = pivot;
        }
        quickSort7(arr,L,right);
        quickSort7(arr,right+1,R); }


    /**
     * 找一个标准值，小于的放到左边，大于的放到右边
     * @param arr
     * @param L
     * @param R
     */
    private static void quickSort6(int[] arr, int L, int R) {
        if(L >= R)
            return;
        int left = L;
        int right = R;
        int pivot = arr[left];
        while (left < right){
            while (left <right && pivot <= arr[right])
                right--;
            if(left < right)
                arr[left] = arr[right];
            while (left < right && pivot >= arr[left])
                left++;
            if(left < right)
                arr[right] = arr[left];
            if(left >= right)
                arr[left] = pivot;//这里为啥是left不是right呢
        }
        quickSort6(arr,L,right-1);
        quickSort6(arr,right+1,R);
    }


    private static void quickSort5(int[] arr, int L, int R) {
        if(L >= R)
            return;
        int left = L;
        int right = R;
        int pivot = arr[left];
        while (left < right){
            while (left < right && pivot <= arr[right]){
                right --;
            }
            if(left < right)
                arr[left] = arr[right];
            while (left < right && pivot >= arr[left])
                left++;
            if(left < right)
                arr[right] = arr[left];
            if(left >= right)
                arr[left] = pivot;
        }
        quickSort5(arr,L,right-1);
        quickSort5(arr,right+1,R);
    }


    private static void quickSort4(int[] arr, int L, int R) {
        if(L >= R)
            return;
        System.out.println(Arrays.toString(arr));
        int pivot = arr[L];
        int left = L;
        int right = R;
        while (left < right){
            //while (left < right && pivot < arr[right]){
            while (left < right && pivot <= arr[right]){
                right --;
            }
            if(left < right)
                arr[left] = arr[right];
            System.out.println(Arrays.toString(arr));
            while (left < right && pivot >= arr[left]){
                left ++;
            }
            if(left < right){
                arr[right] = arr[left];
            }
            System.out.println(Arrays.toString(arr));
            if(left >= right)//
                arr[left] = pivot;
            System.out.println(Arrays.toString(arr));
        }
        quickSort4(arr,L,right-1);
        quickSort4(arr,right+1,R);
    }

    private static void quickSort3(int[] arr, int L, int R) {
        if(L >= R)
            return;
        int left = L, right = R;
        int pivot  = arr[left];
        while (left < right){
            while (left < right && pivot <= arr[right])
                right--;
            if(left < right)
                arr[left] = arr[right];
            while (left < right && pivot >= arr[left])
                left ++;
            if(left < right)
                arr[right] = arr[left];
            if(left>=right)
                arr[left] = pivot;
        }
        quickSort3(arr,L,right-1);
        quickSort3(arr,right+1,R);
    }



    private static void quickSort1(int[] arr, int L, int R) {
        if(L >= R)
            return;
        int left = L, right = R;
        int pivot = arr[L];
        //int pivot = arr[left];
        while (left < right){
            while (left<right && arr[right] >= pivot){
                right --;
            }
            //if(arr[right] < pivot)
            if(left < right)
                arr[left] = arr[right];

            while (left < right && pivot >= arr[left]){
                left ++;
            }

            //if(arr[left] > pivot)
            if(left < right)
                arr[right] = arr[left];

            //---
            if(left >= right)
                arr[left] = pivot;
        }
        quickSort(arr,L,right-1);
        quickSort(arr,right+1,R);
    }


    private static void quickSort(int arr[], int L, int R) {
        if(L >= R)
            return;
        int left = L, right = R;
        int pivot = arr[left];
        while (left < right){
            while (left < right && pivot <= arr[right]){
                right --;
            }
            if(left < right){
                arr[left] = arr[right];
            }
            while (left < right && pivot >= arr[left]){
                left ++;
            }
            if(left < right){
                arr[right] = arr[left];
            }

            if(left >= right){
                arr[left] = pivot;
            }
        }
        quickSort(arr,L,right-1);
        quickSort(arr,right+1,R);

    }

}
