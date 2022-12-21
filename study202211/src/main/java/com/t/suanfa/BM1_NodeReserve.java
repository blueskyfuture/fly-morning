package com.t.suanfa;

import java.util.ArrayList;
import java.util.List;

/**
 * 单列表翻转
 */
public class BM1_NodeReserve {

    public static void main(String[] args) {
        Node head = getNodeList();
        printNodeList(head);
        Node reverse = reverse7(head);
        System.out.println("---------");
        printNodeList(reverse);
    }

    private static Node reverse7(Node head) {
        if(head == null || head.getNext() == null)
            return head;
        Node begin = null;
        Node mid = head;
        Node end = head.getNext();
        while (true){
            mid.setNext(begin);
            if(end == null)
                break;
            begin = mid;
            mid = end;
            end = end.getNext();
        }
        return mid;
    }

    /**
     * 自己练习
     * @param head
     * @return
     */
    private static Node reverse6(Node head) {
        if(head == null || head.getNext()==null)
            return head;
        Node node = null;
        Node front = head;
        Node tmp;
        while (front != null){
            tmp = front.getNext();
            front.setNext(node);
            node = front;
            front = tmp;
        }
        return node;
    }

    private static Node reverse5(Node head) {
        if(head == null || head.getNext() == null)
            return head;
        Node begin = null;
        Node mid = head;
        Node end = head.getNext();
        while (true){
            mid.setNext(begin);
            if(end == null)
                break;
            begin = mid;
            mid = end;
            end = end.getNext();
        }
//        while (mid != null){
//            mid.setNext(begin);
//            begin = mid;
//            if(end != null){
//                mid = end;
//                end = end.getNext();
//            }else {
//                break;
//            }
//        }
        return mid;
 }


    private static Node reverse4(Node head) {
        if(head == null || head.getNext() == null)
            return head;
        else {
            Node begin = null;
            Node mid = head;
            Node end = mid.getNext();
            while (true){
                mid.setNext(begin);
                if(end == null)
                    break;
                begin = mid;
                mid = end;
                end = end.getNext();
            }
            return mid;
        }









    }


    private static Node reverse3(Node head) {
        //
        if(head == null || head.getNext()==null)
            return head;
        else {
            Node begin = null;
            Node mid = head;
            Node end = head.getNext();
            //while (mid != null && mid.getNext() != null){
            while (true){
                mid.setNext(begin);
                if(end == null)
                    break;
                //end.setNext(mid);
                begin = mid;
                mid = end;
                end = end.getNext();
            }
            return mid;
        }
    }


    private static Node reverse2(Node head) {
        if(head == null || head.getNext() == null){
            return head;
        }else {
            Node begin = null;
            Node mid = head;
            Node end = head.getNext();
            while (true){//----1
                mid.setNext(begin);
                if(end == null)//-----2
                    break;
                begin = mid;
                mid = end;
                end = end.getNext();
            }
            return mid;
        }
    }


    private static Node reverse1(Node head) {
        if(head == null || head.getNext() == null)
            return head;
        else {
            Node begin = null;
            Node mid = head;
            Node end = head.getNext();
            while (true){
                mid.setNext(begin);
                //end.setNext(mid);
                //if(end.getNext() == null)
                if(end == null)
                    break;
                begin = mid;
                mid = end;
                end = end.getNext();
            }
            //return end;
            return mid;
        }
    }



    private static  Node reverse(Node head){
        if(head == null || head.getNext() == null){
            return head;
        }else {
            Node begin = null;
            Node mid = head;
            Node end = head.getNext();
            while (true){
                mid.setNext(begin);
                if(end == null)
                    break;
                begin = mid;
                mid = end;
                end = end.getNext();
            }
            head = mid;
            return head;
        }

    }

    /**
     * 获取单向链表
     * @return
     */
    static Node getNodeList(){
        Node head  = new Node();
        head.setVal(0);
        Node tmp = head;
        for (int i = 1; i <=8 ; i++) {
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
    public static void printNodeList(Node head){
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
