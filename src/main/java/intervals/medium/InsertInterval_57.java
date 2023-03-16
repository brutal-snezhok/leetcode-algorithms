package intervals.medium;

import java.util.ArrayList;
import java.util.List;

// https://leetcode.com/problems/insert-interval/
public class InsertInterval_57 {
    // solution1
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


    // solution2
    public static int[][] insert2(int[][] intervals, int[] newInterval) {
        // time O(n)
        // space O(n)

        // insert interval in correct place
        // then merge this intervals
        // return output

        if(intervals.length == 0) {
            int[][] res = new int[1][2];
            res[0] = newInterval;
            return res;
        }

        int[][] updatedIntervals = insertNewInterval2(intervals, newInterval);
        List<int[]> listIntervals = new ArrayList<>();
        listIntervals.add(updatedIntervals[0]);

        for(int i = 1; i < updatedIntervals.length; i++) {
            int[] prev = listIntervals.get(listIntervals.size() - 1);
            int[] next = updatedIntervals[i];

            if(prev[1] >= next[0])
                prev[1] = Math.max(prev[1], next[1]);
            else
                listIntervals.add(next);
        }

        int[][] res = new int[listIntervals.size()][2];
        int i = 0;
        for(int[] interval : listIntervals)
            res[i++] = interval;

        return res;
    }

    private static int[][] insertNewInterval2(int[][] intervals, int[] newInterval) {
        int[][] res = new int[intervals.length + 1][2];

        int i = 0;
        boolean isNotInserted = true;
        for(int[] interval : intervals) {
            if(isNotInserted && interval[0] >= newInterval[0]) {
                res[i++] = newInterval;
                isNotInserted = false;
            }

            res[i++] = interval;
        }
        if(isNotInserted) { // to handle case intervals = [[1, 5]], newInterval = [2, 7]
            res[i++] = newInterval;
            isNotInserted = false;
        }

        return res;
    }

    public static void main(String[] args) {
        insert2(new int[][]{{1, 5}}, new int[]{2, 7});
    }
}
