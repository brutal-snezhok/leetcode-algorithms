package pramp;

import java.util.Arrays;

/*
* You’re testing a new driverless car that is located at the Southwest (bottom-left) corner of an n×n grid.
* The car is supposed to get to the opposite, Northeast (top-right), corner of the grid.
* Given n, the size of the grid’s axes,
* write a function numOfPathsToDest that returns the number of the possible paths the driverless car can take.
*
* input:  n = 4

output: 5 # since there are five possibilities:
          # “EEENNN”, “EENENN”, “ENEENN”, “ENENEN”, “EENNEN”,
          # where the 'E' character stands for moving one step
          # East, and the 'N' character stands for moving one step
          # North (so, for instance, the path sequence “EEENNN”
          # stands for the following steps that the car took:
          # East, East, East, North, North, North)
* */
public class NumberOfPaths {
    // solution1
    static int numOfPathsToDest(int n) {
        // top down dp
        // time O(n^2)
        // space O(n^2)

        int[][] memo = new int[n][n];
        for (int[] arr : memo)
            Arrays.fill(arr, -1);

        return dfs(n - 1, n - 1, memo);
    }

    private static int dfs(int r, int c, int[][] memo) {
        if (r < 0 || c < 0)
            return 0;
        if (r < c)
            return memo[r][c] = 0;
        if (r == 0 && c == 0)
            return memo[r][c] = 1;

        int val = dfs(r - 1, c, memo) + dfs(r, c - 1, memo);

        return memo[r][c] = val;
    }


    // solution2
    static int numOfPathsToDest2(int n) {
        // bottom up dp
        // time O(n^2)
        // space O(n^2)

        if (n == 1)
            return n;

        int[][] dp = new int[n][n];
        Arrays.fill(dp[0], 1);

        for (int r = 1; r < n; r++) {
            for (int c = 1; c < n; c++) {
                if (r > c)
                    continue;

                dp[r][c] = dp[r - 1][c] + dp[r][c - 1];
            }
        }

        return dp[n - 1][n - 1];
    }

    // solution3
    static int numOfPathsToDest3(int n) {
        // bottom up dp
        // time O(n^2)
        // space O(n), remember only last two rows

        if (n == 1)
            return n;

        int[] prevRow = new int[n];
        int[] currRow = new int[n];
        Arrays.fill(prevRow, 1);

        for (int r = 1; r < n; r++) {
            for (int c = 1; c < n; c++) {
                if (r == c)
                    currRow[c] = prevRow[c];
                else
                    currRow[c] = prevRow[c] + currRow[c - 1];
            }
            prevRow = currRow;
        }

        return currRow[n - 1];
    }
}
