package dp;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

// https://leetcode.com/problems/word-break/
public class WordBreak_139 {

    // solution1
    public boolean wordBreak1(String s, List<String> wordDict) {
        // time O(2^n), TLE
        // space O(n)

        Set<String> dict = new HashSet<>(wordDict);
        return dfs(s, dict, 0);
    }

    private boolean dfs(String s, Set<String> dict, int startInd) {
        if(startInd == s.length())
            return true;

        for(int i = startInd + 1; i <= s.length(); i++) {
            if(dict.contains(s.substring(startInd, i)) && dfs(s, dict, i))
                return true;
        }

        return false;
    }

    // solution2
    public boolean wordBreak2(String s, List<String> wordDict) {
        // top down, memo
        // time O(n^3)
        // space O(n)

        Set<String> dict = new HashSet<>(wordDict);
        Boolean[] memo = new Boolean[s.length()];

        return dfs(s, dict, 0, memo);
    }

    private boolean dfs(String s, Set<String> dict, int startInd, Boolean[] memo) {
        if(startInd == s.length())
            return true;
        if(memo[startInd] != null)
            return memo[startInd];

        for(int i = startInd + 1; i <= s.length(); i++) {
            if(dict.contains(s.substring(startInd, i)) && dfs(s, dict, i, memo))
                return memo[startInd] = true;
        }

        return memo[startInd] = false;
    }

    // solution3
    public boolean wordBreak3(String s, List<String> wordDict) {
        // bottom up
        // time O(n^3)
        // space O(n)

        Set<String> dict = new HashSet<>(wordDict);
        boolean[] dp = new boolean[s.length() + 1];
        dp[0] = true;

        for(int i = 1; i <= s.length(); i++) {
            for(int j = 0; j < i; j++) {
                String sub = s.substring(j, i);
                if(dp[j] && dict.contains(sub)) {
                    dp[i] = true;
                    break;
                }
            }
        }

        return dp[s.length()];
    }

    // solution4
    public boolean wordBreak4(String s, List<String> wordDict) {
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

    public static void main(String[] args) {
        WordBreak_139 word = new WordBreak_139();
        word.wordBreak3("leetcode", Arrays.asList("leet","code"));
    }
}
