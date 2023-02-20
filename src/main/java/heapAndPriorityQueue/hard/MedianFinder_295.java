package heapAndPriorityQueue.hard;

import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Queue;

// https://leetcode.com/problems/find-median-from-data-stream/
public class MedianFinder_295 {
//    Queue<Integer> small; // max heap
//    Queue<Integer> large; // min heap
//
//    public MedianFinder_295() {
//        small = new PriorityQueue<>(Comparator.reverseOrder());
//        large = new PriorityQueue<>();
//    }
//
//    // time O(logn) - every addNum() call
//    public void addNum(int num) {
//        small.add(num);
//
//        // make sure every num in small is <= than in large
//        if(!small.isEmpty() && !large.isEmpty() && small.peek() > large.peek()) {
//            int val = small.poll();
//            large.add(val);
//        }
//
//        // uneven size
//        if(small.size() > large.size() + 1) {
//            int val = small.poll();
//            large.add(val);
//        }
//        if(large.size() > small.size() + 1) {
//            int val = large.poll();
//            small.add(val);
//        }
//    }
//
//    // time O(1)
//    public double findMedian() {
//        if(small.size() > large.size())
//            return small.peek();
//        if(large.size() > small.size())
//            return large.peek();
//
//        return ((double)small.peek() + large.peek()) / 2;
//    }


    // more intuitive solution
    Queue<Integer> maxHeap;
    Queue<Integer> minHeap;

    // create maxHeap for values before mid
    // create minHeap for values after mid
    // to findMedian check if size maxHeap and minHeap is equal -> return (top from maxHeap + top from minHeap) / 2
    // if size is not equal then return top of heap with the biggest size

    // maxHeap always will have the same size or on 1 el bigger than minHeap
    // that why in findMedian before returning res need to do balancing one time
    // since maxHeap can have values [1,4,3,9] minHeap[7,8,6]
    // after balancing - maxHeap[1,3,4,6] minHeap[7,8,9]

    public MedianFinder_295() {
        maxHeap = new PriorityQueue<>(Comparator.reverseOrder());
        minHeap = new PriorityQueue<>();
    }

    // time O(logn)
    public void addNum(int num) {
        int n = maxHeap.size();
        int m = minHeap.size();

        if(n != m) {
            if(maxHeap.peek() < num)
                minHeap.add(num);
            else {
                minHeap.add(maxHeap.poll());
                maxHeap.add(num);
            }
        } else
            maxHeap.add(num);
    }

    // O(1)
    public double findMedian() {
        int n = maxHeap.size();
        int m = minHeap.size();

        if(n != 0 && m != 0 && maxHeap.peek() > minHeap.peek()) {
            minHeap.add(maxHeap.poll());
            maxHeap.add(minHeap.poll());
        }

        if(maxHeap.size() == minHeap.size())
            return (maxHeap.peek() + minHeap.peek()) / 2.0;
        else
            return maxHeap.peek();
    }
}
