package dp.palindromic;

// https://leetcode.com/problems/minimum-insertion-steps-to-make-a-string-palindrome/description/
public class MinimumInsertionStepsToMakeAStringPalindrome_1312 {
    public int minInsertions(String s) {
        // bottom up
        // time O(n^2)
        // space O(n^2)

        int n = s.length();
        return n - lps(s, n);
    }

    // longest palindromic subsequence
    private int lps(String s, int n) {
        int[][] dp = new int[n][n];

        for (int start = n - 1; start >= 0; start--) {
            dp[start][start] = 1;

            for (int end = start + 1; end < n; end++) {
                if (s.charAt(start) == s.charAt(end))
                    dp[start][end] = 2 + dp[start + 1][end - 1];
                else
                    dp[start][end] = Math.max(dp[start + 1][end], dp[start][end - 1]);
            }
        }

        return dp[0][n - 1];
    }
}
