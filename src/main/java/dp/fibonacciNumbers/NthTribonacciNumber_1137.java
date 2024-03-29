package dp.fibonacciNumbers;

// https://leetcode.com/problems/n-th-tribonacci-number/description/?envType=study-plan&id=dynamic-programming-i
public class NthTribonacciNumber_1137 {
    public int tribonacci(int n) {
        // dp, bottom up approach
        // time O(n)
        // space O(n)
        if(n < 2)
            return n;
        if(n == 2)
            return 1;

        int[] dp = new int[n + 1];
        dp[0] = 0;
        dp[1] = 1;
        dp[2] = 1;

        for(int i = 3; i <=n; i++)
            dp[i] = dp[i - 1] + dp[i - 2] + dp[i - 3];

        return dp[n];
    }
}
