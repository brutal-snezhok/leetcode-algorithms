package intervals.medium;

import java.util.ArrayList;
import java.util.List;

// https://leetcode.com/problems/insert-interval/
public class InsertInterval_57 {
    public int[][] insert(int[][] intervals, int[] newInterval) {
        // time O(n), O(logn) - binary search and O(n) inserting into particular position
        // space O(n), O(n) with returned value

        // find place where newInterval should be inserted
        // do insertion
        // go through all intervals and do merge operation

        int[][] updatedIntervals = insertNewInterval(intervals, newInterval);

        // merge intervals
        List<int[]> list = new ArrayList<>();
        for (int[] interval : updatedIntervals) {
            if (list.isEmpty()) {
                list.add(interval);
                continue;
            }

            int[] prev = list.get(list.size() - 1);
            int prevEnd = prev[1];
            int currStart = interval[0];
            if (prevEnd >= currStart)
                prev[1] = Math.max(prev[1], interval[1]);
            else
                list.add(interval);
        }

        // create res
        return list.toArray(new int[list.size()][2]);
    }

    private int[][] insertNewInterval(int[][] intervals, int[] newInterval) {
        int[][] res = new int[intervals.length + 1][2];

        int insertInd = findInd(intervals, newInterval);
        int i = 0;
        int j = 0;
        while (i < insertInd)
            res[i++] = intervals[j++];

        res[insertInd] = newInterval;
        i++;
        while (j < intervals.length)
            res[i++] = intervals[j++];

        return res;
    }

    private int findInd(int[][] intervals, int[] newInterval) {
        int l = 0;
        int r = intervals.length - 1;
        int res = Integer.MAX_VALUE;

        while (l <= r) {
            int mid = l + (r - l) / 2;

            if (intervals[mid][0] < newInterval[0])
                l = mid + 1;
            else {
                res = mid;
                r = mid - 1;
            }
        }

        if (res == Integer.MAX_VALUE)
            res = intervals.length;

        return res;
    }
}
