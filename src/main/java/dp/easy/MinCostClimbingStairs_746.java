package dp.easy;

import java.util.HashMap;
import java.util.Map;

// https://leetcode.com/problems/min-cost-climbing-stairs/
public class MinCostClimbingStairs_746 {
    // solution1
    public int minCostClimbingStairs1(int[] cost) {
        // time O(2^n)
        // space O(n)
        // TLE

        int n = cost.length;
        int firstVal = backtracking1(cost, n - 1);
        int secondVal = backtracking1(cost, n - 2);
        return Math.min(firstVal, secondVal);
    }

    private int backtracking1(int[] cost, int n) {
        if(n < 0)
            return 0;
        if(n == 0 || n == 1)
            return cost[n];

        int firstVal = backtracking1(cost, n - 1);
        int secondVal = backtracking1(cost, n - 2);
        return cost[n] + Math.min(firstVal, secondVal);
    }

    // solution2
    Map<Integer, Integer> map = new HashMap<>();

    public int minCostClimbingStairs2(int[] cost) {
        // dp top down with memo
        // time O(n)
        // space O(n)

        int n = cost.length;
        map.put(0, cost[0]);
        map.put(1, cost[1]);
        return Math.min(backtracking2(cost, n - 1), backtracking2(cost, n - 2));
    }

    private int backtracking2(int[] cost, int n) {
        if(n < 0)
            return 0;

        if(map.containsKey(n))
            return map.get(n);

        int firstVal = backtracking2(cost, n - 1);
        int secondVal = backtracking2(cost, n - 2);

        map.put(n - 1, firstVal);
        map.put(n - 2, secondVal);

        return cost[n] + Math.min(firstVal ,secondVal);
    }

    public int minCostClimbingStairs3(int[] cost) {
        // bottom up approach
        // time O(n)
        // space O(n)

        int n = cost.length;
        int[] dp = new int[n + 1];
        dp[0] = cost[0];
        dp[1] = cost[1];

        for(int i = 2; i <= n; i++) {
            int val = i == n ? 0 : cost[i];
            dp[i] = Math.min(dp[i - 1], dp[i - 2]) + val;
        }

        return dp[n];
    }

    public int minCostClimbingStairs4(int[] cost) {
        // bottom up approach, optimized
        // time O(n)
        // space O(1)

        int n = cost.length;
        int prev = cost[1];
        int prevPrev = cost[0];

        for(int i = 2; i <= n; i++) {
            int val = i == n ? 0 : cost[i];
            int temp = Math.min(prev, prevPrev) + val;
            prevPrev = prev;
            prev = temp;
        }

        return prev;
    }

    public static void main(String[] args) {
        MinCostClimbingStairs_746 stairs = new MinCostClimbingStairs_746();
        stairs.minCostClimbingStairs1(new int[]{10, 15, 20});
    }
}
