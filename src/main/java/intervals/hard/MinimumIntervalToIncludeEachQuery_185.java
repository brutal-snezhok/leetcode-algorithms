package intervals.hard;

import java.util.*;

// https://leetcode.com/problems/minimum-interval-to-include-each-query/description/
public class MinimumIntervalToIncludeEachQuery_185 {
    public static int[] minInterval(int[][] intervals, int[] queries) {
        // time O(nlogn + qlogq)
        // space O(n + q)

        // Sort queries and intervals.
        //Iterate queries from small to big,
        //and find out all open intervals [l, r],
        //and we add them to a priority queue.
        //Also, we need to remove all closed interval from the queue.

        Map<Integer, Integer> map = new HashMap<>(); // to save [query, res val] for queries

        int[] queriesCopy = queries.clone();
        Arrays.sort(intervals, (arr1, arr2) -> arr1[0] - arr2[0]);
        Arrays.sort(queries);
        Queue<int[]> minHeap = new PriorityQueue<>((arr1, arr2) -> arr1[0] - arr2[0]); // [size, r]
        int i = 0;
        int[] res = new int[queries.length];

        for(int q : queries) {
            // add all possible intervals to minHeap
            while(i < intervals.length && intervals[i][0] <= q) {
                int l = intervals[i][0];
                int r = intervals[i][1];

                minHeap.add(new int[]{r - l + 1, r});
                i++;
            }

            // remove all intervals from minHeap which are no include q
            while(!minHeap.isEmpty() && minHeap.peek()[1] < q)
                minHeap.poll();

            map.put(q, minHeap.isEmpty() ? -1 : minHeap.peek()[0]);
        }

        i = 0;
        for (int q : queriesCopy)
            res[i++] = map.get(q);

        return res;
    }

    public static void main(String[] args) {
        minInterval(new int[][]{
                {4,5},
                {5,8},
                {1,9},
                {8,10},
                {1,6}
        }, new int[] {7,9,3,9,3});
    }
}
