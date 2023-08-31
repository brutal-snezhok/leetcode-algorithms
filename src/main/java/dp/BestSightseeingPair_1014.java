package dp;

// https://leetcode.com/problems/best-sightseeing-pair/
public class BestSightseeingPair_1014 {
    // solution1
    public int maxScoreSightseeingPair1(int[] values) {
        // brute force
        // time O(n^2)
        // space O(1)

        int n = values.length;
        int res = Integer.MIN_VALUE;
        for(int i = 0; i < n; i++)
            for(int j = i + 1; j < n; j++)
                res  = Math.max(res, values[i] + values[j] + i - j);

        return res;
    }

    // solution2
    public int maxScoreSightseeingPair2(int[] values) {
        // time O(n)
        // space O(1)

        // from values[i] + values[j] + i - j
        // we want to maximize first = values[i] + i
        // we want to maximize second = values[j] - j, where j > i

        int n = values.length;
        int res = Integer.MIN_VALUE;
        int first = values[0] + 0;

        for(int i = 1; i < n; i++) {
            res = Math.max(res, first + values[i] - i);
            first = Math.max(first, values[i] + i);
        }

        return res;
    }
}
