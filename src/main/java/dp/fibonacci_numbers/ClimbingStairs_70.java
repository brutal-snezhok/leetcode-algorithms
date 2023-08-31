package dp.fibonacci_numbers;

// https://leetcode.com/problems/climbing-stairs/description/
public class ClimbingStairs_70 {
    // solution1, TLE
    public int climbStairs1(int n) {
        // time O(2^n)
        // space O(n)

        if(n <= 2)
            return n;

        return climbStairs1(n - 1) + climbStairs1(n - 2);
    }

    // solution2
    public int climbStairs2(int n) {
        // top down, with memo
        // time O(n)
        // space O(n)

        if(n <= 2)
            return n;
        int[] memo = new int[n + 1];
        memo[1] = 1;
        memo[2] = 2;

        return dfs(n, memo);
    }

    private int dfs(int n, int[] memo) {
        if(memo[n] != 0)
            return memo[n];

        memo[n] = dfs(n - 1, memo) + dfs(n - 2, memo);
        return memo[n];
    }

    // solution3
    public int climbStairs3(int n) {
        // bottom up, dp
        // time O(n)
        // space O(n)

        // dp[i] - number of ways to reach i step

        if(n == 1)
            return 1;
        int[] dp = new int[n + 1];
        dp[1] = 1;
        dp[2] = 2;

        for(int i = 3; i <= n; i++) {
            dp[i] = dp[i - 1] + dp[i - 2];
        }

        return dp[n];
    }

    // solution4
    public int climbStairs4(int n) {
        // bottom up, space optimized
        // time O(n)
        // space O(1)

        if(n <= 2)
            return n;

        int prevPrev = 1;
        int prev = 2;
        for(int i = 3; i <= n; i++) {
            int temp = prevPrev + prev;
            prevPrev = prev;
            prev = temp;
        }

        return prev;
    }
}
