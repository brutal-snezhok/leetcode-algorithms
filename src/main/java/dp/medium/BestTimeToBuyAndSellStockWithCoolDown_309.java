package dp.medium;

import java.util.Arrays;

// https://leetcode.com/problems/best-time-to-buy-and-sell-stock-with-cooldown/
public class BestTimeToBuyAndSellStockWithCoolDown_309 {
    public int maxProfit1(int[] prices) {
        // recursive
        // time O(2^n)
        // space O(n)

        return dfs1(prices, 0, true);
    }

    private int dfs1(int[] prices, int day, boolean buy) {
        if(day >= prices.length)
            return 0;

        int res = dfs1(prices, day + 1, buy); // no transactions this day
        if(buy) {
            res = Math.max(res, dfs1(prices, day + 1, false) - prices[day]);
        } else {
            res = Math.max(res, dfs1(prices, day + 2, true) + prices[day]);
        }

        return res;
    }

    // solution2
    public int maxProfit(int[] prices) {
        // dp with memo
        // time O(n)
        // space O(n)
        int dp[][] = new int[prices.length + 2][2];
        for(int[] row : dp)
            Arrays.fill(row, -1);

        return dfs(prices, 0, true, dp);
    }

    private int dfs(int[] prices, int day, boolean buy, int[][] dp) {
        if(day >= prices.length)
            return 0;
        if(dp[day][buy ? 1 : 0] != -1)
            return dp[day][buy ? 1 : 0];

        int res = dfs(prices, day + 1, buy, dp); // no transactions this day
        if(buy) {
            res = Math.max(res, dfs(prices, day + 1, false, dp) - prices[day]);
        } else {
            res = Math.max(res, dfs(prices, day + 2, true, dp) + prices[day]);
        }

        return dp[day][buy ? 1 : 0] = res;
    }

    // solution3
    public static int maxProfit3(int[] prices) {
        // iterative
        // time O(n)
        // space O(n)
        int dp[][] = new int[prices.length + 2][2];

        for(int day = prices.length + 1; day >= 0; day--) {
            for(int buy = 0; buy <= 1; buy++) {
                if(day >= prices.length)
                    continue;

                int res = dp[day + 1][buy]; // no transactions this day

                if(buy == 1)
                    res = Math.max(res, dp[day + 1][0] - prices[day]);
                else
                    res = Math.max(res, dp[day + 2][1] + prices[day]);

                dp[day][buy] = res;
            }
        }

        return dp[0][1];
    }

    // solution4
    public static int maxProfit4(int[] prices) {
        // iterative, optimized
        // time O(n)
        // space O(1)
        int[][] dp = new int[3][2];

        for(int day = prices.length + 1; day >= 0; day--) {
            for(int buy = 0; buy <= 1; buy++) {
                if(day >= prices.length)
                    continue;
                int res = dp[(day + 1) % 3][buy]; // no transactions this day

                if(buy == 1)
                    res = Math.max(res, dp[(day + 1) % 3][0] - prices[day]);
                else
                    res = Math.max(res, dp[(day + 2) % 3][1] + prices[day]);

                dp[day % 3][buy] = res;
            }
        }

        return dp[0][1];
    }

    // solution5
    public int maxProfit5(int[] prices) {
        // iterative, optimized
        // time O(n)
        // space O(1)

        int[][] dp = new int[2][2];

        for(int day = prices.length - 1; day >= 0; day--) {
            for(int buy = 0; buy <= 1; buy++) {
                if(day >= prices.length)
                    continue;
                int res = dp[(day + 1) % 2][buy]; // no transactions this day

                if(buy == 1)
                    res = Math.max(res, dp[(day + 1) % 2][0] - prices[day]);
                else
                    res = Math.max(res, dp[(day + 2) % 2][1] + prices[day]);

                dp[day % 2][buy] = res;
            }
        }

        return dp[0][1];
    }

    public static void main(String[] args) {
        maxProfit3(new int[]{1,2,3,0,2});
    }
}
