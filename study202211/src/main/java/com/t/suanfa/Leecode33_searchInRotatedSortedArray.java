package com.t.suanfa;

/**
 * https://leetcode.cn/problems/search-in-rotated-sorted-array/
 * 33. 搜索旋转排序数组
 *         整数数组 nums 按升序排列，数组中的值 互不相同 。
 *         在传递给函数之前，nums 在预先未知的某个下标 k（0 <= k < nums.length）上进行了 旋转，使数组变为 [nums[k], nums[k+1], ..., nums[n-1], nums[0], nums[1], ..., nums[k-1]]（下标 从 0 开始 计数）。例如， [0,1,2,4,5,6,7] 在下标 3 处经旋转后可能变为 [4,5,6,7,0,1,2] 。
 *         给你 旋转后 的数组 nums 和一个整数 target ，如果 nums 中存在这个目标值 target ，则返回它的下标，否则返回 -1 。
 *         你必须设计一个时间复杂度为 O(log n) 的算法解决此问题。
 *
 *         示例 1：
 *         输入：nums = [4,5,6,7,0,1,2], target = 0
 *         输出：4
 *
 *         示例 2：
 *         输入：nums = [4,5,6,7,0,1,2], target = 3
 *         输出：-1
 *
 *         示例 3：
 *         输入：nums = [1], target = 0
 *         输出：-1
 */
public class Leecode33_searchInRotatedSortedArray {

    public static void main(String[] args) {
        int[] arr = {0,1,2,3,4,5,6};
//        int[] rotatedArr = {5,6,0,1,2,3,4};
//        int[] rotatedArr = {4,5,6,7,0,1,2};
        int[] rotatedArr = {3,1};
        int target = 1;
//        int res = findTarget(arr, target);
        int res2 = searchInRotatedSortedArray_1(rotatedArr, target);
        System.out.println("res2:" + res2);
    }

    private static int searchInRotatedSortedArray_1(int[] arr, int target) {
        if(arr == null || arr.length == 0 )
            return -1;
        if(arr.length == 1 && arr[0] == target)
            return 0;
        int low = 0;
        int high = arr.length - 1;
        int mid = -1;
        while (low <= high){
            mid = low + (high - low) / 2;
            if(arr[mid] == target)
                return mid;
            //先判断数组的左半部分是有序的还是数组右半部分是有序的
            //if(arr[low] < arr[mid]){//数组的左半部分是有序递增的
            if(arr[low] <= arr[mid]){//数组的左半部分是有序递增的，是小于等于，调试了半天

                if(target >= arr[low] && target < arr[mid]){
                    high = mid -1;
                }else{
                    low = mid + 1;
                }
            }else{//数组的右半部分是有序递增的

                if(target > arr[mid] && target <= arr[high]){
                    low = mid + 1;
                }else{
                    high = mid -1;
                }
            }
        }
        return -1;
    }

    public int searchInRotatedSortedArray(int[] nums, int target) {
        int n = nums.length;
        if (n == 0) {
            return -1;
        }
        if (n == 1) {
            return nums[0] == target ? 0 : -1;
        }
        int l = 0, r = n - 1;
        while (l <= r) {
            int mid = (l + r) / 2;
            if (nums[mid] == target) {
                return mid;
            }
            if (nums[0] <= nums[mid]) {
                if (nums[0] <= target && target < nums[mid]) {
                    r = mid - 1;
                } else {
                    l = mid + 1;
                }
            } else {
                if (nums[mid] < target && target <= nums[n - 1]) {
                    l = mid + 1;
                } else {
                    r = mid - 1;
                }
            }
        }
        return -1;
    }

    /**
     * 有序数组，为旋转进行二分查找法
     * @param arr
     * @param target
     * @return
     */
    private static int findTarget(int[] arr, int target) {
        if(arr == null || arr.length == 0)
            return -1;
        if(arr.length == 1 && arr[0] == target)
            return 0;
        int low = 0;
        int high = arr.length -1;
        int mid = -1;
        while (low <= high){
            mid = low + (high - low) / 2;
            if(arr[mid] == target)
                return mid;
            if(arr[mid] < target)
                low = mid + 1;
            else
                high = mid -1;
        }
        return -1;
    }
}
