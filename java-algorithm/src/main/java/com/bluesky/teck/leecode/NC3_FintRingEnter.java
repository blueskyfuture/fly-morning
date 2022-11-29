package com.bluesky.teck.leecode;

public class NC3_FintRingEnter {
      //Definition for singly-linked list.
      static class ListNode {
          int val;
          ListNode next;
          ListNode(int x) {
              val = x;
              next = null;
          }
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
            detectCycle(head);
        }


}
