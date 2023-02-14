package heapAndPriorityQueue.easy;

import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Queue;

// https://leetcode.com/problems/last-stone-weight/
public class LastStoneWeight_1046 {
    public int lastStoneWeight(int[] stones) {
        // time O(nlogn)
        // space O(n)

        Queue<Integer> maxHeap = new PriorityQueue<>(Comparator.reverseOrder());
        for(int stone : stones)
            maxHeap.add(stone);

        int res = 0;
        while(maxHeap.size() != 0) {
            int stone1 = maxHeap.poll();
            if(maxHeap.size() != 0) {
                int stone2 = maxHeap.poll();
                if(stone1 != stone2)
                    maxHeap.add(Math.abs(stone2 - stone1));
            } else {
                res = stone1;
                break;
            }
        }

        return res;
    }
}
