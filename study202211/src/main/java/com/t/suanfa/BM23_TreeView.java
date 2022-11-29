package com.t.suanfa;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 描述
 * 给你二叉树的根节点 root ，返回它节点值的 前序 遍历。
 * 数据范围：二叉树的节点数量满足 1 \le n \le 100 \1≤n≤100  ，二叉树节点的值满足 1 \le val \le 100 \1≤val≤100  ，树的各节点的值各不相同
 */
public class BM23_TreeView {
    // 测试方法
    public static void main(String[] args) {
        TreeNode<Integer> treeNode1 = new TreeNode(1);
        TreeNode<Integer> treeNode2 = new TreeNode(2);
        TreeNode<Integer> treeNode3 = new TreeNode(3);
        TreeNode<Integer> treeNode4 = new TreeNode(4);
        TreeNode<Integer> treeNode5 = new TreeNode(5);
        TreeNode<Integer> treeNode6 = new TreeNode(6);
        treeNode1.left = treeNode2;
        treeNode1.right = treeNode3;
        treeNode2.left = treeNode5;
        treeNode3.left = treeNode4;
        treeNode3.right = treeNode6;
        BM23_TreeView test = new BM23_TreeView();
        List<Integer> list = new ArrayList<>();
        //test.preorder(list,treeNode1);
//        test.middleOrder(list,treeNode1);
        test.afterorder(list,treeNode1);
        System.out.println("xxxx结果 = " + Arrays.toString(list.toArray()));
    }


    /**
     * 前序遍历 1,2,5,3,4,6
     * @param list
     * @param root
     */
    public void preorder(List<Integer> list, TreeNode<Integer> root){
        //遇到空节点则返回
        if(root == null)
            return;
        //先遍历左节点
        list.add(root.val);
        //再去中子树
        preorder(list, root.left);
        //最后去右子树
        preorder(list, root.right);
    }


    /**
     * 中序遍历 5,2,1,4,3,6
     * @param list
     * @param root
     */
    public void middleOrder(List<Integer> list, TreeNode<Integer> root){
        //遇到空节点则返回
        if(root == null)
            return;
        //先遍历中节点
        middleOrder(list, root.left);
        //再去左子树
        list.add(root.val);
        //最后去右子树
        middleOrder(list, root.right);
    }

    /**
     * 后序遍历  6,3,4,1,2,5
     * @param list
     * @param root
     */
    public void afterorder(List<Integer> list, TreeNode<Integer> root){
        //遇到空节点则返回
        if(root == null)
            return;
        //先遍右子树
        afterorder(list, root.right);
        //再去中节点
        list.add(root.val);
        //最后去左子树
        afterorder(list, root.left);
    }

    public int[] preorderTraversal (TreeNode root) {
        //添加遍历结果的数组
        List<Integer> list = new ArrayList();
        //递归前序遍历
        preorder(list, root);
        //返回的结果
        int[] res = new int[list.size()];
        for(int i = 0; i < list.size(); i++)
            res[i] = list.get(i);
        return res;
    }

}

class TreeNode<T> {
    T val;          // 头结点
    TreeNode left;    // 左子树
    TreeNode right;   // 右子树

    TreeNode(T x) {
        val = x;
    }
}
