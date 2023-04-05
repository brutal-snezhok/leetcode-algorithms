package arraysHashing.easy;

import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Set;

// https://leetcode.com/problems/third-maximum-number/description/
public class ThirdMaximumNumber_414 {
    public int thirdMax(int[] nums) {
        // time O(n)
        // space O(1)

        // will use min heap with 3 unique elements, and set with elements that in heap to see if they are unique
        // after all iterationon the top of heap will have res
        // if there will be less than 3 el -> return first max

        Queue<Integer> minHeap = new PriorityQueue<>();
        Set<Integer> set = new HashSet<>();

        for(int num : nums) {
            if(set.contains(num))
                continue;

            if(set.size() < 3) {
                minHeap.add(num);
                set.add(num);
                continue;
            }

            if(minHeap.peek() < num) {
                set.remove(minHeap.poll());
                minHeap.add(num);
                set.add(num);
            }
        }

        if(minHeap.size() == 1)
            return minHeap.peek();
        else if(minHeap.size() == 2) {
            minHeap.poll();
            return minHeap.poll();
        }

        return minHeap.poll();
    }
}
