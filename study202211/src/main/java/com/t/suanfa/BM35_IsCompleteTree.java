package com.t.suanfa;

import java.util.LinkedList;
import java.util.Queue;

/**
 * https://www.nowcoder.com/practice
 * 描述
 * 给定一个二叉树，确定他是否是一个完全二叉树。
 *
 * 完全二叉树的定义：若二叉树的深度为 h，除第 h 层外，其它各层的结点数都达到最大个数，第 h 层所有的叶子结点都连续集中在最左边，这就是完全二叉树。（第 h 层可能包含 [1~2h] 个节点）
 *
 * 数据范围：节点数满足 1 \le n \le 100 \1≤n≤100
 *
 *
 *
 * 题目主要信息：
 * 判断给定二叉树是否为完全二叉树
 * 首先我们需要知道什么是完全二叉树：叶子节点只能出现在最下层和次下层，且最下层的叶子节点集中在树的左部。
 * 需要注意的是，满二叉树肯定是完全二叉树，而完全二叉树不一定是满二叉树。
 *
 *
 *
 * 方法：层次遍历（推荐使用）
 * 知识点：队列
 *
 * 队列是一种仅支持在表尾进行插入操作、在表头进行删除操作的线性表，插入端称为队尾，删除端称为队首，因整体类似排队的队伍而得名。它满足先进先出的性质，元素入队即将新元素加在队列的尾，元素出队即将队首元素取出，它后一个作为新的队首。
 *
 * 思路：
 *
 * 对完全二叉树最重要的定义就是叶子节点只能出现在最下层和次下层，所以我们想到可以使用队列辅助进行层次遍历——从上到下遍历所有层，每层从左到右，只有次下层和最下层才有叶子节点，其他层出现叶子节点就意味着不是完全二叉树。
 *
 * 具体做法：
 *
 * step 1：先判断空树一定是完全二叉树。
 * step 2：初始化一个队列辅助层次遍历，将根节点加入。
 * step 3：逐渐从队列中弹出元素访问节点，如果遇到某个节点为空，进行标记，代表到了完全二叉树的最下层，若是后续还有访问，则说明提前出现了叶子节点，不符合完全二叉树的性质。
 * step 4：否则，继续加入左右子节点进入队列排队，等待访问。
 *
 *
 * 复杂度分析：
 *
 * 时间复杂度：O(n)O(n)O(n)，其中nnn为二叉树节点数，层次遍历最坏情况下遍历每一个节点
 * 空间复杂度：O(n)O(n)O(n)，最坏情况下，层次队列的最大空间为O(n)O(n)O(n)
 */
public class BM35_IsCompleteTree {


    public static void main(String[] args) {
        BM35_IsCompleteTree test  = new BM35_IsCompleteTree();
        TreeNode tree = test.getTree();
        boolean complete = isCompleteTree(tree);
        System.out.println("complete:" + complete);


        TreeNode completeTree = test.getCompleteTree();
        boolean complete2 = isCompleteTree(completeTree);
        System.out.println("complete2:" + complete2);
    }


    public static boolean isCompleteTree (TreeNode root) {
        //空树一定是完全二叉树
        if(root == null)
            return true;
        //辅助队列
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        TreeNode cur;
        //定义一个首次出现的标记位
        boolean notComplete = false;
        while(!queue.isEmpty()){
            cur = queue.poll();
            //标记第一次遇到空节点
            if(cur == null){
                notComplete = true;
                continue;
            }
            //后续访问已经遇到空节点了，说明经过了叶子
            if(notComplete)
                return false;
            queue.offer(cur.left);
            queue.offer(cur.right);
        }
        return true;
    }

    /**
     * 构建非完全二叉树
     * 1;2;3;4;5;
     * @return
     */
    private TreeNode getCompleteTree() {
        TreeNode root = new TreeNode(1);
        TreeNode left1 = new TreeNode(2);
        TreeNode right1 = new TreeNode(3);

        root.left = left1;
        root.right = right1;
        TreeNode left2 = new TreeNode(4);
        TreeNode right2 = new TreeNode(5);
        left1.left = left2;
        left1.right = right2;
        return root;
    }


    /**
     * 构建非完全二叉树
     * 1;2;3;4;#;#;5;#;6;#;#;#;#;#;#;7;8;
     * @return
     */
    private TreeNode getTree() {
        TreeNode root = new TreeNode(1);
        TreeNode left1 = new TreeNode(2);
        TreeNode right1 = new TreeNode(3);

        root.left = left1;
        root.right = right1;
        TreeNode left2 = new TreeNode(4);
        left1.left = left2;
        TreeNode right3 = new TreeNode(6);
        left2.right = right3;
        TreeNode left4 = new TreeNode(7);
        TreeNode right4 = new TreeNode(8);
        right3.left = left4;
        right3.right = right4;
        TreeNode right1_2 = new TreeNode(5);
        right1.right = right1_2;
        return root;
    }


    class TreeNode {
        int val = 0;
        TreeNode left = null;
        TreeNode right = null;
        public TreeNode(int val) {
          this.val = val;
        }
  }

}
