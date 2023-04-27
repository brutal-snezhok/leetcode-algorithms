package dp.medium;

import java.util.Arrays;

// https://leetcode.com/problems/unique-paths/
public class UniquePaths_62 {
    // solution1
    public int uniquePaths1(int m, int n) {
        // brute-force, TLE
        // time O(2^(n + m))
        // space O(n + m)

        return uniquePaths1(m, n, 0, 0);
    }

    private int uniquePaths1(int m, int n, int r, int c) {
        if(r >= m || c >= n)
            return 0;
        if(r == m - 1 && c == n - 1)
            return 1;

        int rightMove = uniquePaths1(m, n, r, c + 1);
        int downMove = uniquePaths1(m, n, r + 1, c);

        return rightMove + downMove;
    }

    // solution2
    public int uniquePaths2(int m, int n) {
        // dp, memo
        // time O(n * m))
        // space O(n * m)

        int[][] dp = new int[m][n];
        for(int[] row : dp)
            Arrays.fill(row, -1);
        dp[m - 1][n - 1] = 1;
        uniquePaths2(m, n, 0, 0, dp);

        return dp[0][0];
    }

    private int uniquePaths2(int m, int n, int r, int c, int[][] dp) {
        if(r >= m || c >= n)
            return 0;
        if(r == m - 1 && c == n - 1)
            return 1;
        if(dp[r][c] != -1)
            return dp[r][c];

        int rightMove = uniquePaths2(m, n, r, c + 1, dp);
        int downMove = uniquePaths2(m, n, r + 1, c, dp);
        dp[r][c] = rightMove + downMove;

        return dp[r][c];
    }

    // solution3
    public int uniquePaths3(int m, int n) {
        // dp, tabulation
        // time O(n * m))
        // space O(n * m)

        int[][] dp = new int[m][n];
        dp[0][0] = 1;
        for(int r = 0; r < m; r++)
            for(int c = 0; c < n; c++) {
                if(r == 0 && c == 0)
                    continue;

                int upVal = r - 1 >= 0 ? dp[r - 1][c] : 0;
                int leftVal = c - 1 >= 0 ? dp[r][c - 1] : 0;
                dp[r][c] = upVal + leftVal;
            }

        return dp[m - 1][n - 1];
    }

    public static void main(String[] args) {
        UniquePaths_62 unique = new UniquePaths_62();
        unique.uniquePaths1(3, 2);
    }
}
