package com.t.suanfa.tree;

/**
 * 给定一个二叉树，判断它是否是高度平衡的二叉树。
 *
 * 本题中，一棵高度平衡二叉树定义为：
 *
 * 一个二叉树每个节点 的左右两个子树的高度差的绝对值不超过1。
 */
public class Leecode110_IsBalancedTree {
    class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }

        public boolean isBalanced(TreeNode root) {
            //root为空肯定是平衡的
            if (root == null) {
                return true;
            }
            //根节点左右子树的高度差 <= 1 && 左子树是平衡的 && 右子树是平衡的
            return (Math.abs(maxDepth(root.left) - maxDepth(root.right)) <= 1) && isBalanced(root.left) && isBalanced(root.right);
        }

        public int maxDepth(TreeNode root) {
            if (root == null) {
                return 0;
            }
            return Math.max(maxDepth(root.left),maxDepth(root.right)) + 1;
        }


}
