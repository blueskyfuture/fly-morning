package com.t.suanfa;

import java.util.Arrays;

import java.util.Scanner;

public class ChargeProblem_2 {
    static int n,m,ans=0;
    static int[] arr;
    public static void main(String[] args) {
        //test01();

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

