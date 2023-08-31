package dp;

import java.util.Arrays;

// https://leetcode.com/problems/maximum-subarray/
public class MaximumSubarray_53 {
    // solution1
    public int maxSubArray1(int[] nums) {
        // time O(n^2), TLE, There are N*(N+1)/2 total sub-arrays and trying out each one takes time of O(N^2)
        // space O(1)

        int n = nums.length;
        int sum = Integer.MIN_VALUE;
        int currSum;

        for(int i = 0; i < n; i++) {
            currSum = 0;
            for(int j = i; j < n; j++) {
                currSum += nums[j];
                sum = Math.max(sum, currSum);
            }
        }

        return sum;
    }

    // solution2
    public int maxSubArray2(int[] nums) {
        // (Recursive) [TLE]
        // time O(n^2), we are basically considering every subarray sum and choosing maximum of it.
        // space O(n), recursive space

        return solve2(nums, 0, false);
    }

    private int solve2(int[] nums, int i, boolean mustPick) {
        // our subarray must contain at least 1 element. If mustPick is false at the end means no element is picked and this is not valid case
        if(i >= nums.length) return mustPick ? 0 : Integer.MIN_VALUE;
        if(mustPick)
            return Math.max(0, nums[i] + solve2(nums, i + 1, true)); // either stop here or choose current element and recurse
        return Math.max(solve2(nums, i + 1, false), nums[i] + solve2(nums, i + 1, true)); // try both choosing current element or not choosing
    }


    public int maxSubArray3(int[] nums) {
        // solution - III (Dynamic Programming - Memoization)
        // time O(n), we are calculating each state of the dp just once and memoizing the result.
        // space O(n), recursive space

        int[][] dp = new int[2][nums.length];
        Arrays.fill(dp[0], -1);
        Arrays.fill(dp[1], -1);

        return solve3(nums, 0, 0, dp);
    }

    private int solve3(int[] nums, int i, int mustPick, int[][] dp) {
        if(i >= nums.length) return mustPick == 1 ? 0 : Integer.MIN_VALUE;
        if(dp[mustPick][i] != -1) return dp[mustPick][i];

        if(mustPick == 1)
            return dp[mustPick][i] = Math.max(0, nums[i] + solve3(nums, i + 1, 1, dp));
        return dp[mustPick][i] = Math.max(solve3(nums, i + 1, 0, dp), nums[i] + solve3(nums, i + 1, 1, dp));
    }

    public int maxSubArray4(int[] nums) {
        // Solution - IV (Dynamic Programming - Tabulation)
        // time O(n), we are calculating each state of the dp just once and memoizing the result.
        // space O(n), recursive space

        int[][] dp = new int[2][nums.length];
        dp[0][0] = nums[0];
        dp[1][0] = nums[0];

        for(int i = 1; i < nums.length; i++) {
            dp[1][i] = Math.max(nums[i], nums[i] + dp[1][i - 1]);
            dp[0][i] = Math.max(dp[0][i - 1], dp[1][i]);
        }

        return dp[0][nums.length - 1];
    }

    public int maxSubArray5(int[] nums) {
        // Solution - V (Kadane's Algorithm)
        // time O(n)
        // space O(1)

        int max = Integer.MIN_VALUE;
        int currMax = 0;

        for(int num : nums) {
            currMax = Math.max(num, currMax + num);
            max = Math.max(max, currMax);
        }

        return max;
    }

    public int maxSubArray6(int[] nums) {
        // Solution - VI (Divide & Conquer)
        // time O(nlogn)
        // space O(logn)

        return maxSubArr6(nums, 0, nums.length - 1);
    }

    private int maxSubArr6(int[] nums, int l, int r) {
        if(l > r)
            return Integer.MIN_VALUE;

        int mid = (l + r) / 2;
        int leftSum = 0;
        int rightSum = 0;

        // leftSum = max subarray sum in [L, mid-1] and starting from mid-1
        for(int i = mid - 1, currSum = 0; i >= l; i--) {
            currSum += nums[i];
            leftSum = Math.max(leftSum, currSum);
        }

        // rightSum = max subarray sum in [mid+1, R] and starting from mid+1
        for(int i = mid + 1, currSum = 0; i <= r; i++) {
            currSum += nums[i];
            rightSum = Math.max(rightSum, currSum);
        }

        return Math.max(maxSubArr6(nums, l, mid - 1),
                Math.max(maxSubArr6(nums, mid + 1, r), leftSum + nums[mid] + rightSum));
    }

    public int maxSubArray7(int[] nums) {
        // Solution - VII (Optimized Divide & Conquer)
        // time O(n)
        // space O(n)

        int n = nums.length;
        int[] pre = Arrays.copyOf(nums, n);
        int[] suf = Arrays.copyOf(nums, n);
        for(int i = 1; i < n; i++)
            pre[i] += Math.max(0, pre[i - 1]);

        for(int i = n - 2; i >= 0; i--)
            suf[i] += Math.max(0, suf[i + 1]);

        return maxSubArr7(nums, 0, n - 1, pre, suf);
    }

    private int maxSubArr7(int[] nums, int l, int r, int[] pre, int[] suf) {
        if(l == r)
            return nums[l];

        int mid = (l + r) / 2;

        return Math.max(maxSubArr7(nums, l, mid, pre, suf),
                Math.max(maxSubArr7(nums, mid + 1, r, pre, suf), pre[mid] + suf[mid + 1]));
    }
}
