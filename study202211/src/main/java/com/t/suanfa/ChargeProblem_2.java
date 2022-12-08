package com.t.suanfa;

import java.util.Arrays;

import java.util.Scanner;

/**
 * 找零问题，有多少种组合
 */
public class ChargeProblem_2 {
    static int n,m,ans=0;
    static int[] arr;
    public static void main(String[] args) {
        //test01();
        //int[] arr = {2,1,4};
        int[] arr = {2,1};
        test03(arr,4);
    }

    private static void test03(int[] arr, int am) {
        int[] dp = new int[am+1];
        dp[0] = 1;
        for (int i = 0; i < arr.length; i++) {
            for (int j = arr[i]; j <= am; j++) {
                dp[j] = dp[j] + dp[j-arr[i]];
            }
        }
        System.out.println("dp["+am+"]="+dp[am]);
    }


    /**
     *
     * @param arr 币值种类
     * @param n 找零总额
     */
    private static void test02(int[] arr,int n) {
        int[] dp = new int[n + 1];
        dp[0] = 1;
        for (int coin : arr) {
            for (int i = coin; i <= n; i++) {
                dp[i] += dp[i - coin];
            }
        }
        System.out.println( dp[n]);
    }

    private static void test01() {
        System.out.println("请输入找零金额和币值类型数量：");
        Scanner s=new Scanner(System.in);
        n=s.nextInt();
        m=s.nextInt();
        arr=new int[m];
        System.out.println("请输入不同种类币值面额：");
        for(int i=0;i<m;i++){
            arr[i]=s.nextInt();
        }
        int[] dp = new int[n + 1];
        dp[0] = 1;
        for (int coin : arr) {
            for (int i = coin; i <= n; i++) {
                dp[i] += dp[i - coin];
            }
        }
        System.out.println( dp[n]);
    }
}

