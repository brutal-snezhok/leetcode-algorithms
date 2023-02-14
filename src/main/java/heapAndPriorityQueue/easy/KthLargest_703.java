package heapAndPriorityQueue.easy;

import java.util.PriorityQueue;
import java.util.Queue;

// https://leetcode.com/problems/kth-largest-element-in-a-stream/
class KthLargest_703 {

    Queue<Integer> minHeap;
    int k;

    // time O(nlogk)
    // space O(k)
    public KthLargest_703(int k, int[] nums) {
        minHeap = new PriorityQueue<>(k);
        this.k = k;

        for(int num : nums)
            add(num);
    }

    // time O(mlogk), m time call add method
    // space O(k)
    public int add(int val) {
        if(minHeap.size() < k) {
            minHeap.add(val);
            return minHeap.peek();
        }

        if(minHeap.peek() < val) {
            minHeap.poll();
            minHeap.add(val);
        }

        return minHeap.peek();
    }

    public int add2(int val) {
        minHeap.add(val);
        while(minHeap.size() != k)
            minHeap.poll();

        return minHeap.peek();
    }
}

/**
 * Your KthLargest object will be instantiated and called as such:
 * KthLargest obj = new KthLargest(k, nums);
 * int param_1 = obj.add(val);
 */
