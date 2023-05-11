package heapAndPriorityQueue.medium;

import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Queue;

// https://leetcode.com/problems/kth-smallest-element-in-a-sorted-matrix/description/
public class KthSmallestElementInSortedMatrix_378 {
    // solution1
    public int kthSmallest1(int[][] matrix, int k) {
        // time O(n^2*log(n^2))
        // space O(n^2)

        int n = matrix.length;
        int[] res = new int[n * n];
        int ind = 0;

        for(int[] row : matrix)
            for(int el : row)
                res[ind++] = el;

        Arrays.sort(res);

        return res[k - 1];
    }

    // solution2
    public int kthSmallest2(int[][] matrix, int k) {
        // time O(n^2*log(k))
        // space O(k)

        Queue<Integer> maxHeap = new PriorityQueue<>(k, (el1, el2) -> el2 - el1);

        for(int[] row : matrix)
            for(int el : row) {
                maxHeap.add(el);

                if(maxHeap.size() > k)
                    maxHeap.poll();
            }

        return maxHeap.peek();
    }

    // solution3
    public int kthSmallest3(int[][] matrix, int k) {
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

    // solution4
    public int kthSmallest4(int[][] matrix, int k) {
        // time O(min(k, n) + k*logn)
        // space O(n)

        // create minHeap
        // add k values to heap from first col, if there are less then k values then add val one by one from next cols
        // count how many times you take el from heap
        // on k time you will take the res

        final int ROWS = matrix.length;
        final int COLS = matrix[0].length;

        Queue<int[]> minHeap = new PriorityQueue<>(k, (arr1, arr2) -> matrix[arr1[0]][arr1[1]] - matrix[arr2[0]][arr2[1]]); // {row, col}

        for(int i = 0; i < Math.min(k, ROWS); i++)
            minHeap.add(new int[]{i, 0});

        int count = 0;
        int res = 0;
        while(!minHeap.isEmpty()) {
            int[] peek = minHeap.poll();
            count++;
            res = matrix[peek[0]][peek[1]];
            if(count == k)
                break;

            peek[1] += 1;
            if(peek[1] < COLS)
                minHeap.add(peek);
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
