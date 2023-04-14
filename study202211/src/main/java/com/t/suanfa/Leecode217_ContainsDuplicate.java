package com.t.suanfa;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * https://leetcode.cn/problems/contains-duplicate/
 *
 * 给你一个整数数组 nums 。如果任一值在数组中出现 至少两次 ，返回 true ；如果数组中每个元素互不相同，返回 false 。
 *  
 *
 * 示例 1：
 *
 * 输入：nums = [1,2,3,1]
 * 输出：true
 * 示例 2：
 *
 * 输入：nums = [1,2,3,4]
 * 输出：false
 * 示例 3：
 *
 * 输入：nums = [1,1,1,3,3,4,3,2,4,2]
 * 输出：true
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/contains-duplicate
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class Leecode217_ContainsDuplicate {

    public static void main(String[] args) {
        int[] arr = {0,1,2,3,4,5,6,7,8,9,9,9,9,13,19,17,27};
        boolean b = Leecode217_ContainsDuplicate.containsDuplicate(arr);
        System.out.println("res:" + b);
    }

    public static boolean containsDuplicate(int[] nums) {
        Arrays.sort(nums);
        Map<Integer,Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length -1; i++) {
            if(nums[i] == nums[i+1])
                return true;
        }
        return false;
    }
}
