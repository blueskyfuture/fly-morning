package com.bluesky.teck.leecode;

import java.util.*;

/**
 给你一个包含 n 个整数的数组nums，判断nums中是否存在三个元素 a，b，c ，使得a + b + c = 0 ？请你找出所有和为 0 且不重复的三元组。

 注意：答案中不可以包含重复的三元组。

 示例 1：
 输入：nums = [-1,0,1,2,-1,-4]
 输出：[[-1,-1,2],[-1,0,1]]

 示例 2：
 输入：nums = []
 输出：[]

 示例 3：
 输入：nums = [0]
 输出：[]

 链接：https://leetcode-cn.com/problems/3sum
 */
public class Leetcode_15 {
        public static void main(String[] args) {
            Leetcode_15 test = new Leetcode_15();
            int[] nums = {-1,0,1,2,-1,-4};
            List<List<Integer>> lists = test.threeSum(nums);
            for (List<Integer> li : lists ) {
                for (Integer i : li ) {
                    System.out.print(i+",");
                }
                System.out.println();
            }
        }

        public List<List<Integer>> threeSum(int[] nums) {
            // 长度
            int len = nums.length;
            // 排序
            Arrays.sort(nums);
            List<List<Integer>> lists = new ArrayList<>();
            for (int i = 0; i < len; i++) {
                // 如果当前值大于0, 那么L和R指向的数都大于0, 三数之和就不可能为0
                if (nums[i] > 0) return lists;
                if (i > 0 && nums[i] == nums[i - 1]) continue;
                int curr = nums[i];
                int L = i + 1;
                int R = len - 1;
                while (L < R) {
                    int sum = curr + nums[L] + nums[R];
                    if (sum == 0) {
                        List<Integer> list = new ArrayList<>();
                        list = Arrays.asList(curr, nums[L], nums[R]);
                        lists.add(list);
                        // 避免重复
                        while (L < R && nums[L] == nums[L + 1]) L++;
                        while (L < R && nums[R] == nums[R - 1]) R--;
                        L++;
                        R--;
                    } else if (sum < 0) {
                        L++;
                    } else {
                        R--;
                    }
                }
            }
            return lists;
        }
    }
