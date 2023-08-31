package dp.longest_common_substr;

// https://leetcode.com/problems/longest-common-subsequence/description/
public class LongestCommonSubsequence_1143 {
    // solution1
    public int longestCommonSubsequence1(String text1, String text2) {
        // brute force, TLE
        // time O(2^(n + m))
        // space O(n+m)

        /**
         i = 0, j = 0, -> 0
         a[i] == b[j] -> 1 + dp[i - 1][j - 1]
         a[i] != b[j] -> max(dp[i - 1][j], dp[i][j - 1])
         */

        return lcs(text1, text2, 0, 0);
    }

    private int lcs(String text1, String text2, int i, int j) {
        if (i == text1.length() || j == text2.length())
            return 0;

        if (text1.charAt(i) == text2.charAt(j))
            return lcs(text1, text2, i + 1, j + 1) + 1;

        return Math.max(lcs(text1, text2, i + 1, j),
                lcs(text1, text2, i, j + 1));
    }

    /*
    * public int longestCommonSubsequence(String text1, String text2) {
        // brute force
        // time O(2^(n + m))
        // space O(n+m)

        int n = text1.length();
        int m = text2.length();

        return lcs(text1, text2, n - 1, m - 1);
}

    private int lcs(String text1, String text2, int i, int j) {
        if(i < 0 || j < 0)
            return 0;

        if(text1.charAt(i) == text2.charAt(j))
            return lcs(text1, text2, i - 1, j - 1) + 1;

        return Math.max(lcs(text1, text2, i - 1, j),
                lcs(text1, text2, i, j - 1));
    }
    */

    // solution2
    public int longestCommonSubsequence2(String text1, String text2) {
        // top down, memo
        // time O(n*m)
        // space O(n*m)

        /**
         i = 0, j = 0, -> 0
         a[i] == b[j] -> 1 + dp[i - 1][j - 1]
         a[i] != b[j] -> max(dp[i - 1][j], dp[i][j - 1])
         */

        Integer[][] memo = new Integer[text1.length()][text2.length()];

        return lcs(text1, text2, 0, 0, memo);
    }

    private int lcs(String text1, String text2, int i, int j, Integer[][] memo) {
        if (i == text1.length() || j == text2.length())
            return 0;

        if(memo[i][j] != null)
            return memo[i][j];

        if (text1.charAt(i) == text2.charAt(j))
            return memo[i][j] = lcs(text1, text2, i + 1, j + 1, memo) + 1;

        return memo[i][j] = Math.max(lcs(text1, text2, i + 1, j, memo),
                lcs(text1, text2, i, j + 1, memo));
    }

    // solution3
    public int longestCommonSubsequence3(String text1, String text2) {
        // bottom up, dp
        // time O(n*m)
        // space O(n*m)

        /**
         i = 0, j = 0, -> 0
         a[i] == b[j] -> 1 + dp[i - 1][j - 1]
         a[i] != b[j] -> max(dp[i - 1][j], dp[i][j - 1])
         */

        int n = text1.length();
        int m = text2.length();

        int[][] dp = new int[n + 1][m + 1];

        for(int i = 1; i <= n; i++) {
            for(int j = 1; j <= m; j++) {
                dp[i][j] = text1.charAt(i - 1) == text2.charAt(j - 1) ? 1 + dp[i - 1][j - 1] : Math.max(dp[i - 1][j], dp[i][j - 1]);
            }
        }

        return dp[n][m];
    }
}
