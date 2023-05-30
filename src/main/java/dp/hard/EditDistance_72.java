package dp.hard;

// https://leetcode.com/problems/edit-distance/description/
public class EditDistance_72 {
    // solution1
    public int minDistance1(String word1, String word2) {
        // recursive, TLE
        // time O(3^n), n = max(k, l), k - length of w1, l - length of w2
        // space O(n)

        return dfs(word1, word2, 0, 0);
    }

    private int dfs(String w1, String w2, int p1, int p2) {
        if(p1 == w1.length())
            return w2.length() - p2;
        if(p2 == w2.length())
            return w1.length() - p1;

        int res = w1.length() + w2.length();
        if(w1.charAt(p1) == w2.charAt(p2))
            res = Math.min(res, dfs(w1, w2, p1 + 1, p2 + 1));
        else {
            // Insert
            res = Math.min(res, dfs(w1, w2, p1, p2 + 1) + 1);

            // Delete
            res = Math.min(res, dfs(w1, w2, p1 + 1, p2) + 1);

            // Replace
            res = Math.min(res, dfs(w1, w2, p1 + 1, p2 + 1) + 1);
        }

        return res;
    }

    // solution2
    public int minDistance2(String word1, String word2) {
        // top down, memo
        // time O(m*n)
        // space O(m*n)

        int n = word1.length();
        int m = word2.length();
        Integer[][] memo = new Integer[n][m];

        return dfs(word1, word2, 0, 0, memo);
    }

    private int dfs(String w1, String w2, int p1, int p2, Integer[][] memo) {
        if(p1 == w1.length())
            return w2.length() - p2;
        if(p2 == w2.length())
            return w1.length() - p1;
        if(memo[p1][p2] != null)
            return memo[p1][p2];

        int res = w1.length() + w2.length();
        if(w1.charAt(p1) == w2.charAt(p2))
            res = Math.min(res, dfs(w1, w2, p1 + 1, p2 + 1, memo));
        else {
            // Insert
            res = Math.min(res, dfs(w1, w2, p1, p2 + 1, memo) + 1);

            // Delete
            res = Math.min(res, dfs(w1, w2, p1 + 1, p2, memo) + 1);

            // Replace
            res = Math.min(res, dfs(w1, w2, p1 + 1, p2 + 1, memo) + 1);
        }

        return memo[p1][p2] = res;
    }

    // soolution3
    public int minDistance3(String word1, String word2) {
        // bottom up
        // time O(m*n)
        // space O(m*n)

        int n = word1.length();
        int m = word2.length();
        int[][] dp = new int[n + 1][m + 1];

        // Set values for base cases
        for(int p1 = 0; p1 < n; p1++)
            dp[p1][m] = n - p1;

        for(int p2 = 0; p2 < m; p2++)
            dp[n][p2] = m - p2;

        dp[n][m] = 0;

        for(int p1 = n - 1; p1 >= 0; p1--) {
            for(int p2 = m - 1; p2 >= 0; p2--) {
                int res = n + m;
                if(word1.charAt(p1) == word2.charAt(p2))
                    res = Math.min(res, dp[p1 + 1][p2 + 1]);
                else {
                    // Insert
                    res = Math.min(res, dp[p1][p2 + 1] + 1);

                    // Delete
                    res = Math.min(res, dp[p1 + 1][p2] + 1);

                    // Replace
                    res = Math.min(res, dp[p1 + 1][p2 + 1] + 1);
                }
                dp[p1][p2] = res;
            }
        }

        return dp[0][0];
    }

    public static void main(String[] args) {
        EditDistance_72 editDistance_72 = new EditDistance_72();
        editDistance_72.minDistance3("horse", "ros");

    }
}
