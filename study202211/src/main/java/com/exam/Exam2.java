package com.exam;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * aba,aab ==>true
 * abc,cab ==>true
 */
public class Exam2 {

    public static void main(String[] args) {
        String str1 = "abc";
        String str2 = "cab";
        boolean equals = isEquals2(str1, str2);
        System.out.println("result = "+equals);
    }

    private static boolean isEquals2(String ss1, String ss2) {
        char[] cc1 = ss1.toCharArray();
        char[] cc2 = ss2.toCharArray();
        Arrays.sort(cc1);
        Arrays.sort(cc2);
        ss1 = new String(cc1);
        ss2 = new String(cc2);
        boolean result = ss1.equals(ss2);
        System.out.println("ss1:"+ss1+"--ss2:"+ss2+";result="+result);
        return result;
    }

    /**
     * 代码中的类名、方法名、参数名已经指定，请勿修改，直接返回方法规定的值即可
     *
     * 比较给定的两个字符串是否相等
     * @param str1 string字符串
     * @param str2 string字符串
     * @return bool布尔型
     */
    public static boolean isEquals (String str1, String str2) {
        if(str1.length() != str2.length())
            return false;
        // write code here
        char[] str1Char = str1.toCharArray();
        char[] str2Char = str2.toCharArray();
        Map<Character,Integer> map = new HashMap<>();
        for(int i = 0; i<str1Char.length;i++){
            Character ch = str1Char[i];
            if(map.containsKey(ch)){
                int count = map.get(ch);
                map.put(ch,count+1);
            }else{
                map.put(ch,1);
            }
        }

        for(int i = 0; i<str2Char.length;i++){
            Character ch = str2Char[i];
            if(map.containsKey(ch)){
                int count = map.get(ch);
                count = count -1;
                if(count >=0 ){
                    map.put(ch,count);
                }else{
                    return false;
                }
            }else{
                return false;
            }
        }

        for(Character ch : map.keySet()){
            if(map.get(ch) != 0)
                return false;
        }

        return true;

    }
}