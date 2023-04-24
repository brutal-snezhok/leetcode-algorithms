package dp.medium;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

// https://leetcode.com/problems/best-time-to-buy-and-sell-stock-with-transaction-fee/description/
public class BestTimeToBuyAndSellStockWithTransactionFee_714 {
    // solution1
    public int maxProfit1(int[] prices, int fee) {
        // recursive, TLE
        // time O(2^n)
        // space O(n)

        return dfs1(prices, fee, 0, true);
    }

    private int dfs1(int[] prices, int fee, int i, boolean buy) {
        if(i >= prices.length)
            return 0;

        int res = dfs1(prices, fee, i + 1, buy); // do nothing
        if(buy)
            res = Math.max(res, dfs1(prices, fee, i + 1, false) - prices[i]);
        else
            res = Math.max(res, dfs1(prices, fee, i + 1, true) + prices[i] - fee);

        return res;
    }

    // solution2
    Map<String, Integer> map = new HashMap<>();

    public int maxProfit2(int[] prices, int fee) {
        // top down dp
        // time O(n)
        // space O(n)

        return dp(0, false, prices, fee);
    }

    private int dp(int pos, boolean bought, int[] prices, int fee) {
        if (map.containsKey(pos + String.valueOf(bought)))
            return map.get(pos + String.valueOf(bought));

        if (pos == prices.length)
            return 0;

        int maxProfit = 0;

        if (bought)
            // sell
            maxProfit = Math.max(maxProfit, dp(pos + 1, false, prices, fee) + prices[pos]);
        else
            // buy
            maxProfit = Math.max(maxProfit, dp(pos + 1, true, prices, fee) - prices[pos] - fee);

        // do nothing
        maxProfit = Math.max(maxProfit, dp(pos + 1, bought, prices, fee));

        map.put(pos + String.valueOf(bought), maxProfit);

        return maxProfit;
    }

    /*
     public int maxProfit(int[] prices, int fee) {
        // dp, top-down
        // time O(n)
        // space O(n)

       int[][] dp = new int[prices.length][2];
       for(int[] row : dp)
           Arrays.fill(row, -1);

       return dfs(prices, fee, 0, true, dp);
    }

    private int dfs(int[] prices, int fee, int i, boolean buy, int[][] dp) {
        if(i >= prices.length)
            return 0;
        if(dp[i][buy ? 1 : 0] != -1)
            return dp[i][buy ? 1 : 0];

        int res = dfs(prices, fee, i + 1, buy, dp); // do nothing
        if(buy)
            res = Math.max(res, dfs(prices, fee, i + 1, false, dp) - prices[i]);
        else
            res = Math.max(res, dfs(prices, fee, i + 1, true, dp) + prices[i] - fee);

        return dp[i][buy ? 1 : 0] = res;
    }
    */

    // solution3
    public int maxProfit3(int[] prices, int fee) {
        // bottom up, dp
        // time O(n)
        // space O(n)

        int[][] dp = new int[prices.length + 1][2];
        Arrays.fill(dp[0], 0);
        Arrays.fill(dp[1], 0);

        for (int pos = prices.length - 1; pos >= 0; pos--) {
            int bought = 1;
            while (bought >= 0) {
                int maxProfit = 0;

                if (bought == 1)
                    // buy a stock
                    // 0 = true
                    maxProfit = Math.max(maxProfit, dp[pos + 1][0] - prices[pos] - fee);
                else
                    // sell a stock
                    // 1 = false
                    maxProfit = Math.max(maxProfit, dp[pos + 1][1] + prices[pos]);

                // do nothing
                dp[pos][bought] = Math.max(maxProfit, dp[pos + 1][bought]);
                bought--;
            }
        }

        return dp[0][1];
    }

    /*
    public int maxProfit(int[] prices, int fee) {
        // iterative
        // time O(n)
        // space O(n)
        int dp[][] = new int[prices.length + 1][2];

        for(int day = prices.length; day >= 0; day--) {
            for(int buy = 0; buy <= 1; buy++) {
                if(day >= prices.length)
                    continue;

                int res = dp[day + 1][buy]; // no transactions this day

                if(buy == 1)
                    res = Math.max(res, dp[day + 1][0] - prices[day]);
                else
                    res = Math.max(res, dp[day + 1][1] + prices[day] - fee);

                dp[day][buy] = res;
            }
        }

        return dp[0][1];
    }
     */

    // solution4
    public int maxProfit4(int[] prices, int fee) {
        // bottom up, dp optimized
        // time O(n)
        // space O(1)

        int[][] dp = new int[1][2];

        for (int pos = prices.length - 1; pos >= 0; pos--) {
            int[][] oldDp = dp;
            dp = new int[1][2];
            int bought = 1;

            while (bought >= 0) {
                int maxProfit = 0;

                if (bought == 1) {
                    // buy a stock
                    // 0 = true
                    maxProfit = Math.max(maxProfit, oldDp[0][0] - prices[pos] - fee);
                } else {
                    // sell a stock
                    // 1 = false
                    maxProfit = Math.max(maxProfit, oldDp[0][1] + prices[pos]);
                }

                // do nothing
                dp[0][bought] = Math.max(maxProfit, oldDp[0][bought]);
                bought--;
            }
        }

        return dp[0][1];
    }

    /*
    public int maxProfit(int[] prices, int fee) {
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
                    res = Math.max(res, dp[(day + 1) % 2][1] + prices[day] - fee);

                dp[day % 2][buy] = res;
            }
        }

        return dp[0][1];
    }
     */
}
