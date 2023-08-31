package dp.fibonacci_numbers;

// https://leetcode.com/problems/delete-and-earn/description/
public class DeleteAndEarn_740 {
    // solution1
    public int deleteAndEarn1(int[] nums) {
        // recursion, TLE
        // time O(2^n)
        // space O(n)

        // create freq arr
        // f(i) = max(f(i - 1), f(i - 2) + freq[num] * num)
        int[] freq = new int[10001];
        int maxVal = 0;
        for(int num : nums) {
            maxVal = Math.max(maxVal, num);
            freq[num]++;
        }

        return backtracking1(freq, maxVal);
    }

    private int backtracking1(int[] freq, int num) {
        if(num <= 0)
            return 0;

        int skip = backtracking1(freq, num - 1);
        int take = backtracking1(freq, num - 2) + freq[num] * num;

        return Math.max(skip, take);
    }

    // solution2
    public int deleteAndEarn2(int[] nums) {
        // dp, memoization, top down, TLE
        // time O(n^2)
        // space O(n)

        // create freq arr
        // f(i) = max(f(i - 1), f(i - 2) + freq[num] * num)
        int[] freq = new int[10001];
        int maxVal = 0;
        for(int num : nums) {
            maxVal = Math.max(maxVal, num);
            freq[num]++;
        }

        return backtracking2(freq, maxVal, new int[10001]);
    }

    private int backtracking2(int[] freq, int num, int[] memo) {
        if(num <= 0)
            return 0;
        if(memo[num] > 0)
            return memo[num];

        int skip = backtracking2(freq, num - 1, memo);
        int take = backtracking2(freq, num - 2, memo) + freq[num] * num;
        int res = Math.max(skip, take);
        memo[num] = res;

        return res;
    }

    // solution3
    public int deleteAndEarn3(int[] nums) {
        // dp, tabulation, bottom-up
        // time O(n)
        // space O(n)

        // create freq arr
        // f(i) = max(f(i - 1), f(i - 2) + freq[num] * num)

        int n = 10001;
        int[] freq = new int[n];
        for(int num : nums) {
            freq[num]++;
        }

        int[] dp = new int[n];
        dp[1] = 1 * freq[1];

        for(int i = 2; i < n; i++)
            dp[i] = Math.max(dp[i - 1], dp[i - 2] + freq[i] * i);


        return dp[n - 1];
    }

    // solution4
    public int deleteAndEarn4(int[] nums) {
        // dp, tabulation, bottom-up, optimized
        // time O(n)
        // space O(n)

        // create freq arr
        // f(i) = max(f(i - 1), f(i - 2) + freq[num] * num)

        int n = 10001;
        int[] freq = new int[n];
        for(int num : nums) {
            freq[num]++;
        }

        int prevPrev = 0;
        int prev = 1 * freq[1];

        for(int i = 2; i < n; i++) {
            int temp = Math.max(prev, prevPrev + freq[i] * i);
            prevPrev = prev;
            prev = temp;
        }

        return prev;
    }
}
