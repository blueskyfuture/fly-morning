package com.t.suanfa.tree;

import org.junit.Test;

import java.util.Arrays;

public class Traversal2 {
    /*
                 45
                / \
              34   5
             / \    \
            12  2    3
           /   / \    \
          33  76  8   44
    */
    static class MyTreeNode {
        Integer value;
        MyTreeNode left;
        MyTreeNode right;
        //有这个就很容易计算出数组大小了
        int depth;


        MyTreeNode(Integer value) {
            this.value = value;
        }

        public int getDepth() {
            return depth;
        }
    }

    /**
     * 数组转换成树
     */
    @Test
    public void arrayToTree() {
        Integer[] arr = {45, 34, 5, 12, 2, null, 3, 33, null, 76, 8, null, null, null, 44};
        MyTreeNode root = generateTreeFromArray(arr, 0);
    }

    /**
     * 树转换成数组
     */
    @Test
    public void treeToArray() {
        //生成一棵树。数组没有元素情况，本例不考虑
        Integer[] arr = {45, 34, 5, 12, 2, null, 3, 33, null, 76, 8, null, null, null, 44};
        MyTreeNode root = generateTreeFromArray(arr, 0);
        //根据depth，计算数组size
        int arraySize = (int) Math.pow(2, root.depth) - 1;
        Integer[] dst = new Integer[arraySize];
        //再将node转换成对应的数组,根据index填充
        generateArrayFromTree(dst,root,0);
        System.out.println("Traversal2 result:"+Arrays.toString(dst)); //打印验证

    }

    private static void generateArrayFromTree(Integer[] arr,MyTreeNode node,int index){
        if(node==null){
            return;
        }
        arr[index]=node.value;
        generateArrayFromTree(arr,node.left,2*index+1);
        generateArrayFromTree(arr,node.right,2*index+2);
    }

    private static MyTreeNode generateTreeFromArray(Integer[] arr, int index) {
        if (index > arr.length - 1 || arr[index] == null) {
            return null;
        }
        MyTreeNode myTreeNode = new MyTreeNode(arr[index]);
        myTreeNode.left = generateTreeFromArray(arr, 2 * index + 1);
        myTreeNode.right = generateTreeFromArray(arr, 2 * index + 2);
        int leftDepth = myTreeNode.left == null ? 0 : myTreeNode.left.depth;
        int rightDepth = myTreeNode.right == null ? 0 : myTreeNode.right.depth;
        myTreeNode.depth = Math.max(leftDepth, rightDepth) + 1;
        return myTreeNode;
    }
}
