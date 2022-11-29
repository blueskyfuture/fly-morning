package com.t.suanfa;

import java.util.ArrayList;
import java.util.List;

public class NodeDel {

    public static void main(String[] args) {
        Node head = getNodeList();
        printNodeList(head);
        head = delNode(head,5);
        System.out.println("---------");
        printNodeList(head);

    }

    private static Node delNode(Node head,int n) {
        if(head == null)
            return head;
        Node cur = head;
        Node pre = null;
        for (int i = 1; i <= n ; i++) {
            if (cur == null) {
                System.out.println("when i = " + i + "；cur == null");
                return head;
            }else{
                pre = cur;
                cur = cur.getNext();
            }
        }
        pre.setNext(cur.getNext());
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
        for (int i = 1; i <=3 ; i++) {
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
