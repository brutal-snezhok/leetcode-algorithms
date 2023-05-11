package heapAndPriorityQueue.hard;

import java.util.PriorityQueue;
import java.util.Queue;

// https://leetcode.com/problems/kth-smallest-number-in-multiplication-table/description/
public class KthSmallestNumberInMultiplicationTable_668 {
    // TODO: check binary search solution
    // solution1, MLE
    public int findKthNumber(int m, int n, int k) {
        // time O(n*m*logk)
        // space O(k)

        Queue<int[]> minHeap = new PriorityQueue<>(k, (a, b) -> a[0] * a[1] - b[0] * b[1]);
        for(int i = 1; i <= Math.min(m, k); i++)
            minHeap.add(new int[]{i, 1});

        int res = 0;
        int count = 0;
        while(!minHeap.isEmpty()) {
            int[] peek = minHeap.poll();
            count++;
            res = peek[0] * peek[1];
            if(count == k)
                break;

            peek[1] += 1;
            if(peek[1] <= n)
                minHeap.add(peek);
        }

        return res;
    }
}
