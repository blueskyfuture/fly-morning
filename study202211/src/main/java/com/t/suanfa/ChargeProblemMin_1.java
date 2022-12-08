package com.t.suanfa;

import java.util.Arrays;

public class ChargeProblemMin_1 {


    public static void main(String[] args) {
        int am = 9;
        int[] coinArr = {2,1,4};
        Arrays.sort(coinArr);
        int result = solution(am,coinArr);
        System.out.println("result==" + result);
    }

    private static int solution(int am, int[] coinArr) {
        int coinLen = coinArr.length;
        int[][] arr = new int[coinLen+1][am+1];
        for (int i = 0; i < coinLen; i++) {
            arr[i][0] = 0;
        }
        for (int i = 1; i <= am; i++) {
            arr[0][i] = 111;
        }
        for (int i = 1; i <= am ; i++) {
            for (int j = 1; j <= coinLen; j++) {
                if(coinArr[j-1] > i){
                    arr[j][i] = arr[j-1][i];
                    continue;
                }
                //不包含
                int exclu = arr[j-1][i];
                //包含
                int inclu = arr[j][i-coinArr[j-1]]+1;
                arr[j][i] = exclu > inclu ? inclu : exclu ;
            }

        }
        return arr[coinLen][am];
    }
}
