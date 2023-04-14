package com.t.suanfa;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * https://leetcode.cn/problems/candy/
 *
 * 135. 分发糖果
 * n 个孩子站成一排。给你一个整数数组 ratings 表示每个孩子的评分。
 *
 * 你需要按照以下要求，给这些孩子分发糖果：
 *
 * 每个孩子至少分配到 1 个糖果。
 * 相邻两个孩子评分更高的孩子会获得更多的糖果。
 * 请你给每个孩子分发糖果，计算并返回需要准备的 最少糖果数目 。
 *
 *
 *
 * 示例 1：
 *
 * 输入：ratings = [1,0,2]
 * 输出：5
 * 解释：你可以分别给第一个、第二个、第三个孩子分发 2、1、2 颗糖果。
 * 示例 2：
 *
 * 输入：ratings = [1,2,2]
 * 输出：4
 * 解释：你可以分别给第一个、第二个、第三个孩子分发 1、2、1 颗糖果。
 *      第三个孩子只得到 1 颗糖果，这满足题面中的两个条件。
 *
 *
 * 提示：
 *
 * n == ratings.length
 * 1 <= n <= 2 * 104
 * 0 <= ratings[i] <= 2 * 104
 */
public class Leecode135_Candy {

    public static void main(String[] args) {
        int[] arr = {0,1,2,3,4,5,6,7,8,9,9,9,9,13,19,17,27};
    }

    public int candy(int[] ratings) {
        int length = ratings.length;
        int ret = 1;
        int pre = 1;
        int inc = 1,dec = 0;
        for(int i = 1;i < length;i++){
            if(ratings[i] >= ratings[i-1]){
                dec = 0;
                // if(ratings[i] == ratings[i-1])
                //     cur = 1;
                // else
                //     cur = pre+1;
                // ret = ret + pre;
                // pre = cur;
                pre = ratings[i] == ratings[i-1] ? 1 : pre + 1;
                ret = ret + pre;
                inc = pre;
            }else{
                dec ++;
                if(inc == dec){
                    dec ++;
                }
                ret = ret + dec;
                pre = 1;
            }
        }
        return ret;
    }
}
