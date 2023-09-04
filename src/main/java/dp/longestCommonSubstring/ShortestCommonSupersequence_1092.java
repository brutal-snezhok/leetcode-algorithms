package dp.longestCommonSubstring;

// https://leetcode.com/problems/shortest-common-supersequence/description/
public class ShortestCommonSupersequence_1092 {
    // solution1
    public String shortestCommonSupersequence(String str1, String str2) {
        // time O(n * m)
        // space O(n * m)

        int n = str1.length();
        int m = str2.length();
        int[][] dp = new int[n + 1][m + 1];
        lcs(dp, str1, str2);

        String lcs = restoreLCS(dp, n, m, str1, str2);

        int p1 = 0;
        int p2 = 0;

        StringBuilder res = new StringBuilder();
        for(int i = 0; i < lcs.length(); i++) {
            while(p1 < n && str1.charAt(p1) != lcs.charAt(i)) {
                char curr = str1.charAt(p1);
                res.append(curr); // add to res all chars before/after lcs
                p1++;
            }

            while(p2 < m && str2.charAt(p2) != lcs.charAt(i)) {
                char curr = str2.charAt(p2);
                res.append(curr);
                p2++;
            }

            res.append(lcs.charAt(i));
            p1++;
            p2++;
        }

        // append to res left chars
        if(p1 < n)
            res.append(str1.substring(p1, n));
        if(p2 < m)
            res.append(str2.substring(p2, m));

        return res.toString();
    }

    private void lcs(int[][] dp, String s1, String s2) {
        int n = s1.length();
        int m = s2.length();

        for(int i = 1; i <= n; i++) {
            for(int j = 1; j <= m; j++) {
                if(s1.charAt(i - 1) == s2.charAt(j - 1)) {
                    dp[i][j] = 1 + dp[i - 1][j - 1];
                }
                else
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
            }
        }
    }

    private String restoreLCS(int[][] dp, int n, int m, String a, String b) {
        int ind = dp[n][m]; // length of longest common subsequence
        char[] ans = new char[ind]; // longest common subsequence
        int i = n;
        int j = m;

        while(i > 0 && j > 0) {
            if(a.charAt(i - 1) == b.charAt(j - 1)) {
                ans[ind - 1] = a.charAt(i - 1);
                i--;
                j--;
                ind--;
            }
            else if(dp[i - 1][j] > dp[i][j - 1])
                i--;
            else
                j--;
        }

        return new String(ans);
    }
}
