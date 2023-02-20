package heapAndPriorityQueue.hard;

import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Queue;

// https://leetcode.com/problems/maximum-performance-of-a-team/description/
public class MaximumPerformanceOfTeam_1383 {
    public int maxPerformance(int n, int[] speed, int[] efficiency, int k) {
        // time O(nlogn + nlogk)
        // space O(n + k), O(n) take for sorting arr

        // create 2d arr for pair of {eff, speed}
        // sort in descending order by eff
        // create minHeap of k largets elements
        // if add or remove el to/from heap update currSum
        // compare to res on every iteration

        int[][] pairs = new int[speed.length][2];
        for (int i = 0; i < speed.length; i++) {
            pairs[i][0] = efficiency[i];
            pairs[i][1] = speed[i];
        }

        Arrays.sort(pairs, (p1, p2) -> p2[0] - p1[0]); // sort by eff in decreasing order
        Queue<Integer> minHeap = new PriorityQueue<>();
        long currSum = 0;
        long res = 0;
        for (int[] pair : pairs) {
            if (minHeap.size() == k)
                currSum -= minHeap.poll();

            minHeap.add(pair[1]);
            currSum += pair[1];

            res = Math.max(res, currSum * pair[0]);
        }

        return (int) (res % (long) (1e9 + 7));
    }
}
