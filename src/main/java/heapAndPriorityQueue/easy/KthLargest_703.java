package heapAndPriorityQueue.easy;

import java.util.PriorityQueue;
import java.util.Queue;

// https://leetcode.com/problems/kth-largest-element-in-a-stream/
class KthLargest_703 {

    Queue<Integer> q;
    int k;

    // time O(nlogk)
    // space O(k)
    public KthLargest_703(int k, int[] nums) {
        q = new PriorityQueue<>(k);
        this.k = k;

        for(int num : nums)
            add(num);
    }

    // time O(mlogk), m time call add method
    // space O(k)
    public int add(int val) {
        if(q.size() < k) {
            q.add(val);
            return q.peek();
        }

        if(q.peek() < val) {
            q.poll();
            q.add(val);
        }

        return q.peek();
    }
}

/**
 * Your KthLargest object will be instantiated and called as such:
 * KthLargest obj = new KthLargest(k, nums);
 * int param_1 = obj.add(val);
 */
