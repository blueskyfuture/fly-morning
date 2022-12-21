package com.t.suanfa;

import java.util.Arrays;

/**
 * 给你一个按照非递减顺序排列的整数数组 nums，和一个目标值 target。请你找出给定目标值在数组中的开始位置和结束位置。
 *
 * 如果数组中不存在目标值 target，返回[-1, -1]。
 *
 * 你必须设计并实现时间复杂度为 O(log n)的算法解决此问题。
 *
 *
 *
 * 示例 1：
 *
 * 输入：nums = [5,7,7,8,8,10], target = 8
 * 输出：[3,4]
 * 示例2：
 *
 * 输入：nums = [5,7,7,8,8,10], target = 6
 * 输出：[-1,-1]
 * 示例 3：
 *
 * 输入：nums = [], target = 0
 * 输出：[-1,-1]
 *
 *
 * 提示：
 *
 * 0 <= nums.length <= 105
 * -109<= nums[i]<= 109
 * nums是一个非递减数组
 * -109<= target<= 109
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/find-first-and-last-position-of-element-in-sorted-array
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class Leecode34_FindFirstAndLastIndex {

    public static void main(String[] args) {
        int[] arr = {0,1,2,3,4,5,6,7,8,9,9,9,9,13,19,17,27};
        int[] result = searchRange1(arr, 9);
        System.out.println("result:" + Arrays.toString(result));
    }

    private static int[] searchRange1(int[] arr, int target) {
        if(arr.length == 0) return new int[]{-1,-1};
        int l = 0;
        int len = arr.length;
        int r = len - 1;
        while (l < r){
            int mid = l + (r - l)/2;
            int mid0 = (l + r)/2;
            System.out.println("mid=" + mid +",mid0=" +mid0);
            System.out.println(arr[mid]);
            if(arr[mid] >= target)
                r = mid;
            else
                l = mid + 1;
        }
        if(arr[r] != target) return new int[]{-1,-1};
        //如果arr[r] == target，说明找到了最右边的目标值
        int L = r;
        l = 0;
        r = len - 1;
        while (l < r){
           // int mid = (l + r + 1)/2;
            int mid = (l + r + 1)/2;
            if(arr[mid] <= target)
                l = mid;
            else
                r = mid - 1;
        }
        System.out.println(arr[r]);
        return new int[]{L,r};



    }


    public static int[] searchRange(int[] nums, int target) {
        if(nums.length == 0) return new int[]{-1,-1};

        int l = 0, r = nums.length - 1; //二分范围
        while( l < r)			        //查找元素的开始位置
        {
            int mid = (l + r )/2;
            if(nums[mid] >= target) r = mid;
            else l = mid + 1;
        }
        if( nums[r] != target) return new int[]{-1,-1}; //查找失败
        int L = r;
        l = 0; r = nums.length - 1;     //二分范围
        while( l < r)			        //查找元素的结束位置
        {
            int mid = (l + r + 1)/2;
            if(nums[mid] <= target ) l = mid;
            else r = mid - 1;
        }
        return new int[]{L,r};
    }
}
