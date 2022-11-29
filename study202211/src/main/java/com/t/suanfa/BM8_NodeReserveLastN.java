package com.t.suanfa;

import java.util.ArrayList;
import java.util.List;

/**
 * 描述
 * 将一个节点数为 size 链表 m 位置到 n 位置之间的区间反转，要求时间复杂度 O(n)O(n)，空间复杂度 O(1)O(1)。
 * 例如：
 * 给出的链表为 1\to 2 \to 3 \to 4 \to 5 \to NULL1→2→3→4→5→NULL, m=2,n=4m=2,n=4,
 * 返回 1\to 4\to 3\to 2\to 5\to NULL1→4→3→2→5→NULL.
 *
 * 数据范围： 链表长度 0 < size \le 10000<size≤1000，0 < m \le n \le size0<m≤n≤size，链表中每个节点的值满足 |val| \le 1000∣val∣≤1000
 * 要求：时间复杂度 O(n)O(n) ，空间复杂度 O(n)O(n)
 * 进阶：时间复杂度 O(n)O(n)，空间复杂度 O(1)O(1)
 */
public class BM8_NodeReserveLastN {
    public static void main(String[] args) {
        Node head = getNodeList();
        printNodeList(head);
        Node reverse = reverse3(head,4,7);
        System.out.println("---------");
        printNodeList(reverse);
    }

    private static Node reverse4(Node head, int m, int n) {
        Node dummyNode = new Node();
        dummyNode.setNext(head);
        Node pre = dummyNode;
        for (int i = 0; i < m-1; i++) {
            pre = pre.getNext();
        }
        Node cur = pre.getNext();
        Node curNext = null;
        for (int i = 0; i < n-m; i++) {
            curNext = cur.getNext();
            cur.setNext(curNext.getNext());
            curNext.setNext(pre.getNext());//
            pre.setNext(curNext);
        }

        return dummyNode.getNext();
    }

    private static Node reverse3(Node head, int m, int n) {
        Node dummyNode = new Node();
        dummyNode.setVal(-1);
        dummyNode.setNext(head);
        Node pre = dummyNode;
        for (int i = 0; i < m-1; i++) {
            pre = pre.getNext();
        }

        Node cur = pre.getNext();
        Node cur_next;
        for (int i = 0; i < n-m; i++) {
            cur_next = cur.getNext();
            cur.setNext(cur_next.getNext());
            cur_next.setNext(pre.getNext());
            pre.setNext(cur_next);
        }
        return dummyNode.getNext();
    }


    private static Node reverse2(Node head, int m, int n) {
        Node dummyNode = new Node();
        dummyNode.setNext(head);
        Node pre = dummyNode;
        for (int i = 0; i < m-1; i++) {
            //head = head.getNext()
            pre = pre.getNext();
        }
        //pre = head;

        Node cur = pre.getNext();
        Node cur_next;
        //for (int i = 0; i < m-n+1; i++) {
        //for (int i = 0; i < m-n; i++) {
        for (int i = 0; i < n-m; i++) {
            cur_next = cur.getNext();
            cur.setNext(cur_next.getNext());
            cur_next.setNext(pre.getNext());
            pre.setNext(cur_next);
        }
        return dummyNode.getNext();
    }


    /**
     *
     * 11
     * result:->0->1->2->3->4->5->6->7->8->9->10
     * ---------
     * 11
     * result:->0->1->2->6->5->4->3->7->8->9->10
     *
     * @param head Node类 
     * @param m int整型 
     * @param n int整型 
     * @return Node类
     */
    public static Node reverse1 (Node head, int m, int n) {
        //设置虚拟头节点
        Node dummyNode = new Node();
        dummyNode.setNext(head);
        Node pre = dummyNode;
        for(int i=0;i<m-1;i++){
            pre = pre.getNext();
        }

        Node cur = pre.getNext();
        Node Cur_next ;
        for(int i=0;i<n-m;i++){
            Cur_next = cur.getNext();
            cur.setNext(Cur_next.getNext());
            Cur_next.setNext(pre.getNext());
            pre.setNext(Cur_next);
        }
        return dummyNode.getNext();
    }

    /**
     *这个方法错误
     * @param head
     * @param m
     * @param n
     * @return
     */
    private static Node reverse_err(Node head,int m,int n) {
        int i = 1;
        Node begin = null;
        Node b = null;
        Node mid = null;
        Node end = null;
        Node tmp = head;
        while (tmp != null){
            if(i == m-1){
                begin = tmp;
            }
            if(i>=m && i<=n){
                mid = tmp;
                end = tmp.getNext();

            }
            tmp = tmp.getNext();
            i++;

        }

        return head;
    }


    /**
     * 获取单向链表
     * @return
     */
    private static Node getNodeList(){
        Node head  = new Node();
        head.setVal(0);
        Node tmp = head;
        for (int i = 1; i <=10 ; i++) {
            Node node = new Node();
            node.setVal(i);
            tmp.setNext(node);
            tmp = node;
        }
        return head;
    }


    /**
     * 打印
     * @param head
     */
    private static void printNodeList(Node head){
        List<Integer> list = new ArrayList<>();
        Node tmp = head;
        String result = "";
        while (tmp.getNext() != null) {
            list.add(tmp.getVal());
            result = result + "->" + tmp.getVal();
            tmp = tmp.getNext();
        }
        list.add(tmp.getVal());
        result = result + "->" + tmp.getVal();
        System.out.println(list.size());
        System.out.println("result:" + result);
    }

}
