package dp;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

// https://leetcode.com/problems/extra-characters-in-a-string/description/
public class ExtraCharactersInString_2707 {
    // solution1
    public int minExtraChar1(String s, String[] dictionary) {
        // time O(2^n),TLE
        // space O(n)

        Set<String> dict = new HashSet<>(Arrays.asList(dictionary));

        return dfs(s, dict, 0);
    }

    private int dfs(String s, Set<String> dict, int ind) {
        if(ind == s.length())
            return 0;

        int take = Integer.MAX_VALUE;
        for(int i = ind + 1; i <= s.length(); i++) {
            if(dict.contains(s.substring(ind, i)))
                take = Math.min(take, dfs(s, dict, i));
        }

        int notTake = 1 + dfs(s, dict, ind + 1);

        return Math.min(take, notTake);
    }

    // solution2
    public int minExtraChar2(String s, String[] dictionary) {
        // top down, memo
        // time O(n^2)
        // space O(n)

        Set<String> dict = new HashSet<>(Arrays.asList(dictionary));
        Integer[] memo = new Integer[s.length()];

        return dfs(s, dict, 0, memo);
    }

    private int dfs(String s, Set<String> dict, int ind, Integer[] memo) {
        if(ind == s.length())
            return 0;
        if(memo[ind] != null)
            return memo[ind];

        int take = Integer.MAX_VALUE;
        for(int i = ind + 1; i <= s.length(); i++) {
            if(dict.contains(s.substring(ind, i)))
                take = Math.min(take, dfs(s, dict, i, memo));
        }

        int notTake = 1 + dfs(s, dict, ind + 1, memo);

        return memo[ind] = Math.min(take, notTake);
    }

    // solution3
    public int minExtraChar3(String s, String[] dictionary) {
        // bottom up, dp
        // time O(n^2)
        // space O(n)

        // dp[i] - minimum number of extra character in s[0:i]

        Set<String> dict = new HashSet<>(Arrays.asList(dictionary));
        int n = s.length();
        int[] dp = new int[n + 1];

        for(int i = 1; i <= n; i++) {
            dp[i] = n + 1;
            for(int j = 0; j < i; j++) {
                String sub = s.substring(j, i);
                if(dict.contains(sub))
                    dp[i] = Math.min(dp[i], dp[j]);
                else
                    dp[i] = Math.min(dp[i], dp[j] + i - j);
            }
        }

        return dp[n];
    }

    public static void main(String[] args) {
        ExtraCharactersInString_2707 extra = new ExtraCharactersInString_2707();
        extra.minExtraChar1("sayhelloworld", new String[]{"hello","world"});
    }
}
