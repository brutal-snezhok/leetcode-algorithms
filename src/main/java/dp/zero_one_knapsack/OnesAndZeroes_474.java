package dp.zero_one_knapsack;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

// https://leetcode.com/problems/ones-and-zeroes/description/
public class OnesAndZeroes_474 {
    // solution1, TLE
    public int findMaxForm(String[] strs, int m, int n) {
        // time O(2^n), - number of words
        // space O(n * avgWord)

        // strs = ["10","0001","111001","1","0"], m = 5, n = 3

        // can take element from string
        // can not take
        // if take count how many zeros and one's are in

        Map<String, Pair> map = new HashMap<>(); // {str, count of zeros and count of ones}
        for(String s : strs) {
            int z = 0;
            int o = 0;
            for(char ch : s.toCharArray()) {
                if(ch == '0')
                    z++;
                else
                    o++;
            }

            map.put(s, new Pair(z, o));
        }

        return dfs(strs, m, n, map, 0);
    }

    private int dfs(String[] strs, int m, int n, Map<String, Pair> map, int i) {
        if(i == strs.length)
            return 0;

        Pair p = map.get(strs[i]);
        int take = 0;
        if(m - p.m >= 0 && n - p.n >= 0)
            take = 1 + dfs(strs, m - p.m, n - p.n, map, i + 1);
        int skip = dfs(strs, m, n, map, i + 1);

        return Math.max(take, skip);
    }

    // solution2
    int[][][] memo;
    public int findMaxForm2(String[] strs, int m, int n) {
        // dp, memo
        // time O(n*m*strs.length), - number of words
        // space O(n*m*strs.length)

        // strs = ["10","0001","111001","1","0"], m = 5, n = 3

        // can take element from string
        // can not take
        // if take count how many zeros and one's are in

        Map<String, Pair> map = new HashMap<>(); // {str, count of zeros and count of ones}
        for(String s : strs) {
            int z = 0;
            int o = 0;
            for(char ch : s.toCharArray()) {
                if(ch == '0')
                    z++;
                else
                    o++;
            }

            map.put(s, new Pair(z, o));
        }

        memo = new int[m + 1][n + 1][strs.length];
        return dfs2(strs, m, n, map, 0);
    }

    private int dfs2(String[] strs, int m, int n, Map<String, Pair> map, int i) {
        if(i == strs.length)
            return 0;
        if(memo[m][n][i] > 0)
            return memo[m][n][i];

        Pair p = map.get(strs[i]);
        int take = 0;
        if(m - p.m >= 0 && n - p.n >= 0)
            take = 1 + dfs2(strs, m - p.m, n - p.n, map, i + 1);
        int skip = dfs2(strs, m, n, map, i + 1);

        memo[m][n][i] = Math.max(take, skip);
        return memo[m][n][i];
    }

    // solution3
    public int findMaxForm3(String[] strs, int m, int n) {
        // dp, tabulation
        // time O(n*m*strs.length), - number of words
        // space O(n*m*strs.length)

        // strs = ["10","0001","111001","1","0"], m = 5, n = 3

        // can take element from string
        // can not take
        // if take count how many zeros and one's are in

        Map<String, Pair> map = new HashMap<>(); // {str, count of zeros and count of ones}
        for(String s : strs) {
            int z = 0;
            int o = 0;
            for(char ch : s.toCharArray()) {
                if(ch == '0')
                    z++;
                else
                    o++;
            }

            map.put(s, new Pair(z, o));
        }

        int[][] dp = new int[m + 1][n + 1];
        for(String str : strs) {
            Pair p = map.get(str);

            for(int zero = m; zero >= p.m; zero--) {
                for(int one = n; one >= p.n; one--) {
                    dp[zero][one] = Math.max(1 + dp[zero - p.m][one - p.n], dp[zero][one]);
                }
            }
        }

        return dp[m][n];
    }

    class Pair {
        int m;
        int n;

        public Pair(int m, int n) {
            this.m = m;
            this.n = n;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Pair pair = (Pair) o;
            return m == pair.m && n == pair.n;
        }

        @Override
        public int hashCode() {
            return Objects.hash(m, n);
        }
    }

    public static void main(String[] args) {
        OnesAndZeroes_474 onesAndZeroes_474 = new OnesAndZeroes_474();
        onesAndZeroes_474.findMaxForm(new String[]{"10","0001","111001","1","0"}, 4, 3);
    }
}
