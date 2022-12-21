package com.t.suanfa;


/**
 * 问题：
 * 描述
 * 在数组中的两个数字，如果前面一个数字大于后面的数字，则这两个数字组成一个逆序对。输入一个数组,求出这个数组中的逆序对的总数P。并将P对1000000007取模的结果输出。 即输出P mod 1000000007
 *
 * 数据范围：  对于 50\%50% 的数据, size\leq 10^4size≤10
 * 4
 *
 * 对于 100\%100% 的数据, size\leq 10^5size≤10
 * 5
 *
 * 数组中所有数字的值满足 0 \le val \le 10^90≤val≤10
 * 9
 *
 *
 * 要求：空间复杂度 O(n)O(n)，时间复杂度 O(nlogn)O(nlogn)
 * 输入描述：
 * 题目保证输入的数组中没有的相同的数字
 * 示例1
 * 输入：
 * [1,2,3,4,5,6,7,0]
 * 返回值：
 * 7
 *
 *
 * 方法二：归并统计法
 *
 * 相信大家都知道归并排序这个算法吧，如果不知道的，可以移至这里排序先去学习一波~
 *
 * 那么，我们先来说说归并算法吧，归并算法讲究一个先分后并！
 *
 * 先分：分呢，就是将数组分为两个子数组，两个子数组分为四个子数组，依次向下分，直到数组不能再分为止！
 *
 * 后并：并呢，就是从最小的数组按照顺序合并，从小到大或从大到小，依次向上合并，最后得到合并完的顺序数组！
 *
 * 介绍完归并排序，我们来说说归并统计法，我们要在哪个步骤去进行统计呢？
 *
 * 归并统计法，关键点在于合并环节，在合并数组的时候，当发现右边的小于左边的时候，此时可以直接求出当前产生的逆序对的个数。
 *
 * 举个例子：
 *
 * 在合并 {4 ,5} {1 , 2} 的时候，首先我们判断 1 < 4，我们即可统计出逆序对为2，为什么呢？这利用了数组的部分有序性。因为我们知道 {4 ,5} 这个数组必然是有序的，因为是合并上来的。此时当 1比4小的时候，证明4以后的数也都比1大，此时就构成了从4开始到 {4,5}这个数组结束，这么多个逆序对（2个），此时利用一个临时数组，将1存放起来，接着比较2和4的大小，同样可以得到有2个逆序对，于是将2也放进临时数组中，此时右边数组已经完全没有元素了，则将左边剩余的元素全部放进临时元素中，最后将临时数组中的元素放进原数组对应的位置。
 *
 * 最后接着向上合并~
 */
public class BM20_InversePairs {
    int count = 0;
    public int InversePairs(int [] array) {
        // 长度小于2则无逆序对
        if(array.length < 2)
            return 0;
        // 进入归并
        mergeSort(array,0,array.length-1);
        return count;
    }

    public void mergeSort(int[] array,int left,int right){
        // 找分割点
        int mid = left+(right-left)/2;
        if(left < right){
            // 左子数组
            mergeSort(array,left,mid);
            // 右子数组
            mergeSort(array,mid+1,right);
            // 并
            merge(array,left,mid,right);
        }
    }

    public void merge(int[] array,int left,int mid,int right){
        // 创建临时数组，长度为此时两个子数组加起来的长度
        int[] arr =  new int[right-left+1];
        // 临时数组的下标起点
        int c = 0;
        // 保存在原数组的起点下标值
        int s = left;
        // 左子数组的起始指针
        int l = left;
        // 右子数组的起始指针
        int r = mid+1;
        while(l <= mid && r <= right ){
            // 当左子数组的当前元素小的时候，跳过，无逆序对
            if(array[l] <= array[r]){
                // 放入临时数组
                arr[c] = array[l];
                // 临时数组下标+1
                c++;
                // 左子数组指针右移
                l++;
            }else{ // 否则，此时存在逆序对
                // 放入临时数组
                arr[c] = array[r];
                // 逆序对的个数为    左子数组的终点- 当前左子数组的当前指针
                count += mid+1-l;
                count %= 1000000007;
                // 临时数组+1
                c++;
                // 右子数组的指针右移
                r++;
            }
        }

        // 左子数组还有元素时，全部放入临时数组
        while(l <= mid)
            arr[c++] = array[l++];
        // 右子数组还有元素时，全部放入临时数组
        while(r <= right)
            arr[c++] = array[r++];
        // 将临时数组中的元素放入到原数组的指定位置
        for(int num:arr){
            array[s++] = num;
        }
    }

    public static void main(String[] args) {
        BM20_InversePairs test = new BM20_InversePairs();
        int[] arr = {1,2,3,4,5,6,7,0};
        int i = test.InversePairs(arr);
        System.out.println("i==" + i);
    }
}
