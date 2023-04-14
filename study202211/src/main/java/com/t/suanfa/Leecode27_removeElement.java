package com.t.suanfa;

/**
 * https://leetcode.cn/problems/remove-duplicates-from-sorted-array/
 * 27. 移除元素
 * 给你一个数组 nums 和一个值 val，你需要 原地 移除所有数值等于 val 的元素，并返回移除后数组的新长度。
 *
 * 不要使用额外的数组空间，你必须仅使用 O(1) 额外空间并 原地 修改输入数组。
 *
 * 元素的顺序可以改变。你不需要考虑数组中超出新长度后面的元素。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/remove-element
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class Leecode27_removeElement {

    public static void main(String[] args) {
        Leecode27_removeElement test  = new Leecode27_removeElement();
        int[] nums = {0,0,1,1,1,2,2,3,3,4};
        int res = test.removeElement(nums,2);
        System.out.println("res: " + res);
    }

    public int removeElement(int[] nums, int val) {
        if(nums == null) return 0;
        int len = nums.length;
        int slow = 0;
        int fast = 0;
        while(fast < len){
            if(nums[fast] != val){
                if(fast > slow)
                    nums[slow] = nums[fast];
                slow ++;
            }
            fast ++;
        }
        return slow;
    }
}
