package heap_and_priority_queue.hard;

import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Queue;

public class MedianFinder_295 {
    Queue<Integer> small; // max heap
    Queue<Integer> large; // min heap

    public MedianFinder_295() {
        small = new PriorityQueue<>(Comparator.reverseOrder());
        large = new PriorityQueue<>();
    }

    // time O(logn) - every addNum() call
    public void addNum(int num) {
        small.add(num);

        // make sure every num in small is <= than in large
        if(!small.isEmpty() && !large.isEmpty() && small.peek() > large.peek()) {
            int val = small.poll();
            large.add(val);
        }

        // uneven size
        if(small.size() > large.size() + 1) {
            int val = small.poll();
            large.add(val);
        }
        if(large.size() > small.size() + 1) {
            int val = large.poll();
            small.add(val);
        }
    }

    // time O(1)
    public double findMedian() {
        if(small.size() > large.size())
            return small.peek();
        if(large.size() > small.size())
            return large.peek();

        return ((double)small.peek() + large.peek()) / 2;
    }
}
