package dp.medium;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

// https://leetcode.com/problems/house-robber/description/
public class HouseRobber_198 {
    // solution1
    public int rob1(int[] nums) {
        // recursive, TLE
        // time O(2^n)
        // space O(n)

        return rob1(nums, nums.length - 1);
    }

    private int rob1(int[] nums, int ind) {
        if(ind < 0)
            return 0;

        int robCurr = nums[ind] + rob1(nums, ind - 2);
        int doNotRobCurr = rob1(nums, ind - 1);

        return Math.max(robCurr, doNotRobCurr);
    }

    // solution2
    Map<Integer, Integer> map = new HashMap<>();
    public int rob2(int[] nums) {
        // dp top down with memo
        // time O(n)
        // space O(n)

        return rob2(nums, nums.length - 1);
    }

    private int rob2(int[] nums, int ind) {
        if(ind < 0)
            return 0;
        if(map.containsKey(ind))
            return map.get(ind);

        int robCurr = nums[ind] + rob2(nums, ind - 2);
        int doNotRobCurr = rob2(nums, ind - 1);
        int res = Math.max(robCurr, doNotRobCurr);

        map.put(ind, res);

        return res;
    }

    // solution3
    public int rob3(int[] nums) {
        // dp top down with memo
        // time O(n)
        // space O(n)
        int n = nums.length;
        int[] memo = new int[n + 1];
        Arrays.fill(memo, -1);

        return rob3(nums, n - 1, memo);
    }

    private int rob3(int[] nums, int ind, int[] memo) {
        if(ind < 0)
            return 0;
        if(memo[ind] >= 0)
            return memo[ind];

        int robCurr = nums[ind] + rob3(nums, ind - 2, memo);
        int doNotRobCurr = rob3(nums, ind - 1, memo);
        int res = Math.max(robCurr, doNotRobCurr);

        memo[ind] = res;

        return res;
    }

    // solution4
    public int rob4(int[] nums) {
        // dp bottom up, iterative
        // time O(n)
        // space O(n)

        int n = nums.length;
        int[] dp = new int[n + 1];
        dp[0] = 0;
        dp[1] = nums[0];
        for(int i = 1; i < n; i++) {
            int val = nums[i];
            dp[i + 1] = Math.max(val + dp[i - 1], dp[i]);
        }

        return dp[n];
    }

    // solution5
    public int rob5(int[] nums) {
        // dp bottom up with 2 variables
        // time O(n)
        // space O(1)

        int n = nums.length;
        int prev = nums[0];
        int prevPrev = 0;

        for(int i = 1; i < n; i++) {
            int temp = Math.max(nums[i] + prevPrev, prev);
            prevPrev = prev;
            prev = temp;
        }

        return prev;
    }
}
