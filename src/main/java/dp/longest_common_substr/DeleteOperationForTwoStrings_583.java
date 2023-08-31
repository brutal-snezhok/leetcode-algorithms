package dp.longest_common_substr;

// https://leetcode.com/problems/delete-operation-for-two-strings/description/
public class DeleteOperationForTwoStrings_583 {
    public int minDistance(String word1, String word2) {
        // bottom up, dp
        // solution based on longest common subseq
        // time O(n * m)
        // space O(n * m)

        // find longest common subseq
        // res = n + m - 2 * lcs

        int n = word1.length();
        int m = word2.length();

        int[][] dp = new int[n + 1][m + 1];
        for(int i = 1; i <= n; i++) {
            for(int j = 1; j <= m; j++) {
                if(word1.charAt(i - 1) == word2.charAt(j - 1))
                    dp[i][j] = 1 + dp[i - 1][j - 1];
                else
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
            }
        }

        return n + m - 2 * dp[n][m];
    }
}
