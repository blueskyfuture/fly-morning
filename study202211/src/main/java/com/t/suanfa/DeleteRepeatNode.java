package com.t.suanfa;

import static com.t.suanfa.BM1_NodeReserve.printNodeList;

/**
 *
 *
 */
public class DeleteRepeatNode {

    public static void main(String[] args) {
        Node head = getNodeList();
        printNodeList(head);
        System.out.println("-----------");
//        deleteRepeatNode11(head);
        deleteRepeatNode22(head);
        printNodeList(head);
    }

    private static Node deleteRepeatNode22(Node head) {
        if(head == null || head.getNext() == null)
            return head;
        Node dummy = new Node();
        dummy.setVal(-1);
        dummy.setNext(head);
        Node pre = dummy;
        Node cur = head;
        while (cur != null && cur.getNext() != null){
            if(cur.getVal() ==  cur.getNext().getVal()){
                int val = cur.getVal();
                while (cur != null && cur.getVal() == val){
                    cur = cur.getNext();
                }
                pre.setNext(cur.getNext());
            }else{
                pre = cur;
                cur = cur.getNext();
            }
        }
        return dummy.getNext();
    }


    /**
     * result:->0->1->2->2->3->4->4->5->6->6->7
     * -----------result:->0->1->3->5->7
     */
    private static Node deleteRepeatNode21(Node head) {
        if(head == null || head.getNext() == null)
            return head;
        Node dummyNode = new Node();
        dummyNode.setVal(-1);
        dummyNode.setNext(head);
        Node pre = dummyNode;
        Node cur = head;
        while (cur != null && cur.getNext() != null){
            if(cur.getVal() == cur.getNext().getVal()){
                int val = cur.getVal();
                while (cur != null && cur.getVal() == val){
                    cur = cur.getNext();
                }
                pre.setNext(cur);
            }else{
                pre = cur;
                cur = cur.getNext();
            }
        }
        return head;
    }

    /**
     * result:->0->1->2->2->3->4->4->5->6->6->7
     * -----------result:->0->1->3->5->7
     */
    private static Node deleteRepeatNode20(Node head) {
        if(head == null || head.getNext() == null)
            return head;
        Node dummyNode = new Node();
        dummyNode.setVal(-1);
        dummyNode.setNext(head);
        Node pre = dummyNode;
        Node cur = head;
        Node next;
        while (cur != null && cur.getNext() != null){
            int curVal = cur.getVal();
            next = cur.getNext();
            boolean flag = false;
            while (next != null && curVal == next.getVal()){
                next = next.getNext();
                flag = true;
            }
            if(flag){
                cur = next;
                pre.setNext(cur);
            }else{
                pre = cur;
                cur = next;
            }
        }
        return head;

    }

    /**
     * 去重但不删除
     * 1,2,2,2,3,4==>1,2,3,4
     * @param head
     * @return
     */
    private static Node deleteRepeatNode11(Node head) {
        Node cur = head;
        while (cur.getNext() != null){
            if(cur.getVal() == cur.getNext().getVal()){
                cur.setNext(cur.getNext().getNext());
            }else {
                cur = cur.getNext();
            }
        }
        return head;
    }

    /**
     * 1,2,2,2,3,4==>1,2,3,4
     * @return
     */
    private static Node deleteRepeatNode10(Node head) {
        if(head == null || head.getNext() == null)
            return head;
        Node cur = head;
        while (cur != null && cur.getNext() != null){
            if(cur.getVal() == cur.getNext().getVal()){
                cur.setNext(cur.getNext().getNext());
            }else {
                cur = cur.getNext();
            }
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
        for (int i = 1; i <=7 ; i++) {
            Node node = new Node();
            node.setVal(i);
            tmp.setNext(node);
            tmp = node;
            if( i%2 == 0 ){
                Node node1 = new Node();
                node1.setVal(i);
                tmp.setNext(node1);
                tmp = node1;
            }
        }

        return head;
    }


}
