package com.t.suanfa;

/**
 * 采用该方式
 */
public class Test_MaxSubstring_02 {
    public static void main(String[] args) {
        String str1 = "bab";
        String str2 = "cabab";
        String result = Test_MaxSubstring_02.maxSubstring1(str1, str2);
        System.out.println(result);
    }

    private static String maxSubstring1(String str1, String str2) {
        if(str1 == null || str2 == null)
            return null;
        if(str1 == null || str2 == null)
            return null;
        int len1 = str1.length();
        int len2 = str2.length();
        int maxlen = 0;
        int pos = 0;
        int[] cur = new int[len1];
        int[] top = new int[len1];
        //for (int i = 0; i < len1; i++) {
        for (int i = 0; i < len2; i++) {
            char ch = str2.charAt(i);
            //for (int j = 0; j < len2; j++) {
            for (int j = 0; j < len1; j++) {
                if(ch == str1.charAt(j)){ //{}
                    if(j == 0){
                        //cur[i] = 1;
                        cur[j] = 1;
                    }else {
                        //cur[i] = cur[i-1] + 1;
                        //cur[i] = top[i-1] + 1;
                        cur[j] = top[j-1] + 1;
                    }

                    //if(cur[i] > maxlen){
                    if(cur[j] > maxlen){
                    //maxlen = cur[i];
                     maxlen = cur[j];
                    //pos = i;
                    pos = j;
                }
            }
            }
            for (int j = 0; j < len1; j++) {
                top[j] = cur[j];
                cur[j] = 0;
            }
        }
        String res = str1.substring(pos - maxlen + 1, pos + 1);
        return res;
    }



    // 求解两个字符号的最长公共子串
    public static String maxSubstring(String strOne, String strTwo) {
        // 参数检查
        if (strOne == null || strTwo == null) {
            return null;
        }
        if (strOne.equals("") || strTwo.equals("")) {
            return null;
        }
        // 矩阵的横向长度
        int len1 = strOne.length();
        // 矩阵的纵向长度
        int len2 = strTwo.length();

        // 保存矩阵的上一行
        int[] topLine = new int[len1];
        // 保存矩阵的当前行
        int[] currentLine = new int[len1];
        // 矩阵元素中的最大值
        int maxLen = 0;
        // 矩阵元素最大值出现在第几列
        int pos = 0;
        char ch = ' ';
        for (int i = 0; i < len2; i++) {
            ch = strTwo.charAt(i);
            // 遍历str1，填充当前行的数组
            for (int j = 0; j < len1; j++) {
                if (ch == strOne.charAt(j)) {
                    // 如果当前处理的是矩阵的第一列，单独处理，因为其坐上角的元素不存在
                    if (j == 0) {
                        currentLine[j] = 1;
                    } else {
                        currentLine[j] = topLine[j - 1] + 1;
                    }
                    if (currentLine[j] > maxLen) {
                        maxLen = currentLine[j];
                        pos = j;
                    }
                }
            }
            // 将矩阵的当前行元素赋值给topLine数组; 并清空currentLine数组
            for (int k = 0; k < len1; k++) {
                topLine[k] = currentLine[k];
                currentLine[k] = 0;
            }
            // 或者采用下面的方法
            // topLine = currentLine;
            // currentLine = new int[len1];
        }
        return strOne.substring(pos - maxLen + 1, pos + 1);
    }

}
