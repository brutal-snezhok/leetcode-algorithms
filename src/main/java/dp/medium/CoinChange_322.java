package dp.medium;

import java.util.Arrays;

// https://leetcode.com/problems/coin-change/description/
public class CoinChange_322 {
    public int coinChange1(int[] coins, int amount) {
        // backtracking, TLE
        // time O(amount^n), n - number of coins
        // space O(n), n - max depth of recursion

        return coinChange1(0, coins, amount);
    }

    private int coinChange1(int ind, int[] coins, int amount) {
        if(amount == 0)
            return 0;
        if(amount < 0 || ind >= coins.length)
            return -1;

        int maxVal = amount / coins[ind];
        int minCost = Integer.MAX_VALUE;
        for(int c = 0; c <= maxVal; c++) {
            if(amount < c * coins[ind])
                break;

            int res = coinChange1(ind + 1, coins, amount - c * coins[ind]);
            if(res != -1)
                minCost = Math.min(minCost, res + c);
        }

        return minCost == Integer.MAX_VALUE ? -1 : minCost;
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

    public static int coinChange3(int[] coins, int amount) {
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
        //coinChange(new int[]{1, 2, 5}, 11);
        coinChange3(new int[]{2}, 3);
    }
}
