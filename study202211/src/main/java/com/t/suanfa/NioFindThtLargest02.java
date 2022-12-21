package com.t.suanfa;

public class NioFindThtLargest02 {

    /**
     * 1,2,3,4,5,6,7,8右移k位，7，8，1，2，3，4，5，6；
     * 然后查找最大值，要求时间复杂度logN
     * @param args
     */
    public static void main(String[] args) {
        int arr[] = {1,2,3,4,5,6,7,8};
//        int arr[] = {7,8,1,2,3,4,5,6};
        findLeast(arr);
//        int largest = maxNumberInRotateArray01(arr);
        int largest = maxNumberInRotateArray02(arr);
        System.out.println("largest: " + largest);
    }

    public static  int maxNumberInRotateArray02(int [] array) {
        if (array.length <= 0 || array == null){
            return 0;
        }
        int left = 0,right = array.length-1,mid = 0;
        //保证旋转
        while (array[left] >= array[right]){
            //左右指针相邻停止
            if (right - left == 1){
                mid = left;
                //循环终止
                break;
            }
            /**
             * 如果中间值和首尾相等，则按顺序寻找最小值
             * or mid = (left + right)/2
             *
             * mid = (left + right)/2;
             */

            mid = left + (right - left)/2;
            if (array[left] == array[right] && array[right] == array[mid]){
                return maxArray(array,left,right);
            }
            /**
             *如果中间值大于等于左边指针，最大元素位于中间元素的后面，改变左指针
             * 否则最大元素位于前数组中，最大元素位于中间数组的前面，改变右指针继续
             */
            if (array[mid] >= array[left]){
                left = mid;
            }else{
                right = mid;
            }
        }
        return array[mid];
    }


    public static  int maxNumberInRotateArray01(int [] array) {
        if (array.length <= 0 || array == null){
            return 0;
        }
        int left = 0,right = array.length-1,mid = 0;
        //保证旋转
        while (array[left] >= array[right]){
            //左右指针相邻停止
            if (right - left == 1){
                mid = left;
                //循环终止
                break;
            }
            /**
             * 如果中间值和首尾相等，则按顺序寻找最小值
             * or mid = (left + right)/2
             *
             * mid = (left + right)/2;
             */

            mid = left + (right - left)/2;
            if (array[left] == array[right] && array[right] == array[mid]){
                return maxArray(array,left,right);
            }
            /**
             *如果中间值大于等于左边指针，则位于第一个递增数组中，最大元素位于中间元素的后面，改变左指针
             * 否则最大元素位于前数组中，最大元素位于中间数组的前面，改变右指针继续
             */
            if (array[mid] >= array[left]){
                left = mid;
            }else{
                right = mid;
            }
        }
        return array[mid];
    }


    private static int maxArray(int[] array, int left, int right) {
        int max = array[left];
        for (int i=left+1;i<array.length;i++){
            if (array[i] > max){
                max = array[i];
            }
        }
        return max;
    }


    /**
     * 求选择后数组最小值
     * @param arr
     */
    private static void findLeast(int[] arr) {
        int left = 0;
        int right = arr.length - 1;
        while (left < right){
            int mid = (left + right)/2;
            if(arr[mid] > arr[right])
                left = mid + 1;
            else if(arr[mid] < arr[right])
                right = mid;
                //需要注意的是必须考虑数据重复的可能性
            else right = right - 1;
        }
        System.out.println("findLeast arr[" + left + "]=" + arr[left]);
        return;
    }

    /**
     * 下面方法不对
     * @param arr
     * @return
     */
    private static int solution_err(int[] arr) {
        if(arr.length == 0)
            return -1;
        if(arr.length == 1)
            return arr[0];
        int left = 0;
        int right = arr.length - 1;
        while (left < right){
            int mid = (left + right)/2;
//            if(mid==0)
//                return arr[mid];
            if(arr[mid] > arr[left]){
                left = mid;
                continue;
            }else {
                right = mid - 1;
                continue;
            }
        }
        return arr[left];
    }

}
