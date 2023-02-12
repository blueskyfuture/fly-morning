package com.t.suanfa;

import java.util.Arrays;

/**
 *
 * 问题描述：
 * 现存在若干面值为1,5,11,20,50面值的硬币，问兑换总值为N面值有多少换钱方法？
 */
public class Test_Coin {
    //记录已经计算的结果，避免重复计算
    int[] resArr;
    int DEFAULT_VALUE = -666;
    int count01= 0;
    int count02 = 0;


    public static void main(String[] args) {
        Test_Coin demo = new Test_Coin();
//        int[] arr = {1,2,5};
//        int amount = 17;
        //demo.test04(arr,amount);
        int[] arr = {2,1,4};
        int amount = 9;
        int result = demo.test05(arr,amount);
        System.out.println("result--" + result);
    }


    private int test05(int[] arr, int amount) {
        resArr = new int[amount+1];
        Arrays.fill(resArr,DEFAULT_VALUE);
        int res = coinChange07(arr,amount);
        printArr(resArr);
        System.out.println("res="+res);
        return res;
    }

    private int coinChange07(int[] arr, int amount) {
        if(amount == 0)
            return 0;
        if(amount < 0)
            return -1;
        int res = Integer.MAX_VALUE;
        if(resArr[amount] != DEFAULT_VALUE)
            return resArr[amount];
        for (int coin : arr) {
            int sub = coinChange07(arr,amount-coin);
            if(sub == -1)
                continue;
            res = Math.min(res,sub+1);
        }
        res = res == Integer.MAX_VALUE ? -1 : res;
        resArr[amount] = res;
        return res;
    }


    private int coinChange06(int[] arr, int amount) {
        if(amount == 0)
            return 0;
        if(amount < 0)
            return -1;
        int res = Integer.MAX_VALUE;
        if(resArr[amount] != DEFAULT_VALUE)
            return resArr[amount];
        for (int coin : arr) {
            int sub = coinChange06(arr,amount-coin);
            //if(res != -1)
            if(sub == -1)
                continue;

            //res = Math.min(res,sub);
            res = Math.min(res,sub+1);
        }
        res = res == Integer.MAX_VALUE ? -1 : res;
        resArr[amount] = res;
        return res;
    }

    private int coinChange05(int[] arr, int amount) {
        if(amount==0)
            return 0;
        if(amount <0)
            return -1;
        int res = Integer.MAX_VALUE;
        if(resArr[amount] != DEFAULT_VALUE)
            return resArr[amount];

        for (int coin : arr) {
            int sub = coinChange05(arr,amount-coin);
            //if(res == -1)
            if(sub == -1)
                continue;
            res = Math.min(res,sub+1);
        }
        res = res == Integer.MAX_VALUE ? -1 : res;
        resArr[amount] = res;
        return res;
    }


    private int coinChange04(int[] arr, int amount){
        if(amount == 0)
            return 0;
        if(amount < 0)
            return -1;
        if(resArr[amount] != -666){
            return resArr[amount];
        }
        Integer res = Integer.MAX_VALUE;
        for (int curCoinNum : arr) {
            int subProblem = coinChange04(arr,amount - curCoinNum);
            if(subProblem == -1)
                continue;
            res = Math.min(res,subProblem+1);
        }
        res = res == Integer.MAX_VALUE ? -1 : res;
        resArr[amount] = res;
        return res;
    }

    /**
     * 方式1，https://liangyuanshao.blog.csdn.net/article/details/125713564
     * 其逻辑和方式二是一样的，通过一维数组实现
     * @param arr
     * @param amount
     * @return
     */
    private int test21(int[] arr, int amount) {
        int[] dp = new int[amount + 1];
        dp[0] = 1;
        for(int coin : arr){
            for (int i = coin; i < amount+1; i++) {
                dp[i] = dp[i] + dp[i - coin];
            }
        }
        return dp[amount];
    }

    /**
     * 方式2，参考https://blog.nowcoder.net/n/2b71850cf6b947e89aa5f5b17635d655
     * 通过二维数组方式实现
     * 找零的组合数量
     */
    private int test11(int[] arr, int amount) {
        //初始值判断
        if(arr.length == 0 || amount < 0)
            return 0;
        //定义二维数组dp[m][n],m为amount+1，n为币值种类，arr.len
        int m = arr.length;
        int n = amount+1;

        int[][] dp = new int[m][n];//默认初始化各元素值都为0

        //初始化第一列，即当要组成总额为0时的种类,都已1中方式，都不拿
        for (int i = 0; i < m; i++) {
            dp[i][0] = 1;
        }

        //初始化第1行，金额是第1个币值的倍数时候为1
        int j = 1;
        while(arr[0] * j <= amount){
            dp[0][arr[0]*j] = 1;
            j ++;
        }

        for (int y = 1; y < m; y++) {//纵轴
            for (int x = 1; x < n; x++) {//横轴
                if(x >= arr[y])
                    dp[y][x] = dp[y-1][x] + dp[y][x-arr[y]];
                else
                    dp[y][x] = dp[y-1][x];
            }
        }
        return dp[m-1][n-1];
    }




    private int test10(int[] arr, int amount) {
        //初始值判断
        if(arr.length == 0 || amount < 0)
            return 0;
        //定义二维数组dp[m][n],m为amount+1，n为币值种类，arr.len
        int m = amount+1;
        int n = arr.length;
        int[][] dp = new int[m][n];//默认初始化各元素值都为0

        //初始化第一列，即当要组成总额为0时的种类,都已1中方式，都不拿
        for (int i = 0; i < n; i++) {
            dp[i][0] = 1;
        }

        //初始化第1行，金额是第1个币值的倍数时候为1
        int j = 1;
        while(arr[0] * j <= amount){
            dp[0][arr[0]*j] = 1;
            j ++;
        }

        for (int y = 0; y < n; y++) {//纵轴
            for (int x = 0; x < m; x++) {//横轴
                if(x >= arr[x])
                    dp[y][x] = dp[y-1][x] + dp[y][x-arr[x]];
                else
                    dp[y][x] = dp[y-1][x];
            }
        }
        return dp[n-1][m];
    }



    /**
     *该方法解决的问题需要进一步考证，不是找零组合方式多少的问题解决
     * 以下为方式1，参考地址待查
     * -->0[-666]-->1[1]-->2[1]-->3[2]-->4[2]-->5[1]-->6[2]-->7[2]-->8[3]-->9[3]-->10[2]-->11[3]-->12[3]-->13[4]-->14[4]-->15[3]-->16[4]-->17[4]
     * @param arr   {1,2,5};
     * @param amount  17
     * @return
     */
    private int test04(int[]arr,int amount){
        resArr = new int[amount+1];
        Arrays.fill(resArr,-666);
        int res = coinChange04(arr,amount);
        printArr(resArr);
        System.out.println("res="+res);
        return res;
    }



    private int test02(int[] arr,int amount){
        resArr = new int[amount+1];
        Arrays.fill(resArr,-666);
        int i = coinChange02(arr, amount);
        printArr(resArr);
        System.out.println("count01==>"+count01);
        System.out.println("count02==>"+count02);
        System.out.println("i==" + i);
        return i;
    }

    private int coinChange02(int[] arr,int amount){
        count01++;
        if(amount==0)
            return 0;
        if (amount<0)
            return -1;
        if(resArr[amount]!=-666)
            return resArr[amount];
        int res = Integer.MAX_VALUE;
        for (Integer num : arr) {
            count02++;
            int subRes = coinChange02(arr, amount-num);
            if(subRes==-1)
                continue;
            res = Math.min(res,subRes+1);
        }
        res = res == Integer.MAX_VALUE ? -1 : res;
        resArr[amount] = res;
        return res;
    }

    private void printArr(int[] arr){
        for (int i = 0; i < arr.length; i++) {
            System.out.print("-->"+i+"["+arr[i]+"]");
        }
        System.out.println();
    }

    private int test01(int[] arr,int amount){
        int i = coinChange01(arr, amount);
        System.out.println("i==" + i);

        System.out.println("count01==>"+count01);
        System.out.println("count02==>"+count02);
        return i;
    }



    /**
     * 有重复计算，复杂度高
     * @param arr
     * @param amount
     * @return
     */
    private int coinChange01(int[] arr,int amount){
        count01++;
        System.out.println("amount"+ amount);
        if(amount==0)
            return 0;
        if(amount<0)
            return -1;
        int res01 = Integer.MAX_VALUE;
        for (Integer num : arr) {
            count02++;
            int subPro = coinChange01(arr,amount - num);
            if(subPro==-1)
                continue;
            res01 = Math.min(res01,subPro+1);
        }
        return res01 == Integer.MAX_VALUE ? -1 : res01;
    }

    /**
     * 有重复计算，复杂度高
     * @param arr
     * @param amount
     * @return
     */
    private int coinChange(int[] arr, int amount){
        if(amount == 0)
            return 0;
        if(amount < 0)
            return -1;

        int res = Integer.MAX_VALUE;
        for (Integer num : arr) {
            int subProblem = coinChange(arr,amount - num);
            if(subProblem == -1)
                continue;
            res = Math.min(res,subProblem+1);
        }
        return res == Integer.MAX_VALUE ? -1 : res;
    }

}
