package intervals.medium;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

// https://leetcode.com/problems/merge-intervals/
public class MergeIntervals_56 {
    // solution1
    public int[][] merge1(int[][] intervals) {
        // time O(nlogn)
        // space O(n), if count res arr. And O(logn) if do not count res arr

        // sort intervals by start position
        // create list of intervals and add to it first interval
        // go through all intervals and compare last end interval from list with curr start
        // merge if it is necessary

        Arrays.sort(intervals, (arr1, arr2) -> arr1[0] - arr2[0]);
        List<int[]> merged = new ArrayList<>();
        merged.add(intervals[0]);

        for (int i = 1; i < intervals.length; i++) {
            int[] prev = merged.get(merged.size() - 1);
            int[] curr = intervals[i];

            if (prev[1] >= curr[0])
                prev[1] = Math.max(prev[1], curr[1]);
            else
                merged.add(curr);
        }

        int size = merged.size();
        return merged.toArray(new int[size][2]);
    }
}
