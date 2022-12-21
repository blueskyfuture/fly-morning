package com.t.suanfa;

/**
 * https://blog.csdn.net/weixin_40173707/article/details/127886827
 */
public class NioFindThtLargest01 {

    /**
     * 1,2,3,4,5,6,7,8右移k位，7，8，1，2，3，4，5，6；
     * 然后查找最大值，要求时间复杂度logN
     * @param args
     */

    public static void main(String[] args) {

        //以向左旋转为例,类型分为：1.递增型小于半数旋转的旋转数组；2.多于半数旋转的递增数组；3.递减数组小于半数旋转的；4.递减数组多于半数旋转的；以下依次是4种类型旋转数组
        //规则：中间的数mid与两端的数做差。如果两端的小，差值绝对值大的，最小值就在那一侧；并且还有一个规律，就是两端会同时比中间大，或者同时比中间小。
        //int[] arr = {1,2,3,4,5,6,7,8};
//        int[] arr = {9,1,2,3,4,5,6,7,8};
        int[] arr = {2,3,4,5,6,7,8,9,1};
//        int[]  arr = {8,10,11,14,17,18,23,2,5};
//        int[]  arr0 = {17,18,19,26,3,5,8,10,11,14,15};
//        int[] arr1 = {14,11,10,8,6,5,2,21,18,17};
//        int[] arr2 = {5,2,25,18,17,14,11,10,8,6};

        NioFindThtLargest01 max = new NioFindThtLargest01();

        System.out.println(max.maxNumberInRotateArray(arr));
//        System.out.println(max.maxNumberInRotateArray01(arr0));
//        System.out.println(max.maxNumberInRotateArray(arr1));
//        System.out.println(max.maxNumberInRotateArray(arr2));

    }


    public  int maxNumberInRotateArray01(int [] array) {
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




    public  int maxNumberInRotateArray(int [] array) {
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

        while (array[left] <= array[right]){
            //左右指针相邻停止
            if (right - left == 1){
                mid = right;
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
             *如果中间值大于等于左边指针，则位于第一个递增数组中，最小元素位于中间元素的前面，改变左指针
             * 否则最小元素位于后数组中，最小元素位于中间数组的后面，改变右指针继续
             */
            if (array[mid] >= array[right]){
                right = mid;
            }else{
                left = mid;
            }
        }
        return array[mid];
    }

    private  int maxArray(int[] array, int left, int right) {
        int max = array[left];
        for (int i=left+1;i<array.length;i++){
            if (array[i] > max){
                max = array[i];
            }
        }
        return max;
    }

}


