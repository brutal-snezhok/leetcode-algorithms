package slidingWindow.easy;

// https://leetcode.com/problems/best-time-to-buy-and-sell-stock/description/
public class BestTimeToBuyAndSellStock_121 {
    public int maxProfit(int[] prices) {
        // time O(n)
        // space O(1)

        int currMin = Integer.MAX_VALUE;
        int maxProfit = 0;
        for (int price : prices) {
            if (price < currMin)
                currMin = price;
            else {
                maxProfit = Math.max(maxProfit, price - currMin);
            }
        }

        return maxProfit;
    }
}
