package com.t.suanfa;

public class NC3_FintRingEnter {

     


        public static void main(String[] args) {
            ListNode head = new ListNode(0);
            ListNode tmp = head;
            ListNode enter = null;
            for (int i = 1; i <= 5; i++) {
                ListNode node = new ListNode(i);
                tmp.next = node;
                tmp = node;
                if(i == 3)
                    enter = node;
            }
            tmp.next = enter;
            ListNode listNode = detectCycle2(head);
            System.out.println(listNode.val);
        }

    private static ListNode detectCycle2(ListNode head) {
//            if(head == null || head.next == null)
//                return null;
            ListNode fast = head;
            ListNode slow = head;
            while (fast.next != null && fast.next.next != null){
                fast = fast.next.next;
                slow = slow.next;
                if(fast == slow){
                    slow = head;
                    while (slow != fast){
                        slow = slow.next;
                        fast = fast.next;
                    }
                    //
                    break;
                }
            }
            return slow;
    }


    private static ListNode detectCycle1(ListNode head) {
            ListNode fast = head;
            ListNode low = head;
//            if(head == null || head.next == null){
//                return null;
//            }
            while (fast!=null && fast.next !=null){
                fast = fast.next.next;
                low = low.next;
                if(fast == low){
                    System.out.println("ring is exist");
                    //fast = head;
                    low = head;
                    while (fast != low){
                        fast = fast.next;
                        low = low.next;
                    }
                    break;
                }
            }

            return fast;
    }

        public static ListNode detectCycle(ListNode head) {
        ListNode p1 = head;
        ListNode p2 = head;
        while (p2 != null && p2.next != null) {
            p1 = p1.next;
            p2 = p2.next.next;
            if (p1 == p2) {
                p1 = head;
                while(p1 != p2) {
                    p1 = p1.next;
                    p2 = p2.next;
                }
                break;
            }
        }
        if (p2 == null || p2.next == null) return null;


        return p1;
    }

    //Definition for singly-linked list.
    static class ListNode {
        int val;
        ListNode next;
        ListNode(int x) {
            val = x;
            next = null;
        }
    }

}
