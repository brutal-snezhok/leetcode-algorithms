package dp.unbounded_knapsack;

import java.util.Arrays;

// https://leetcode.com/problems/coin-change/description/
public class CoinChange_322 {
    // solution1
    public int coinChange1(int[] coins, int target) {
        // TLE
        // time O(2^amount)
        // space O(amount)

        // can take
        // can not take
        // currSum == amount -> return 0
        // i == coins.length -> return Integer.MAX_VALUE

        int res = dfs1(coins, target, 0);
        return res == Integer.MAX_VALUE ? -1 : res;
    }

    private int dfs1(int[] coins, int target, int i) {
        if(target == 0)
            return 0;
        if(i == coins.length || target < 0)
            return Integer.MAX_VALUE;

        int take = dfs1(coins, target - coins[i], i);
        if(take != Integer.MAX_VALUE) // to avoid overflow
            take += 1;

        int notTake = dfs1(coins, target, i + 1);

        return Math.min(take, notTake);
    }

    // solution2
    public int coinChange(int[] coins, int target) {
        // dp, memo
        // time O(target * n)
        // space O(target * n)

        // can take
        // can not take
        // target == 0 -> return 0
        // i == coins.length -> return Integer.MAX_VALUE

        Integer[][] memo = new Integer[coins.length][target + 1];
        int res = dfs(coins, target, 0, memo);
        return res == Integer.MAX_VALUE ? -1 : res;
    }

    private int dfs(int[] coins, int target, int i, Integer[][] memo) {
        if(target == 0)
            return 0;
        if(i == coins.length || target < 0)
            return Integer.MAX_VALUE;
        if(memo[i][target] != null)
            return memo[i][target];

        int take = dfs(coins, target - coins[i], i, memo);
        if(take != Integer.MAX_VALUE)
            take += 1;

        int notTake = dfs(coins, target, i + 1, memo);

        memo[i][target] = Math.min(take, notTake);
        return memo[i][target];
    }

    public int coinChange2(int[] coins, int amount) {
        // bottom up, dp
        // time O(amount * n), n - number of coins
        // space O(amount)

        int[] dp = new int[amount + 1];
        Arrays.fill(dp, amount + 1);
        dp[0] = 0;

        for(int am = 1; am <= amount; am++) {
            for(int coin : coins) {
                if(am >= coin)
                    dp[am] = Math.min(dp[am], dp[am - coin] + 1);
            }
        }

        return dp[amount] > amount ? -1 : dp[amount];
    }

    public int coinChange3(int[] coins, int target) {
        // bottom up, dp
        // time O(target * n)
        // space O(target * n)

        int n = coins.length;
        Integer[][] dp = new Integer[n + 1][target + 1];

        //Initialization
        for(int i = 1; i < n + 1; i++) dp[i][0] = 0;
        for(int j = 0; j < target + 1; j++) dp[0][j] = Integer.MAX_VALUE - 1;

        for(int i = 1; i < n + 1; i++) {
            for(int j = 1; j < target + 1; j++) {
                if(j >= coins[i - 1])
                    dp[i][j] = Math.min(dp[i - 1][j], dp[i][j - coins[i - 1]] + 1);
                else
                    dp[i][j] = dp[i - 1][j];
            }
        }

        return dp[n][target] == Integer.MAX_VALUE || dp[n][target] == Integer.MAX_VALUE - 1 ? -1 : dp[n][target];
    }

    public static int coinChange4(int[] coins, int amount) {
        // time O(amount * n)
        // space O(amount)

        int[] dp = new int[amount + 1];
        Arrays.fill(dp, Integer.MAX_VALUE - 1); // -1 here is to avoid overflow when add 1 later
        dp[0] = 0;

        for(int am = 1; am <= amount; am++)
            for(int coin : coins)
                if(am >= coin)
                    dp[am] = Math.min(dp[am], dp[am - coin] + 1);

        return dp[amount] == Integer.MAX_VALUE - 1 ? -1 : dp[amount];
    }

    public static void main(String[] args) {
        // coinChange(new int[]{1, 2, 5}, 11);
        // coinChange3(new int[]{2}, 3);
        CoinChange_322 change = new CoinChange_322();
    }
}
