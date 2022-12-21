package com.t.suanfa;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Stack;

/**
 * 深度优先遍历（DFS）和广度优先遍历（BFS）
 * https://www.51cto.com/article/614590.html
 * 见treeDSF.gif
 */
public class TreeDSF {

    public static void main(String[] args) {
        TreeNode tree = getTree();
//        dfsWithStack(tree);
        bfsWithLinkList(tree);
        boolean complete = is_complete(tree);
        System.out.println("complete:" + complete);
        int depthMaxTree = getMaxDepth(tree);
        int depthMinTree = getMinDepth(tree);
        System.out.println("depthTree=" + depthMaxTree+ ";depthMinTree==" + depthMinTree);


        TreeNode completeTree = getCompleteTree();
        boolean complete2 = is_complete(completeTree);
        System.out.println("complete2:" + complete2);
        int depthMaxTree1 = getMaxDepth(completeTree);
        int depthMinTree1 = getMinDepth(completeTree);
        System.out.println("depthTree1=" + depthMaxTree1 + ";depthMinTree1==" + depthMinTree1);
    }

    /**
     * leetcode 104: 求树的最大深度
     * @param node
     * @return
     */
    public static int getMaxDepth(TreeNode node) {
        if (node == null) {
            return 0;
        }
        int leftDepth = getMaxDepth(node.left) + 1;
        int rightDepth = getMaxDepth(node.right) + 1;
        return Math.max(leftDepth, rightDepth);
    }

    /**
     * leetcode 111: 求树的最小深度
     * @param node
     * @return
     */
    public static int getMinDepth(TreeNode node) {
        if (node == null) {
            return 0;
        }
        int leftDepth = getMinDepth(node.left) + 1;
        int rightDepth = getMinDepth(node.right) + 1;
        return Math.min(leftDepth, rightDepth);
    }

    /**
     * 判断是否是完全二叉树
     * @param root
     * @return true 完全二叉树；false 不是完全二叉树
     */
    private static boolean is_complete(TreeNode root) {
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        boolean isEnd = false;
        while (!queue.isEmpty()){
            TreeNode node = queue.poll();
            if(node == null){
                isEnd = true;
                continue;
            }
            if(isEnd)
                return false;
            queue.offer(node.left);
            queue.offer(node.right);
        }
        return true;
    }


    /**
     * 广度优先遍历（BFS）
     * @param node
     */
    private static void bfsWithLinkList(TreeNode node) {
        Queue<TreeNode> list = new LinkedList<>();
        list.add(node);
        while (!list.isEmpty()){
            TreeNode poll = list.poll();
            process(poll);
            if(poll.left != null)
                list.add(poll.left);
            if(poll.right != null)
                list.add(poll.right);
        }
    }

    /**
     * 使用栈来实现深度优先遍历（DFS）
     * @param root
     */
    public static void dfsWithStack(TreeNode root) {
        if (root == null) {
            return;
        }

        Stack<TreeNode> stack = new Stack<>();
        // 先把根节点压栈
        stack.push(root);
        while (!stack.isEmpty()) {
            TreeNode treeNode = stack.pop();
            // 遍历节点
            process(treeNode);

            // 先压右节点
            if (treeNode.right != null) {
                stack.push(treeNode.right);
            }

            // 再压左节点
            if (treeNode.left != null) {
                stack.push(treeNode.left);
            }
        }
    }

    private static void process(TreeNode treeNode) {
        System.out.println(treeNode.val + ";");
    }

    /**
     * 构建非完全二叉树
     * 1;2;3;4;5;
     * @return
     */
    private static TreeNode getCompleteTree() {
        TreeNode root = new TreeNode(1);
        TreeNode left1 = new TreeNode(2);
        TreeNode right1 = new TreeNode(3);

        root.left = left1;
        root.right = right1;
        TreeNode left2 = new TreeNode(4);
        TreeNode right2 = new TreeNode(5);
        left1.left = left2;
        left1.right = right2;
        return root;
    }


    /**
     * 构建非完全二叉树
     * 1;2;3;4;#;#;5;#;6;#;#;#;#;#;#;7;8;
     * @return
     */
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
}
