package dp.easy;

public class FibonacciNumber_509 {
    public int fib(int n) {
        // dp, bottom up approach
        // time O(n)
        // space O(n)
        if(n == 0)
            return 0;
        if(n == 1)
            return 1;

        int[] dp = new int[n + 1];
        dp[0] = 0;
        dp[1] = 1;
        for(int i = 2; i <= n; i++)
            dp[i] = dp[i - 1] + dp[i - 2];

        return dp[n];
    }
}
