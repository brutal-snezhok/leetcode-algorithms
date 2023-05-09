package heapAndPriorityQueue.medium;

import java.util.PriorityQueue;
import java.util.Queue;

// https://leetcode.com/problems/kth-smallest-element-in-a-sorted-matrix/description/
public class KthSmallestElementInSortedMatrix_378 {
    public int kthSmallest(int[][] matrix, int k) {
        // time O(min(k, n) + nlogk)
        // space O(k)

        // create minHeap
        // add all first els of every row
        // 1: [1, 10, 12]
        //  [1, 10, 12, 5, 11, 13, 9, 13]

        Queue<Pair> minHeap = new PriorityQueue<>((p1, p2) -> matrix[p1.row][p1.col] - matrix[p2.row][p2.col]);
        for(int i = 0; i < matrix.length && i < k; i++)
            minHeap.add(new Pair(i, 0));

        int count = 0;
        int res = 0;
        while(!minHeap.isEmpty()) {
            Pair p = minHeap.poll();
            count++;
            res = matrix[p.row][p.col];
            if(count == k)
                break;

            p.col += 1;
            if(p.col < matrix[0].length)
                minHeap.add(p);
        }

        return res;
    }

    class Pair {
        int row;
        int col;

        public Pair(int row, int col) {
            this.row = row;
            this.col = col;
        }
    }
}
