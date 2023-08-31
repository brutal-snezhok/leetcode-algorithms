package dp.fibonacci_numbers;

// https://leetcode.com/problems/decode-ways/description/
public class DecodeWays_91 {
    // solution1, recursion, got TLE
    // case: "111111111111111111111111111111111111111111111"
    public int numDecodings1(String s) {
        // time O(2^n)
        // space O(n)

        // we have two choices
        // 1. take one char
        // 2. take two chars, before you should check if two chars in range [10, 26]

        return numDecodings1(0, s);
    }

    // A char may be decoded alone or by pairing with the next char
    private int numDecodings1(int p, String s) {
        int n = s.length();
        if(p == n) return 1;
        if(s.charAt(p) == '0') return 0;

        int res = numDecodings1(p + 1, s); // take one char
        if(p + 1 < n && (s.charAt(p) == '1' || (s.charAt(p) == '2' && s.charAt(p + 1) < '7')))
            res += numDecodings1(p + 2, s); // take two chars

        return res;
    }

    /////////////////////////////////////////////////////////////////////////////

    // solution2, memoization
    public int numDecodings2(String s) {
        // memoization
        // time O(n)
        // space O(n)

        int n = s.length();
        Integer[] mem = new Integer[n];

        return numDecodings2(0, s, mem);
    }

    private int numDecodings2(int p, String s, Integer[] mem) {
        int n = s.length();
        if(p == n) return 1;
        if(s.charAt(p) == '0') return 0;
        if(mem[p] != null) return mem[p];

        int res = numDecodings2(p + 1, s, mem);
        if(p + 1 < n && (s.charAt(p) == '1' || (s.charAt(p) == '2' && s.charAt(p + 1) < '7')))
            res += numDecodings2(p + 2, s, mem);

        mem[p] = res;

        return res;
    }

    /////////////////////////////////////////////////////////////////////////////

    // solution3, dp
    public int numDecodings3(String s) {
        // dp
        // time O(n)
        // space O(n)

        int n = s.length();
        int[] dp = new int[n + 1];
        dp[n] = 1;

        for(int i = n - 1; i >= 0; i--) {
            if(s.charAt(i) == '0')
                dp[i] = 0;
            else if(s.charAt(i) == '1' && i + 1 < n) {
                dp[i] = dp[i + 1] + dp[i + 2];
            } else if(s.charAt(i) == '2') {
                dp[i] = dp[i + 1];
                if(i + 1 < n && (s.charAt(i) == '1' || (s.charAt(i) == '2' && s.charAt(i + 1) < '7')))
                    dp[i] += dp[i + 2];
            } else
                dp[i] = dp[i + 1];
        }

        return dp[0];
    }

    // solution4, dp, shorter version
    public int numDecodings4(String s) {
        // bottom-up, dp
        // time O(n)
        // space O(n)

        int n = s.length();
        int[] dp = new int[n + 1];
        dp[n] = 1;

        for(int i = n - 1; i >= 0; i--) {
            if(s.charAt(i) != '0') {
                dp[i] = dp[i + 1];
                if(i + 1 < n && (s.charAt(i) == '1' || (s.charAt(i) == '2' && s.charAt(i + 1) < '7')))
                    dp[i] += dp[i + 2];
            }
        }

        return dp[0];
    }

    /////////////////////////////////////////////////////////////////////////////

    // solution5
    public int numDecodings5(String s) {
        // bottom-up, optimized space
        // time O(n)
        // space O(1)

        int n = s.length();
        int dp1 = 1;
        int dp2 = 0;

        for(int i = n - 1; i >= 0; i--) {
            int dp = s.charAt(i) == '0' ? 0 : dp1;

            if(i + 1 < n && (s.charAt(i) == '1' || (s.charAt(i) == '2' && s.charAt(i + 1) < '7')))
                dp += dp2;

            dp2 = dp1;
            dp1 = dp;
        }

        return dp1;
    }

    // solution6
    public int numDecodings6(String s) {
        // bottom-up, optimized space
        // time O(n)
        // space O(1)

        int n = s.length();
        int[] dp = new int[n + 1];
        dp[0] = 1; // empty string has 1 way to decode
        dp[1] = s.charAt(0) == '0' ? 0 : 1;

        for(int i = 2; i <= n; i++) {
            int first = Integer.parseInt(s.substring(i - 1, i));
            int second = Integer.parseInt(s.substring(i - 2, i));
            if(first >= 1 && first <= 9)
                dp[i] += dp[i - 1];

            if(second >= 10 && second <= 26)
                dp[i] += dp[i - 2];
        }

        return dp[n];
    }

    public static void main(String[] args) {
        DecodeWays_91 decode = new DecodeWays_91();
        decode.numDecodings3("226");
    }
}
