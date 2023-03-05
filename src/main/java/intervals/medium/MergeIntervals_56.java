package intervals.medium;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

// https://leetcode.com/problems/merge-intervals/
public class MergeIntervals_56 {
    public int[][] merge(int[][] intervals) {
        // time O(nlogn)
        // space O(n), if count res arr. And O(logn) if do not count res arr

        // sort intervals by start position
        // create list of intervals and add to it first interval
        // go through all intervals and compare last end interval from list with curr start
        // merge if it is necessary

        Arrays.sort(intervals, (arr1, arr2) -> arr1[0] - arr2[0]);
        List<int[]> listOfIntervals = new ArrayList<>();
        for (int[] interval : intervals) {
            if (listOfIntervals.isEmpty()) {
                listOfIntervals.add(interval);
                continue;
            }

            int size = listOfIntervals.size();
            int[] prev = listOfIntervals.get(size - 1);
            if (prev[1] < interval[0]) // prev end < curr start
                listOfIntervals.add(interval);
            else { // prev end >= curr start
                prev[1] = Math.max(prev[1], interval[1]);
            }
        }

        int[][] res = new int[listOfIntervals.size()][2];
        int i = 0;
        for (int[] interval : listOfIntervals)
            res[i++] = interval;

        return res;
    }
}
