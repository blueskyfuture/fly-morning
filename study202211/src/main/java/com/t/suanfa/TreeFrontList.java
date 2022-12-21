package com.t.suanfa;

import java.util.Stack;

/**
 * 树的前序遍历
 */
public class TreeFrontList {

    public static void main(String[] args) {
        TreeNode tree = getTree();
        scanFront(tree);
        System.out.println("--------------------");
        scan2(tree);
    }

    /**
     * 递归前序
     * 递归的表达性很好，也很容易理解，不过如果层级过深，很容易导致栈溢出。所以我们重点看下非递归实现。
     * @param node
     */
    private static void scanFront(TreeNode node) {
        if(node == null){
            return;
        }
        System.out.print(node.val+",");
        scanFront(node.left);
        scanFront(node.right);
    }

    // 递归后序遍历
    public static void recursionPostorderTraversal(TreeNode root) {
        if (root != null) {
            recursionPostorderTraversal(root.left);
            recursionPostorderTraversal(root.right);
            System.out.print(root.val + " ");
        }
    }

    // 递归中序遍历
    public static void recursionMiddleorderTraversal(TreeNode root) {
        if (root != null) {
            recursionMiddleorderTraversal(root.left);
            System.out.print(root.val + " ");
            recursionMiddleorderTraversal(root.right);
        }
    }

    private static TreeNode getTree() {
        TreeNode root = new TreeNode(1);
        TreeNode left1 = new TreeNode(2);
        TreeNode right1 = new TreeNode(3);

        root.left = left1;
        root.right = right1;
        TreeNode left2 = new TreeNode(4);
        left1.left = left2;
        TreeNode right3 = new TreeNode(6);
        left2.right = right3;
        TreeNode left4 = new TreeNode(7);
        TreeNode right4 = new TreeNode(8);
        right3.left = left4;
        right3.right = right4;
        TreeNode right1_2 = new TreeNode(5);
        right1.right = right1_2;
        return root;
    }

    // 非递归先序遍历
    public static void scan2(TreeNode root) {
        // 用来暂存节点的栈
        Stack<TreeNode> treeNodeStack = new Stack<TreeNode>();
        // 新建一个游标节点为根节点
        TreeNode node = root;
        // 当遍历到最后一个节点的时候，无论它的左右子树都为空，并且栈也为空
        // 所以，只要不同时满足这两点，都需要进入循环
        while (node != null || !treeNodeStack.isEmpty()) {
            // 若当前考查节点非空，则输出该节点的值
            // 由考查顺序得知，需要一直往左走
            while (node != null) {
                System.out.print(node.val + " ");
                // 为了之后能找到该节点的右子树，暂存该节点
                treeNodeStack.push(node);
                node = node.left;
            }
            // 一直到左子树为空，则开始考虑右子树
            // 如果栈已空，就不需要再考虑
            // 弹出栈顶元素，将游标等于该节点的右子树
            if (!treeNodeStack.isEmpty()) {
                node = treeNodeStack.pop();
                node = node.right;
            }
        }
    }
}

