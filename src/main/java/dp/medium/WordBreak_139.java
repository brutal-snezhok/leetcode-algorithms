package dp.medium;

import java.util.List;

// https://leetcode.com/problems/word-break/
public class WordBreak_139 {
    public boolean wordBreak(String s, List<String> wordDict) {
        // time O(n*n*m), n - length of s, m - number of words in dic
        // space O(n)

        int n = s.length();
        boolean[] dp = new boolean[n + 1];
        dp[n] = true;

        for(int i = n - 1; i >= 0; i--) {
            for(String word : wordDict) {
                int l = word.length();
                if(i + l <= n && s.substring(i, i + l).equals(word))
                    dp[i] = dp[i + l];

                if(dp[i])
                    break;
            }
        }

        return dp[0];
    }
}
