package heap_and_priority_queue.medium;

import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Queue;

// https://leetcode.com/problems/find-the-kth-largest-integer-in-the-array/description/
public class KthLargestIntegerInTheArray_1985 {
    public String kthLargestNumber(String[] nums, int k) {
        // solution1: minHeap
        // time O(nlogk)
        // space O(k)

        // compare firstly by length, if they equal, then compare lexicographically
        Comparator<String> comp = Comparator.comparingInt(String::length).thenComparing(String::compareTo);
        Queue<String> minHeap = new PriorityQueue<>(comp);
        for(String str : nums) {
            minHeap.add(str);
            if(minHeap.size() > k)
                minHeap.poll();
        }

        return minHeap.peek();
    }

    public String kthLargestNumber2(String[] nums, int k) {
        // solution2: maxHeap
        // time O(nlogk + klogk)
        // space O(n)

        // compare firstly by length, if they equal, then compare lexicographically
        Comparator<String> comp = Comparator.comparingInt(String::length)
                .reversed()
                .thenComparing(Comparator.reverseOrder());
        Queue<String> maxHeap = new PriorityQueue<>(comp);
        for(String str : nums)
            maxHeap.add(str);

        while(k-- > 1)
            maxHeap.poll();

        return maxHeap.peek();
    }
}
