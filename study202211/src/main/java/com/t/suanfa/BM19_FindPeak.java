package com.t.suanfa;

/**
 * 描述
 * 给定一个长度为n的数组nums，请你找到峰值并返回其索引。数组可能包含多个峰值，在这种情况下，返回任何一个所在位置即可。
 * 1.峰值元素是指其值严格大于左右相邻值的元素。严格大于即不能有等于
 * 2.假设 nums[-1] = nums[n] = -\infty−∞
 * 3.对于所有有效的 i 都有 nums[i] != nums[i + 1]
 * 4.你可以使用O(logN)的时间复杂度实现此问题吗？
 * 如输入[2,4,1,2,7,8,4]时，会形成两个山峰，一个是索引为1，峰值为4的山峰，另一个是索引为5，峰值为8的山峰;
 * 
 * 示例1
 * 输入：
 * [2,4,1,2,7,8,4]

 * 返回值：
 * 1

 * 说明：
 * 4和8都是峰值元素，返回4的索引1或者8的索引5都可以     
 * 示例2
 * 输入：
 * [1,2,3,1]

 * 返回值：
 * 2

 * 说明：
 * 3 是峰值元素，返回其索引 2
 *
 *
 *
 * 知识点：分治
 *
 * 分治即“分而治之”，“分”指的是将一个大而复杂的问题划分成多个性质相同但是规模更小的子问题，子问题继续按照这样划分，直到问题可以被轻易解决；“治”指的是将子问题单独进行处理。经过分治后的子问题，需要将解进行合并才能得到原问题的解，因此整个分治过程经常用递归来实现。
 *
 * 思路：
 *
 * 因为题目将数组边界看成最小值，而我们只需要找到其中一个波峰，因此只要不断地往高处走，一定会有波峰。那我们可以每次找一个标杆元素，将数组分成两个区间，每次就较高的一边走，因此也可以用分治来解决，而标杆元素可以选择区间中点。
 *
 * //右边是往下，不一定有坡峰
 * if(nums[mid] > nums[mid + 1])
 *     right = mid;
 * //右边是往上，一定能找到波峰
 * else
 *     left = mid + 1;
 * 具体做法：
 *
 * step 1：二分查找首先从数组首尾开始，每次取中间值，直到首尾相遇。
 * step 2：如果中间值的元素大于它右边的元素，说明往右是向下，我们不一定会遇到波峰，但是那就往左收缩区间。
 * step 3：如果中间值大于右边的元素，说明此时往右是向上，向上一定能有波峰，那我们往右收缩区间。
 * step 4：最后区间收尾相遇的点一定就是波峰。
 */
public class BM19_FindPeak {

    public static void main(String[] args) {
        //int[] nums = {82,76,21,18,35,64,60,47,65,15};
        //int[] nums = {82,76,21,18,35,64,60,47,65,68,72};
        //int[] nums = {56,46,21};
        //int[] nums = {82,76,21,18,35,36,32,60,65,68,72};
        int[] nums = {5};//自己就是波峰
        int peakElement = findPeakElement1(nums);
        System.out.println(peakElement+"---"+ nums[peakElement]);
    }


    private static int findPeakElement1(int[] nums) {
        int left = 0;
        int right = nums.length - 1;
        while (left < right){
            int mid = (left + right)/2;
            if(nums[mid] > nums[mid + 1]){
                //right = mid - 1;
                right = mid;
            }else {
                //left = right + 1;
                left = mid + 1;
            }
        }
        return right;
    }


    public static int findPeakElement (int[] nums) {
        int left = 0;
        int right = nums.length - 1;
        //二分法
        while(left < right){
            int mid = (left + right) / 2;
            //右边是往下，不一定有坡峰
            if(nums[mid] > nums[mid + 1])
                right = mid;
                //右边是往上，一定能找到波峰
            else
                left = mid + 1;
        }
        //其中一个波峰
        return right;
    }
}

