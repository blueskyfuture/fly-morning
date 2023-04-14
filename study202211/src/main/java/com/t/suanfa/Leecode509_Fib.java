package com.t.suanfa;

/**
 * https://leetcode.cn/problems/fibonacci-number/
 * 509. 斐波那契数
 * 斐波那契数 （通常用 F(n) 表示）形成的序列称为 斐波那契数列 。该数列由 0 和 1 开始，后面的每一项数字都是前面两项数字的和。也就是：
 *
 * F(0) = 0，F(1) = 1
 * F(n) = F(n - 1) + F(n - 2)，其中 n > 1
 * 给定 n ，请计算 F(n) 。
 *
 *
 *
 * 示例 1：
 *
 * 输入：n = 2
 * 输出：1
 * 解释：F(2) = F(1) + F(0) = 1 + 0 = 1
 * 示例 2：
 *
 * 输入：n = 3
 * 输出：2
 * 解释：F(3) = F(2) + F(1) = 1 + 1 = 2
 * 示例 3：
 *
 * 输入：n = 4
 * 输出：3
 * 解释：F(4) = F(3) + F(2) = 2 + 1 = 3
 */
public class Leecode509_Fib {
    public static void main(String[] args) {
        Leecode509_Fib fib = new Leecode509_Fib();
        final int fib1 = fib.fib(10);
        System.out.println("fib1=" + fib1);
    }
    public int fib(int n) {
        int[] memo = new int[n+1];
        int res = dp(n,memo);
        return res;
    }

    public int dp(int n,int[] memo){
        if(n==0 || n==1) return n;
        if(memo[n] != 0) return memo[n];
        int res = dp(n-1,memo) + dp(n-2,memo);
        memo[n] = res;
        return res;
    }
}
