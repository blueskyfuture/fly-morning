package com.t.suanfa;


public class Node<T> {
    private int val;

    private  Node next;

    private T obj;

    public Node getNext() {
        return next;
    }

    public void setNext(Node next) {
        this.next = next;
    }



    public int getVal() {
        return val;
    }

    public void setVal(int val) {
        this.val = val;
    }
}
