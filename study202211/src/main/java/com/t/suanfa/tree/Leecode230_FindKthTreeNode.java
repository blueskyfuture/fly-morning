package com.t.suanfa.tree;

import java.util.LinkedList;

/**
 *
 * * LeetCode 230
 *  * 题意：
 *  * 给定一个二叉搜索树，编写一个函数 kthSmallest 来查找其中第 k 个最小的元素。
 *
 *
 *  * 说明：
 *  * 你可以假设 k 总是有效的，1 ≤ k ≤ 二叉搜索树元素个数。
 *
 *
 *  * 示例 1:
 *  * 输入: root = [3,1,4,null,2], k = 1
 *  * 3
 *  * / \
 *  * 1 4
 *  * \
 *  * 2
 *  * 输出: 1
 */

public class Leecode230_FindKthTreeNode {
    public static int kthSmallest1(TreeNode root, int k) {
        LinkedList<TreeNode> stack = new LinkedList<>();
        while(true){
            while(root != null){
                stack.offer(root);
                root = root.left;
            }
            root = stack.removeLast();
            if(--k == 0){
                return root.val;
            }
            root = root.right;
        }
    }
}
