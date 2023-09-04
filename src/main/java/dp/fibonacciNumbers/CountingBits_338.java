package dp.fibonacciNumbers;

// https://leetcode.com/problems/counting-bits/description/
public class CountingBits_338 {
    public int[] countBits(int n) {
        // dp, bottom up approach
        // time O(n)
        // space O(n)
        if(n == 0)
            return new int[]{0};

        int[] dp = new int[n + 1];
        dp[0] = 0;

        for(int i = 1; i <= n; i++) {
            if(i % 2 == 0)
                dp[i] = dp[i / 2];
            else
                dp[i] = dp[i / 2] + 1;
        }

        return dp;
    }
}
