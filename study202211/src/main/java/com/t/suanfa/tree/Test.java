package com.t.suanfa.tree;

/**
 * Created by HH on 2019/7/2.
 */
public class Test {
    public static void main(String[] args) {
        Tree t=new Tree();
        t.insert(80);
        t.insert(70);
        t.insert(100);
        t.insert(90);
        Node node = t.find(100);
        System.out.println(node.leftNode.data);
        System.out.println(t.count());
    }
}
