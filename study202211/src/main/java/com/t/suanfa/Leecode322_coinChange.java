package com.t.suanfa;

import java.util.Arrays;

/**
 * 参考：https://leetcode.cn/problems/coin-change/
 * 讲解：https://labuladong.gitee.io/algo/di-er-zhan-a01c6/dong-tai-g-a223e/dong-tai-g-1e688/
 * 322. 零钱兑换
 * 给你一个整数数组 coins ，表示不同面额的硬币；以及一个整数 amount ，表示总金额。
 *
 * 计算并返回可以凑成总金额所需的 最少的硬币个数 。如果没有任何一种硬币组合能组成总金额，返回 -1 。
 *
 * 你可以认为每种硬币的数量是无限的。
 *
 * 示例 1：
 * 输入：coins = [1, 2, 5], amount = 11
 * 输出：3
 * 解释：11 = 5 + 5 + 1
 *
 * 示例 2：
 * 输入：coins = [2], amount = 3
 * 输出：-1
 *
 * 示例 3：
 * 输入：coins = [1], amount = 0
 * 输出：0
 */
public class Leecode322_coinChange {

    public int coinChange(int[] coins, int amount) {
        int[] memo = new int[amount + 1];
        Arrays.fill(memo,-666);
        int res = dp(amount,coins,memo);
        return res;
    }

    public int dp(int amount, int[] coin, int[] memo){
        if(amount<0) return -1;
        if(amount==0) return 0;

        if(memo[amount] != -666) return memo[amount];
        int res = Integer.MAX_VALUE;
        for (int i = 0; i < coin.length; i++) {
            int sub = dp(amount-coin[i],coin,memo);
            if(sub == -1) continue;
            res = Math.min(res,sub+1);
        }
        if(res == Integer.MAX_VALUE)
            res = -1;
        memo[amount] = res;
        return  res;
    }

    public static void main(String[] args) {
        Leecode322_coinChange test = new Leecode322_coinChange();
        int amout = 11;
        int[] coins = {1,2,5};
        final int solution = test.coinChange( coins,amout);
        System.out.println("solution:" + solution);
    }

}
