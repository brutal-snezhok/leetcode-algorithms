package dp.medium.longest_common_substr;

// https://leetcode.com/problems/palindromic-substrings/description/
public class PalindromicSubstrings_647 {
    public int countSubstrings(String s) {
        // bottom up, dp
        // time O(n^2)
        // space O(n^2)

        int n = s.length();
        boolean[][] dp = new boolean[n][n];

        int count = 0;
        for(int i = n - 1; i >= 0; i--) {
            for(int j = i; j < n; j++) {
                dp[i][j] = s.charAt(i) == s.charAt(j) && (j - i <= 2 || dp[i + 1][j - 1]);

                if(dp[i][j])
                    count++;
            }
        }

        return count;
    }
}
