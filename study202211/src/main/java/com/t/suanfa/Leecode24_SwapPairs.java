package com.t.suanfa;

import static com.t.suanfa.BM1_NodeReserve.getNodeList;
import static com.t.suanfa.BM1_NodeReserve.printNodeList;

/**
 * 给你一个链表，两两交换其中相邻的节点，并返回交换后链表的头节点。你必须在不修改节点内部的值的情况下完成本题（即，只能进行节点交换）。
 *
 * 输入：head = [1,2,3,4]
 * 输出：[2,1,4,3]
 * 示例 2：
 *
 * 输入：head = []
 * 输出：[]
 * 示例 3：
 *
 * 输入：head = [1]
 * 输出：[1]
 *
 * 解题思路：https://cloud.tencent.com/developer/article/1937708
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/swap-nodes-in-pairs
 */
public class Leecode24_SwapPairs {
    public static void main(String[] args) {
        Node head = getNodeList();
        printNodeList(head);
        Node result = swapPairs(head);
        System.out.println("---------");
        printNodeList(result);
    }

    private static Node swapPairs(Node head) {
        Node dummp = new Node();
        dummp.setVal(-1);
        dummp.setNext(head);
        Node pre = dummp;
        Node cur;
        Node curN;
        while (pre.getNext() != null && pre.getNext().getNext() != null){
            cur = pre.getNext();
            curN = pre.getNext().getNext();
            cur.setNext(curN.getNext());
            curN.setNext(cur);
            pre.setNext(curN);
            pre = cur;
        }
        return dummp.getNext();
    }

}
