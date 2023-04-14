package com.t.suanfa;

/**
 *
 * https://leetcode.cn/problems/range-sum-query-immutable/
 * 303. 区域和检索 - 数组不可变
 * 给定一个整数数组  nums，处理以下类型的多个查询:
 *
 * 计算索引 left 和 right （包含 left 和 right）之间的 nums 元素的 和 ，其中 left <= right
 * 实现 NumArray 类：
 *
 * NumArray(int[] nums) 使用数组 nums 初始化对象
 * int sumRange(int i, int j) 返回数组 nums 中索引 left 和 right 之间的元素的 总和 ，包含 left 和 right 两点（也就是 nums[left] + nums[left + 1] + ... + nums[right] )
 *
 *
 * 示例 1：
 *
 * 输入：
 * ["NumArray", "sumRange", "sumRange", "sumRange"]
 * [[[-2, 0, 3, -5, 2, -1]], [0, 2], [2, 5], [0, 5]]
 * 输出：
 * [null, 1, -1, -3]
 *
 * 解释：
 * NumArray numArray = new NumArray([-2, 0, 3, -5, 2, -1]);
 * numArray.sumRange(0, 2); // return 1 ((-2) + 0 + 3)
 * numArray.sumRange(2, 5); // return -1 (3 + (-5) + 2 + (-1))
 * numArray.sumRange(0, 5); // return -3 ((-2) + 0 + 3 + (-5) + 2 + (-1))
 */
public class Leecode303_sum {
    public static void main(String[] args) {
        int[] arr = {1,2,3,4,5,6,7};
        NumArray array = new NumArray(arr);
        System.out.println(array.sumRange(1,2));
        System.out.println(array.sumRange(2,4));
    }
}

class NumArray {
    int[] preSum;
    public NumArray(int[] nums) {
        preSum = new int[nums.length];
        int len = nums.length;
        preSum[0] = nums[0];
        for (int i = 1; i < len; i++) {
            preSum[i] = preSum[i-1] + nums[i];
        }
    }

    public int sumRange(int left, int right) {
        if(left == 0)
            return preSum[right];
        int result = preSum[right] - preSum[left-1];
        return result;
    }
}

class NumArray_1 {
    int[] nums;
    public NumArray_1(int[] nums) {
        this.nums = nums;
    }

    public int sumRange(int left, int right) {
        int result = 0;
        int cur = left;
        while(right > cur){
            result = result + nums[cur];
            cur ++;
        }
        return result;
    }
}
