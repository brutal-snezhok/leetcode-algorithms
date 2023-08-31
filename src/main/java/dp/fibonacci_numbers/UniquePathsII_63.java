package dp.fibonacci_numbers;

// https://leetcode.com/problems/unique-paths-ii/
public class UniquePathsII_63 {
    // solution1
    public int uniquePathsWithObstacles1(int[][] grid) {
        // brute-force, TLE
        // time O(2^(n + m))
        // space O(n + m)

        return backtracking1(grid, 0, 0);
    }

    private int backtracking1(int[][] grid, int r, int c) {
        int m = grid.length; int n = grid[0].length;
        if(r >= m || c >= n || grid[r][c] == 1)
            return 0;
        if(r == m - 1 && c == n - 1)
            return 1;

        int rightMove = backtracking1(grid, r, c + 1);
        int downMove = backtracking1(grid, r + 1, c);

        return rightMove + downMove;
    }

    // solution2
    public int uniquePathsWithObstacles2(int[][] grid) {
        // top-down, memo
        // time O(n * m)
        // space O(n * m)

        int[][] memo = new int[grid.length][grid[0].length];

        return backtracking2(grid, 0, 0, memo);
    }

    private int backtracking2(int[][] grid, int r, int c, int[][] memo) {
        int m = grid.length; int n = grid[0].length;
        if(r >= m || c >= n || grid[r][c] == 1)
            return 0;
        if(r == m - 1 && c == n - 1)
            return 1;
        if(memo[r][c] != 0)
            return memo[r][c];

        int rightMove = backtracking2(grid, r, c + 1, memo);
        int downMove = backtracking2(grid, r + 1, c, memo);
        memo[r][c] = rightMove + downMove;

        return memo[r][c];
    }

    // solution3
    public int uniquePathsWithObstacles3(int[][] grid) {
        // bottom-up, tabulation
        // time O(n * m)
        // space O(n * m)

        int m = grid.length; int n = grid[0].length;
        if(grid[0][0] == 1 || grid[m - 1][n - 1] == 1)
            return 0;

        int[][] dp = new int[m][n];
        dp[0][0] = 1;

        for(int r = 0; r < m; r++) {
            for(int c = 0; c < n; c++) {
                if(r == 0 && c == 0)
                    continue;

                int rightMove = 0;
                int downMove = 0;
                if(c - 1 >= 0 && grid[r][c - 1] != 1)
                    rightMove = dp[r][c - 1];

                if(r - 1 >= 0 && grid[r - 1][c] != 1)
                    downMove = dp[r - 1][c];

                dp[r][c] = rightMove + downMove;
            }
        }


        return dp[m - 1][n - 1];
    }

    /*
     *  public int uniquePathsWithObstacles(int[][] grid) {
     *         // bottom-up, tabulation
     *         // time O(n * m)
     *         // space O(n * m)
     *
     *         int m = grid.length;
     *         int n = grid[0].length;
     *
     *         if(grid[m - 1][n - 1] == 1 || grid[0][0] == 1)
     *             return 0;
     *
     *         int[][] dp = new int[m][n];
     *         dp[0][0] = 1;
     *
     *         for(int i = 0; i < m; i++) {
     *             for(int j = 0; j < n; j++) {
     *                 if(i == 0 && j == 0)
     *                     continue;
     *
     *                 if(grid[i][j] == 1)
     *                     dp[i][j] = 0;
     *                 else {
     *                     dp[i][j] = dp[Math.max(i - 1, 0)][j] + dp[i][Math.max(j - 1, 0)];
     *                 }
     *             }
     *         }
     *
     *         return dp[m - 1][n - 1];
     *     }
     */

}
