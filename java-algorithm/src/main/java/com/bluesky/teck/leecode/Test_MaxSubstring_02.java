package com.bluesky.teck.leecode;

/**
 * 采用该方式
 */
public class Test_MaxSubstring_02 {

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

    public static void main(String[] args) {
        String str1 = "bab";
        String str2 = "cabab";
        String result = Test_MaxSubstring_02.maxSubstring(str1, str2);
        System.out.println(result);
    }
}
