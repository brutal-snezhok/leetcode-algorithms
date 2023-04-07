package dp.easy;

import java.util.HashMap;
import java.util.Map;

// https://leetcode.com/problems/climbing-stairs/
public class ClimbingStairs_70 {
    // solution1
    // TLE
    public int climbStairs1(int n) {
        // dfs
        // time O(2^n)
        // space O(n)

        return dfs1(n, 0);
    }

    private int dfs1(int n, int sum) {
        if(sum == n)
            return 1;
        else if(sum < n) {
            int count = dfs1(n, sum + 1) + dfs1(n, sum + 2);
            return count;
        }

        return 0;
    }

    // solution2
    Map<Integer, Integer> map = new HashMap<>();

    public int climbStairs2(int n) {
        // dfs with memo
        // time O(n)
        // space O(n)

        return dfs2(n, 0);
    }

    private int dfs2(int n, int sum) {
        if(map.containsKey(sum))
            return map.get(sum);

        if(sum == n)
            return 1;
        else if(sum < n) {
            int count = dfs2(n, sum + 1) + dfs2(n, sum + 2);
            map.put(sum ,count);
            return count;
        }

        return 0;
    }

    // solution3
    public int climbStairs3(int n) {
        // dp
        // time O(n)
        // space O(n)
        if(n == 1)
            return 1;

        int[] dp = new int[n + 1];
        dp[n] = 1;
        dp[n - 1] = 1;
        for(int i = n - 2; i >= 0; i--)
            dp[i] = dp[i + 1] + dp[i + 2];

        return dp[0];
    }

    public int climbStairs3a(int n) {
        // starting from the beginning
        // time O(n)
        // space O(n)

        if(n == 1)
            return 1;

        int[] dp = new int[n + 1];
        dp[0] = 1;
        dp[1] = 1;

        for(int i = 2; i <= n; i++)
            dp[i] = dp[i - 1] + dp[i - 2];

        return dp[n];
    }

    // solution4
    public int climbStairs4(int n) {
        // dp
        // time O(n)
        // space O(1)
        if(n == 1)
            return 1;

        int one = 1;
        int two = 1;

        for(int i = n - 2; i >= 0; i--) {
            int temp = one;
            one = one + two;
            two = temp;
        }

        return one;
    }

    public int climbStairs(int n) {
        // starting from the beginning
        // time O(n)
        // space O(1)

        if(n == 1)
            return 1;

        int prev = 1;
        int prevPrev = 1;

        for(int i = 2; i <= n; i++) {
            int temp = prev + prevPrev;
            prevPrev = prev;
            prev = temp;
        }

        return prev;
    }
}
