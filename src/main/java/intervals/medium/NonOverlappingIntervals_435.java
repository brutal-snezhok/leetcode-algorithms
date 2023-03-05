package intervals.medium;

import java.util.Arrays;

// https://leetcode.com/problems/non-overlapping-intervals/description/
public class NonOverlappingIntervals_435 {
    public int eraseOverlapIntervals(int[][] intervals) {
        // time O(nlogn)
        // space O(logn) due to sorting

        Arrays.sort(intervals, (arr1, arr2) -> arr1[0] - arr2[0]);
        int res = 0;
        int prevEnd = intervals[0][1];

        for(int i = 1; i < intervals.length; i++) {
            int currStart = intervals[i][0];
            int currEnd = intervals[i][1];

            if(currStart >= prevEnd)
                prevEnd = currEnd;
            else {
                prevEnd = Math.min(prevEnd, currEnd);
                res++;
            }
        }

        return res;
    }
}
