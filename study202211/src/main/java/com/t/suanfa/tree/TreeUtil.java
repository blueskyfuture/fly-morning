package com.t.suanfa.tree;

import java.util.*;

public class TreeUtil {

    public static TreeNode createTree(Integer[] arr) {
        // 使用队列来存储每一层的非空节点，下一层的数目要比上一层高
        ArrayDeque<TreeNode> pre = new ArrayDeque<>();
        TreeNode root = new TreeNode(arr[0]);
        pre.addLast(root);
        // 表示要遍历的下一个节点
        int index = 0;
        while (!pre.isEmpty()) {

            ArrayDeque<TreeNode> cur = new ArrayDeque<>();
            while (!pre.isEmpty()) {
                TreeNode node = pre.removeFirst();
                TreeNode left=null;
                TreeNode right=null;
                // 如果对应索引上的数组不为空的话就创建一个节点,进行判断的时候，
                // 要先索引看是否已经超过数组的长度，如果索引已经超过了数组的长度，那么剩下节点的左右子节点就都是空了
                // 这里index每次都会增加，实际上是不必要的，但是这样写比较简单
                if (++index<arr.length&&arr[index]!=null){
                    left=new TreeNode(arr[index]);
                    cur.addLast(left);
                }
                if (++index<arr.length&&arr[index]!=null){
                    right=new TreeNode(arr[index]);
                    cur.addLast(right);
                }
                node.left=left;
                node.right=right;
            }
            pre=cur;
        }


        return root;
    }

    public static void main(String[] args) {
        Integer[] arr = {5, 4, 8, 11, 12, 13, 4, 7, 2, null, null, 5, 1};
//        Integer[] arr={5,4};
        TreeNode tree = createTree(arr);
        preOrder(tree);
        System.out.println();
        System.out.println("--------------preOrder finish-------------");
        levelOrder(tree);
        System.out.println();
        System.out.println("--------------levelOrder finish-------------");
        treeDFS(tree);
        System.out.println();
        System.out.println("--------------treeDFS finish-------------");
    }

    private static int depth(TreeNode tree){
        if(tree == null) return 0;
        int depth = Math.max(depth(tree.left),depth(tree.right)) +1;
        return depth;
    }

    /**
     * 树的先序遍历
     * @param tree
     */public static void preOrder(TreeNode tree) {
        if (tree == null)
            return;
        System.out.printf(tree.val + " ");
        preOrder(tree.left);
        preOrder(tree.right);
    }


    /**
     * 广度优先搜索
     * @param tree
     */
    public static void levelOrder(TreeNode tree) {
        if (tree == null)
            return;
        LinkedList<TreeNode> list = new LinkedList<>();//链表，这里我们可以把它看做队列
        List<Integer> reList = new ArrayList<>();
        list.add(tree);//相当于把数据加入到队列尾部
        while (!list.isEmpty()) {
            TreeNode node = list.poll();//poll方法相当于移除队列头部的元素
            System.out.println(node.val);
            reList.add(node.val);
            if (node.left != null)
                list.add(node.left);
            if (node.right != null)
                list.add(node.right);
        }
        System.out.println("levelOrder  reList.size()==" + reList.size());
        System.out.println("levelOrder  reList==" + Arrays.toString(reList.toArray()));
    }

    /**
     * 深度优先搜索
     * @param root
     */
    public static void treeDFS(TreeNode root) {
        List<Integer> reList = new ArrayList<>();
        Stack<TreeNode> stack = new Stack<>();
        stack.add(root);
        while (!stack.empty()) {
            TreeNode node = stack.pop();
            System.out.println(node.val);
            reList.add(node.val);
            if (node.right != null) {
                stack.push(node.right);
            }
            if (node.left != null) {
                stack.push(node.left);
            }
        }
        System.out.println("treeDFS  reList.size()==" + reList.size());
        System.out.println("treeDFS  reList==" + Arrays.toString(reList.toArray()));
    }


}
 class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode(int x) { val = x; }
}

