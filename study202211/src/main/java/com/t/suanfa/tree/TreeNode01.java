package com.t.suanfa.tree;

import java.util.LinkedList;

/**
 * 满二叉树构造和查找
 * @param <T>
 */
public class TreeNode01<T> {

    public static void main(String[] args) {
        TreeNode01 root = new TreeNode01<>();
        root.setVal(1);
        int h = 5;
        TreeNode01<Integer> t = new TreeNode01<>();
        t.genTree(root,h-1);
        TreeNode01 r = root;
        Integer key = 10;
        TreeNode01<Integer> res = t.find1(root, key);
        TreeNode01<Integer> cur = res;
    }

    private TreeNode01<Integer> find1(TreeNode01<Integer> root, int key){
        if(root == null)
            return null;
        if(root.val == key)
            return root;
        TreeNode01<Integer> left = find1(root.getLeft(), key);
        if(left != null)
            return left;
        TreeNode01<Integer> right = find1(root.getRight(), key);
        if(right != null)
            return right;
        return null;
    }


    private TreeNode01<Integer> find(TreeNode01<Integer> root, int key){
        LinkedList<TreeNode01> list = new LinkedList<>();
        while (true){
            while (root != null){
                list.offer(root);
                root  = root.left;
            }
            root = list.removeLast();
            if(--key == 0){
                return root;
            }
            root = root.right;
        }

    }

    private void genTree(TreeNode01<Integer> node, int h){
        if(h >= 1 ){
            node.left = new TreeNode01<>();
            node.left.setParent(node);
            node.left.setVal(node.getVal() * 2);
            node.right = new TreeNode01<>();
            node.right.setParent(node);
            node.right.setVal(node.getVal() * 2 + 1);
            h = h -1;
            genTree(node.left,h);
            genTree(node.right,h);
        }
    }

    private TreeNode01<T> parent ;
    private TreeNode01<T> left;
    private TreeNode01<T> right;
    private T val;


    public TreeNode01<T> getParent() {
        return parent;
    }

    public void setParent(TreeNode01<T> parent) {
        this.parent = parent;
    }

    public TreeNode01<T> getLeft() {
        return left;
    }

    public void setLeft(TreeNode01<T> left) {
        this.left = left;
    }

    public TreeNode01<T> getRight() {
        return right;
    }

    public void setRight(TreeNode01<T> right) {
        this.right = right;
    }

    public T getVal() {
        return val;
    }

    public void setVal(T val) {
        this.val = val;
    }
}
