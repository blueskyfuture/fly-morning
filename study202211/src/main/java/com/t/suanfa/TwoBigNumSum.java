package com.t.suanfa;

/**
 * 描述
 * 以字符串的形式读入两个数字，编写一个函数计算它们的和，以字符串形式返回。
 *
 * 数据范围：s.length,t.length \le 100000s.length,t.length≤100000，字符串仅由'0'~‘9’构成
 * 要求：时间复杂度 O(n)O(n)
 * 示例1
 * 输入：
 * "1","99"
 * 
 * 返回值：
 * "100"
 * 
 * 说明：
 * 1+99=100       
 * 示例2
 * 输入：
 * "114514",""
 * 
 * 返回值：
 * "114514"
 */
public class TwoBigNumSum {


    public static void main(String[] args) {
        String str1 = "123456789";
        String str2 = "987654321";
        System.out.println(solve(str1,str2));

    }
    /**
     * 代码中的类名、方法名、参数名已经指定，请勿修改，直接返回方法规定的值即可
     * 计算两个数之和
     * @param s string字符串 表示第一个整数
     * @param t string字符串 表示第二个整数
     * @return string字符串
     */
    public static String solve (String s, String t) {
        // write code here
        char[] num1 = s.toCharArray();
        char[] num2 = t.toCharArray();
        char[] res = new char[Math.max(s.length(),t.length())+1];
        int i = s.length()-1;
        int j = t.length()-1;
        int index = res.length-1;
        int temp = 0;
        while(i>=0||j>=0||temp==1){
            if(i>=0){
                temp += num1[i]-'0';
                i--;
            }
            if(j>=0){
                temp += num2[j]-'0';
                j--;
            }
            res[index] = (char)(temp%10 + '0');
            index--;
            temp/=10;
        }
        String str = index==0 ? String.valueOf(res).substring(1):String.valueOf(res);
        return str;
    }
}
