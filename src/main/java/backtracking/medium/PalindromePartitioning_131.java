package backtracking.medium;

import java.util.ArrayList;
import java.util.List;

// https://leetcode.com/problems/palindrome-partitioning/description/
public class PalindromePartitioning_131 {
    // solution1
    public List<List<String>> partition1(String s) {
        // time O(n * 2^n)
        // space O(n), didn't count res list

        List<List<String>> res = new ArrayList<>();
        backtracking(s, res, new ArrayList<>(), 0);

        return res;
    }

    private void backtracking(String s, List<List<String>> res, List<String> curr, int start) {
        if(start == s.length()) {
            res.add(new ArrayList<>(curr));
            return;
        }

        for(int end = start; end < s.length(); end++) {
            String currS = s.substring(start, end + 1);
            if(isPalindrome(currS)) {
                curr.add(currS);
                backtracking(s, res, curr, end + 1);
                curr.remove(curr.size() - 1);
            }
        }
    }

    private boolean isPalindrome(String s) {
        int l = 0;
        int r = s.length() - 1;
        while(l <= r) {
            if(s.charAt(l++) != s.charAt(r--))
                return false;
        }

        return true;
    }

    // solution2
    public List<List<String>> partition2(String s) {
        // backtracking + dp
        // time O(n*2^n)
        // space O(n), not count res list

        List<List<String>> res = new ArrayList<>();
        List<String> curr = new ArrayList<>();
        boolean[][] dp = new boolean[s.length()][s.length()];
        dfs(res, curr, s, 0, dp);

        return res;
    }

    private void dfs(List<List<String>> res, List<String> curr, String s, int start, boolean[][] dp) {
        if(start == s.length()) {
            res.add(new ArrayList<>(curr));
            return;
        }

        for(int end = start; end < s.length(); end++) {
            String substr = s.substring(start, end + 1);
            if(s.charAt(start) == s.charAt(end) && (end - start <= 2 || dp[start + 1][end - 1])) {
                dp[start][end] = true;
                curr.add(substr);
                dfs(res, curr, s, end + 1, dp);
                curr.remove(curr.size() - 1);
            }
        }
    }
}
