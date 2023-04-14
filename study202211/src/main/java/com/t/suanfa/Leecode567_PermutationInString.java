package com.t.suanfa;

import java.util.Arrays;

/**
 * https://leetcode.cn/problems/permutation-in-string/
 * 567. 字符串的排列
 * 给你两个字符串 s1 和 s2 ，写一个函数来判断 s2 是否包含 s1 的排列。如果是，返回 true ；否则，返回 false 。
 *
 * 换句话说，s1 的排列之一是 s2 的 子串 。
 *
 *
 *
 * 示例 1：
 *
 * 输入：s1 = "ab" s2 = "eidbaooo"
 * 输出：true
 * 解释：s2 包含 s1 的排列之一 ("ba").
 * 示例 2：
 *
 * 输入：s1= "ab" s2 = "eidboaoo"
 * 输出：false
 *
 *
 * 提示：
 *
 * 1 <= s1.length, s2.length <= 104
 * s1 和 s2 仅包含小写字母
 */
public class Leecode567_PermutationInString {

    public boolean checkInclusion(String s1, String s2) {
        int len1 = s1.length();
        int len2 = s2.length();
        int left = 0;
        int right = len1;
        String cur = "";
        while (right <= len2){
            cur = s2.substring(left,right);
            boolean check = check(s1, cur);
            if(check)
                return true;
            left ++;
            right ++;
        }
        return false;
    }

    public boolean check(String str1, String str2){
        if(str1.length() != str2.length())
            return false;
        char[] chars1 = str1.toCharArray();
        char[] chars2 = str2.toCharArray();
        Arrays.sort(chars1);
        Arrays.sort(chars2);
        for (int i = 0; i < chars1.length; i++) {
            if(chars1[i] != chars2[i])
                return false;
        }
        return true;
    }

    /**
     * 官网写法
     * 作者：LeetCode-Solution
     *     链接：https://leetcode.cn/problems/permutation-in-string/solution/zi-fu-chuan-de-pai-lie-by-leetcode-solut-7k7u/
     *     来源：力扣（LeetCode）
     *     著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     * @param s1
     * @param s2
     * @return
     */
    public boolean checkInclusion1(String s1, String s2) {
        int n = s1.length(), m = s2.length();
        if (n > m) {
            return false;
        }
        int[] cnt1 = new int[26];
        int[] cnt2 = new int[26];
        for (int i = 0; i < n; ++i) {
            ++cnt1[s1.charAt(i) - 'a'];
            ++cnt2[s2.charAt(i) - 'a'];
        }
        if (Arrays.equals(cnt1, cnt2)) {
            return true;
        }
        for (int i = n; i < m; ++i) {
            ++cnt2[s2.charAt(i) - 'a'];
            --cnt2[s2.charAt(i - n) - 'a'];
            if (Arrays.equals(cnt1, cnt2)) {
                return true;
            }
        }
        return false;
    }



    public static void main(String[] args) {
        String str1 = "adc";
        String str2 = "dcda";
        Leecode567_PermutationInString test = new Leecode567_PermutationInString();
        final boolean check = test.checkInclusion(str1, str2);
        System.out.println("check:" + check);
    }
}
