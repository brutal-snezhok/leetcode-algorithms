package dp.medium;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

// https://leetcode.com/problems/best-time-to-buy-and-sell-stock-with-transaction-fee/description/
public class BestTimeToBuyAndSellStockWithTransactionFee_714 {
    // solution1
    public int maxProfit1(int[] prices, int fee) {
        // backtracking, TLE
        // time O(3^n)
        // space O(n)
        return backtracking(0, false, prices, fee);
    }

    private int backtracking(int pos, boolean bought, int[] prices, int fee) {
        if (pos == prices.length)
            return 0;

        int maxProfit = 0;

        if (bought)
            // sell
            maxProfit = Math.max(maxProfit, backtracking(pos + 1, false, prices, fee) + prices[pos]);
        else
            // buy
            maxProfit = Math.max(maxProfit, backtracking(pos + 1, true, prices, fee) - prices[pos] - fee);

        // do nothing
        maxProfit = Math.max(maxProfit, backtracking(pos + 1, bought, prices, fee));

        return maxProfit;
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
}
