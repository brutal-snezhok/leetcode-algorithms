package dp.fibonacciNumbers;

// https://leetcode.com/problems/minimum-path-sum/description/
public class MinimumPathSum_64 {
    public int minPathSum(int[][] grid) {
        // time O(m * n)
        // space O(m * n)

        // dp[i][j] = min(dp[i - 1][j], dp[i][j - 1]) - min cost to reach [i][j] cell

        int m = grid.length;
        int n = grid[0].length;

        int[][] dp = new int[m][n];
        dp[0][0] = grid[0][0];

        for(int i = 0; i < m; i++) {
            for(int j = 0; j < n; j++) {
                if(i == 0 && j == 0)
                    continue;

                dp[i][j] = grid[i][j] +
                        Math.min(i - 1 >= 0 ? dp[i - 1][j] : Integer.MAX_VALUE,
                                j - 1 >= 0 ? dp[i][j - 1] : Integer.MAX_VALUE);
            }
        }

        return dp[m - 1][n - 1];
    }
}
