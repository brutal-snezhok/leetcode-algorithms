package dp.medium.fibonacci_numbers;

// https://leetcode.com/problems/fibonacci-number/description/
public class FibonacciNumber_509 {
    // solution1
    public int fib1(int n) {
        // time O(2^n)
        // space O(n)
        if(n < 2)
            return n;

        return fib1(n - 2) + fib1(n - 1);
    }

    // solution2
    public int fib2(int n) {
        // dp with memo, top-down
        // time O(n)
        // space O(n)
        if(n < 2)
            return n;

        int[] memo = new int[n + 1];
        memo[0] = 0;
        memo[1] = 1;

        return fib2(n, memo);
    }

    private int fib2(int n, int[] memo) {
        if(memo[n] != 0)
            return memo[n];

        int res = fib2(n - 1) + fib2(n - 2);
        memo[n] = res;

        return res;
    }

    // solution3
    public int fib3(int n) {
        // dp bottom up
        // time O(n)
        // space O(n)
        if(n <= 1)
            return n;

        int[] dp = new int[n + 1];
        dp[0] = 0;
        dp[1] = 1;
        for(int i = 2; i <= n; i++)
            dp[i] = dp[i - 1] + dp[i - 2];

        return dp[n];
    }

    // solution4
    public int fib4(int n) {
        // dp bottom up, optimized space
        // time O(n)
        // space O(1)
        if(n <= 1)
            return n;

        int prevPrev = 0;
        int prev = 1;

        for(int i = 2; i <= n; i++) {
            int temp = prevPrev + prev;
            prevPrev = prev;
            prev = temp;
        }

        return prev;
    }
}
