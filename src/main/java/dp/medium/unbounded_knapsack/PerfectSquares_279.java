package dp.medium.unbounded_knapsack;

// https://leetcode.com/problems/perfect-squares/description/
public class PerfectSquares_279 {
    // solution1
    public int numSquares1(int n) {
        // brute force, TLE
        // time O(n^n), ??
        // space O(n)

        if(n < 4)
            return n;

        int ans = n;

        for(int i = 1; i * i <= n; i++)
            ans = Math.min(ans, 1 + numSquares1(n - i * i));

        return ans;
    }

    // solution2
    public int numSquares2(int n) {
        // top down, memo
        // time O(n*sqrt(n))
        // space O(n)

        int[] memo = new int[n + 1];

        return dfs(n, memo);
    }

    private int dfs(int n, int[] memo) {
        if (n < 4)
            return n;

        if (memo[n] != 0)
            return memo[n];

        int ans = n;

        for (int i = 1; i * i <= n; i++) {
            int square = i * i;
            ans = Math.min(ans, 1 + dfs(n - square, memo));
        }

        return memo[n] = ans;
    }

    // solution3
    public int numSquares3(int n) {
        // bottom up
        // time O(n*sqrt(n))
        // space O(n)

        int[] dp = new int[n + 1];
        dp[0] = 0;

        for(int i = 1; i <= n; i++) {
            dp[i] = i;

            for(int j = 1; j * j <= i; j++) {
                int square = j * j;
                dp[i] = Math.min(dp[i], 1 + dp[i - square]);
            }
        }

        return dp[n];
    }
}
