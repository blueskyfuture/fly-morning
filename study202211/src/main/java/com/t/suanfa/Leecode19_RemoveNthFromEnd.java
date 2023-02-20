package com.t.suanfa;

/**
 * https://leetcode.cn/problems/remove-nth-node-from-end-of-list/
 * 给你一个链表，删除链表的倒数第 n 个结点，并且返回链表的头结点。
 * 示例 1：
 * 输入：head = [1,2,3,4,5], n = 2
 * 输出：[1,2,3,5]
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/remove-nth-node-from-end-of-list
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class Leecode19_RemoveNthFromEnd {

    public ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode first = head;
        ListNode dummp = new ListNode();
        dummp.next = head;
        ListNode sec = dummp;
        for (int i = 0; i < n-1; i++) {
            first = first.next;
        }
        while (first.next != null){
            first = first.next;
            sec = sec.next;
        }
        sec.next = sec.next.next;
        return dummp.next;
    }

    class ListNode {
        int val;
        ListNode next;
        ListNode() {}
        ListNode(int val) { this.val = val; }
        ListNode(int val, ListNode next) { this.val = val; this.next = next; }
    }
}


