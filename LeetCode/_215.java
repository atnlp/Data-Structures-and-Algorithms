/*
215. 数组中的第K个最大元素
在未排序的数组中找到第 k 个最大的元素。请注意，你需要找的是数组排序后的第 k 个最大的元素，而不是第 k 个不同的元素。
*/

import java.util.PriorityQueue;
class Solution {
	// 使用优先队列
    public int findKthLargest(int[] nums, int k) {
        PriorityQueue<Integer> pq = new PriorityQueue<> ();
        for(int num: nums){
            pq.add(num);
            if(pq.size() > k)
                pq.poll();
        }
        return pq.peek();
    }
}