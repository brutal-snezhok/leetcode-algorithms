package dp.easy;

// https://leetcode.com/problems/min-cost-climbing-stairs/
public class MinCostClimbingStairs_746 {
    public int minCostClimbingStairs1(int[] cost) {
        // time O(n)
        // space O(n)

        if (cost.length == 0 || cost.length == 1)
            return 0;

        int[] dp = new int[cost.length + 1];
        dp[0] = 0;
        dp[1] = cost[0];

        for (int i = 2; i < dp.length; i++)
            dp[i] = Math.min(dp[i - 1], dp[i - 2]) + cost[i - 1];

        return Math.min(dp[cost.length], dp[cost.length - 1]);
    }

    public int minCostClimbingStairs2(int[] cost) {
        // time O(n)
        // space O(1)

        if (cost.length == 0 || cost.length == 1)
            return 0;

        int step = 0;
        int doubleStep = cost[0];

        for (int i = 1; i < cost.length; i++) {
            int temp = Math.min(step, doubleStep) + cost[i];
            step = doubleStep;
            doubleStep = temp;
        }

        return Math.min(step, doubleStep);
    }

    public static void main(String[] args) {
        MinCostClimbingStairs_746 stairs = new MinCostClimbingStairs_746();
       // stairs.minCostClimbingStairs1(new int[]{10, 15, 20});
    }
}
