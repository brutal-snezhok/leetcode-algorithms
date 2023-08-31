package dp.longest_common_substr;

// https://leetcode.com/problems/longest-palindromic-subsequence/description/
public class LongestPalindromicSubsequence_516 {
    // solution1
    public int longestPalindromeSubseq1(String s) {
        // brute force, TLE
        // time O(2^n)
        // space O(n)

        // l == r -> return 1
        // l > r -> return 0
        // char(l) == char(r) -> return 2 + lps(l + 1, r - 1)
        // max(lps(l + 1, r), lps(l, r + 1));

        int n = s.length();

        return lps(s, 0, n - 1);
    }

    private int lps(String s, int l, int r) {
        if(l > r)
            return 0;
        if(l == r)
            return 1;
        if(s.charAt(l) == s.charAt(r))
            return 2 + lps(s, l + 1, r - 1);

        return Math.max(lps(s, l + 1, r), lps(s, l, r - 1));
    }

    // solution2
    public int longestPalindromeSubseq2(String s) {
        // top down, dp
        // time O(n*n)
        // space O(n*n)

        // l == r -> return 1
        // l > r -> return 0
        // char(l) == char(r) -> return 2 + lps(l + 1, r - 1)
        // max(lps(l + 1, r), lps(l, r + 1));

        int n = s.length();
        Integer[][] memo = new Integer[n][n];

        return lps(s, 0, n - 1, memo);
    }

    private int lps(String s, int l, int r, Integer[][] memo) {
        if(l > r)
            return 0;
        if(l == r)
            return 1;
        if(memo[l][r] != null)
            return memo[l][r];
        if(s.charAt(l) == s.charAt(r))
            return memo[l][r] = 2 + lps(s, l + 1, r - 1, memo);

        return memo[l][r] = Math.max(lps(s, l + 1, r, memo), lps(s, l, r - 1, memo));
    }

    // solution3
    public int longestPalindromeSubseq3(String s) {
        // bottom up, dp
        // time O(n*n)
        // space O(n*n)

        /*
            i
            0 1 2 3 4
            b b b a b

        * */

        int n = s.length();
        int[][] dp = new int[n][n];

        for(int r = n - 1; r >= 0; r--) {
            dp[r][r] = 1;
            for(int c = r + 1; c < n; c++) {
                if(s.charAt(r) == s.charAt(c))
                    dp[r][c] = 2 + dp[r + 1][c - 1];
                else
                    dp[r][c] = Math.max(dp[r + 1][c], dp[r][c - 1]);
            }
        }

        return dp[0][n - 1];
    }


    public static void main(String[] args) {
        LongestPalindromicSubsequence_516 longest = new LongestPalindromicSubsequence_516();
        longest.longestPalindromeSubseq3("bbbab");
    }
}
