package intervals.medium;

import java.util.Arrays;

// https://leetcode.com/problems/non-overlapping-intervals/description/
public class NonOverlappingIntervals_435 {
    // solution1
    public int eraseOverlapIntervals1(int[][] intervals) {
        // time O(nlogn)
        // space O(n) due to sorting

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

    // solution2
    public int eraseOverlapIntervals2(int[][] intervals) {
        // time O(nlogn)
        // space O(n)

        /**
         1. sort
         2. create var to save end value of prev interval
         3. compare prev end with curr start if intersect then res++
         */

        Arrays.sort(intervals, (arr1, arr2) -> arr1[0] - arr2[0]);

        int prevEnd = intervals[0][1];
        int res = 0;
        for(int i = 1; i < intervals.length; i++) {
            int[] curr = intervals[i];

            if(prevEnd > curr[0]) {
                res++;
                prevEnd = Math.min(prevEnd, curr[1]);
            } else
                prevEnd = curr[1];
        }

        return res;
    }
}
