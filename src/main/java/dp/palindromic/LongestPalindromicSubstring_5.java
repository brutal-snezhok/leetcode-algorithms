package dp.palindromic;

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
        // bottom up
        // time O(n^2)
        // spcace O(n^2)

        int n = s.length();
        boolean[][] dp = new boolean[n][n];
        int resStart = 0;
        int resEnd = 0;

        // start - index of starting substring
        // end - index of ending substring
        for(int start = n - 1; start >= 0; start--) {
            for(int end = start; end < n; end++) {
                // if first and last chars equal
                // then check other string from start + 1 till end - 1
                // or maybe it one char or two chars
                if(s.charAt(start) == s.charAt(end) && (end - start <= 2 || dp[start + 1][end - 1]))
                    dp[start][end] = true;


                // just update res
                if(dp[start][end] && (end - start) > (resEnd - resStart)) {
                    resEnd = end;
                    resStart = start;
                }
            }
        }

        return s.substring(resStart, resEnd + 1);
    }
}
