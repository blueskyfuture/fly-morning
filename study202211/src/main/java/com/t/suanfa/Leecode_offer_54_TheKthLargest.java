package com.t.suanfa;

/**
 *
 * https://leetcode.cn/problems/er-cha-sou-suo-shu-de-di-kda-jie-dian-lcof/
 * 剑指 Offer 54. 二叉搜索树的第k大节点
 * 给定一棵二叉搜索树，请找出其中第 k 大的节点的值。
 *
 * 示例 1:
 *
 * 输入: root = [3,1,4,null,2], k = 1
 *    3
 *   / \
 *  1   4
 *   \
 *    2
 * 输出: 4
 * 示例 2:
 *
 * 输入: root = [5,3,6,2,4,null,null,1], k = 3
 *        5
 *       / \
 *      3   6
 *     / \
 *    2   4
 *   /
 *  1
 * 输出: 4
 *
 *
 * 限制：
 *
 * 1 ≤ k ≤ 二叉搜索树元素个数
 *
 *
 *
 * int ans = 0;
 *     bool tag = 0;
 *     void inorder(TreeNode* root,int &k,bool &tag){
 *         if(root==NULL || tag)return;
 *         inorder(root->right,k,tag);
 *         k--;
 *         if(!k){
 *             ans = root->val;
 *             tag = 1;
 *         }
 *         inorder(root->left,k,tag);
 *     }
 *     int kthLargest(TreeNode* root, int k) {
 *         inorder(root,k,tag);
 *         return ans;
 *     }
 *
 */
public class Leecode_offer_54_TheKthLargest {

    int res;
    int index = 0; //计数器
    public int kthLargest(TreeNode root, int k) {
        dfs(root,k);
        return res;
    }
    void dfs(TreeNode root ,int k)
    {
        if(root == null) return;
        dfs(root.right,k); //右
        index++;
        if(k == index) res = root.val; //根
        dfs(root.left,k); //左
    }
//————————————————
//    版权声明：本文为CSDN博主「林小鹿@」的原创文章，遵循CC 4.0 BY-SA版权协议，转载请附上原文出处链接及本声明。
//    原文链接：https://blog.csdn.net/weixin_45629285/article/details/118753779


//下面方法自己写的有问题





    int answer = 0;
    boolean tag = false;
    public int kthLargest1(TreeNode root, int k) {
        inorder(root,k,tag);
        return answer;
    }

    void inorder(TreeNode root,int k,boolean tag){
        if(root == null || tag)
            return;
        inorder(root.right,k,tag);
        k--;
        if(k < 0){
            answer = root.val;
            tag = true;
        }
        inorder(root.left,k,tag);
    }

    class TreeNode {
      int val;
      TreeNode left;
      TreeNode right;
      TreeNode(int x) { val = x; }
  }
}

