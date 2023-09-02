package dp.unbounded_knapsack;

// https://leetcode.com/problems/coin-change-ii/description/
public class CoinChangeII_518 {
    // solution1
    public int change1(int amount, int[] coins) {
        // top down, TLE
        // time O(2^min(n, amount))
        // space O(min(n, amount))

        return backtracking(amount, coins, 0, 0);
    }

    private int backtracking(int target, int[] coins, int currSum, int ind) {
        if(currSum == target)
            return 1;

        if(currSum > target || ind == coins.length)
            return 0;

        int take = 0;
        if(coins[ind] + currSum <= target)
            take = backtracking(target, coins, currSum + coins[ind], ind);

        int notTake = backtracking(target, coins, currSum, ind + 1);

        return take + notTake;
    }

    // solution2
    public int change2(int amount, int[] coins) {
        // top down with memo
        // time O(n * amount)
        // space O(n * amount)

        Integer[][] memo = new Integer[coins.length][amount + 1];
        return backtracking(amount, coins, 0, 0, memo);
    }

    private int backtracking(int target, int[] coins, int currSum, int ind, Integer[][] memo) {
        if(currSum == target)
            return 1;
        if(currSum > target || ind == coins.length)
            return 0;
        if(memo[ind][currSum] != null)
            return memo[ind][currSum];

        int take = 0;
        if(coins[ind] + currSum <= target)
            take = backtracking(target, coins, currSum + coins[ind], ind, memo);

        int notTake = backtracking(target, coins, currSum, ind + 1, memo);

        return memo[ind][currSum] = take + notTake;
    }

    // solution3
    public int change(int amount, int[] coins) {
        // solution: https://leetcode.com/problems/coin-change-ii/solutions/675444/java-3-dp-solutions-o-amount-in-space-clean-concise/
        // bottom up
        // time O(n * amount)
        // space O(n * amount)

        int n = coins.length;
        int[][] dp = new int[n + 1][amount + 1];
        for (int i = 0; i <= n; i++)
            dp[i][0] = 1; // have 1 way to change 0 amount

        for(int am = 1; am <= amount; am++) {
            for(int i = 1; i <= n; i++) {
                if(am >= coins[i - 1])
                    dp[i][am] = dp[i][am - coins[i - 1]];

                dp[i][am] += dp[i - 1][am]; // skip ith coin
            }
        }

        return dp[n][amount];
    }
}
