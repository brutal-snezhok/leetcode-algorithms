package dp.medium.longest_common_substr;

// https://leetcode.com/problems/longest-palindromic-substring/description/
public class LongestPalindromicSubstring_5 {
    // solution1
    public String longestPalindrome1(String s) {
        // bottom up, dp
        // time O(n^3) due to substring func
        // space O(n^2)

        int n = s.length();
        String res = null;
        boolean[][] dp = new boolean[n][n];

        // i - start ind of substring, j - end ind
        // dp[i][j] - true if substring from i to j is palindrome, false otherwise
        for(int i = n - 1; i >= 0; i--)
            for(int j = i; j < n; j++) {
                dp[i][j] = s.charAt(i) == s.charAt(j) && (j - i <= 2 || dp[i + 1][j - 1]);

                if(dp[i][j] && (res == null || j - i + 1 > res.length()))
                    res = s.substring(i, j + 1);
            }


        return res;
    }

    // solution2
    public String longestPalindrome2(String s) {
        // bottom up, dp
        // time O(n^2) use substring only once
        // space O(n^2)

        int n = s.length();
        boolean[][] dp = new boolean[n][n];
        int startInd = 0;
        int endInd = 0;

        // i - start ind of substring, j - end ind
        // dp[i][j] - true if substring from i to j is palindrome, false otherwise
        for(int i = n - 1; i >= 0; i--)
            for(int j = i; j < n; j++) {
                dp[i][j] = s.charAt(i) == s.charAt(j) && (j - i <= 2 || dp[i + 1][j - 1]);

                if(dp[i][j] && j - i > endInd - startInd) {
                    startInd = i;
                    endInd = j;
                }
            }


        return s.substring(startInd, endInd + 1);
    }
}
