package intervals.medium;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

// https://leetcode.com/problems/remove-covered-intervals/
public class RemoveCoveredIntervals_1288 {
    public static int removeCoveredIntervals(int[][] intervals) {
        // time O(nlogn)
        // space O(n)

        Arrays.sort(intervals, (arr1, arr2) -> arr1[0] - arr2[0] == 0 ? arr2[1] - arr1[1] : arr1[0] - arr2[0]);

        List<int[]> res = new ArrayList<>();
        res.add(intervals[0]);
        for(int i = 1; i < intervals.length; i++) {
            int prevStart = res.get(res.size() - 1)[0];
            int prevEnd = res.get(res.size() - 1)[1];
            int currStart = intervals[i][0];
            int currEnd = intervals[i][1];

            if(prevStart <= currStart && prevEnd >= currEnd)
                continue;

            res.add(intervals[i]);
        }

        return res.size();
    }

    public static void main(String[] args) {
        removeCoveredIntervals(new int[][]{
                {1,2},
                {1,4},
                {3,4}
        });
    }
}
