package com.t.suanfa;

/**
 * https://leetcode.cn/problems/range-sum-query-2d-immutable/
 * 304. 二维区域和检索 - 矩阵不可变
 * 给定一个二维矩阵 matrix，以下类型的多个请求：
 *
 * 计算其子矩形范围内元素的总和，该子矩阵的 左上角 为 (row1, col1) ，右下角 为 (row2, col2) 。
 * 实现 NumMatrix 类：
 *
 * NumMatrix(int[][] matrix) 给定整数矩阵 matrix 进行初始化
 * int sumRegion(int row1, int col1, int row2, int col2) 返回 左上角 (row1, col1) 、右下角 (row2, col2) 所描述的子矩阵的元素 总和 。
 *
 *
 * 示例 1：
 *
 *
 *
 * 输入:
 * ["NumMatrix","sumRegion","sumRegion","sumRegion"]
 * [[[[3,0,1,4,2],[5,6,3,2,1],[1,2,0,1,5],[4,1,0,1,7],[1,0,3,0,5]]],[2,1,4,3],[1,1,2,2],[1,2,2,4]]
 * 输出:
 * [null, 8, 11, 12]
 *
 * 解释:
 * NumMatrix numMatrix = new NumMatrix([[3,0,1,4,2],[5,6,3,2,1],[1,2,0,1,5],[4,1,0,1,7],[1,0,3,0,5]]);
 * numMatrix.sumRegion(2, 1, 4, 3); // return 8 (红色矩形框的元素总和)
 * numMatrix.sumRegion(1, 1, 2, 2); // return 11 (绿色矩形框的元素总和)
 * numMatrix.sumRegion(1, 2, 2, 4); // return 12 (蓝色矩形框的元素总和)
 */
public class Leecode304_sumRegion {
    public static void main(String[] args) {
        //够着一个数组，每行5个元素，一共3行,即为3行5列
        int[][] matrix = new int[3][5];
        int cur = 1;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 5; j++) {
                matrix[i][j] = cur;
                cur++;
            }
        }
        NumMatrix numMatrix = new NumMatrix(matrix);
        int sumRegion = numMatrix.sumRegion(0, 0, 1, 1);
        System.out.println("sumRegion==" + sumRegion);
        int sumRegion1 = numMatrix.sumRegion(1, 1, 2, 3);
        System.out.println("sumRegion1==" + sumRegion1);
    }


}

class NumMatrix {
    int[][] matrix;
    int[][] preSum;
    public NumMatrix(int[][] matrix) {
        this.matrix = matrix;
        System.out.println(this.matrix.length);
        System.out.println(this.matrix[0].length);
        int len1 = matrix.length;
        int len2 = matrix[0].length;
        preSum = new int[len1 + 1][len2 + 1];
        //for (int i = 1; i < len1; i++) {
        for (int i = 1; i <= len1; i++) {
//            for (int j = 1; j < len2; j++) {
           for (int j = 1; j <= len2; j++) {
                preSum[i][j] = preSum[i-1][j] + preSum[i][j-1] + matrix[i-1][j-1] - preSum[i-1][j-1];
            }
        }
    }

    public int sumRegion(int row1, int col1, int row2, int col2) {
        //int res = preSum[row2+1][col2+1] - preSum[row2+1][col1+1] - preSum[row1+1][col2+1] + preSum[row1+1][col2+1];
        int res = preSum[row2+1][col2+1] - preSum[row2+1][col1] - preSum[row1][col2+1] + preSum[row1][col1];
        return res;
    }
}
