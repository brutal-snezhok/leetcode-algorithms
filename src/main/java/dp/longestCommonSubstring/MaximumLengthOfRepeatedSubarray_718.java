package dp.longestCommonSubstring;

// https://leetcode.com/problems/maximum-length-of-repeated-subarray/description/
public class MaximumLengthOfRepeatedSubarray_718 {
    public int findLength(int[] nums1, int[] nums2) {
        // bottom up, dp
        // longest common substring
        // time O(n * m)
        // space O(n * m)

        int n = nums1.length;
        int m = nums2.length;

        int[][] dp = new int[n + 1][m + 1];
        int res = 0;
        for(int i = 1; i <= n; i++) {
            for(int j = 1; j <= m; j++) {
                if(nums1[i - 1] == nums2[j - 1]) {
                    dp[i][j] = 1 + dp[i - 1][j - 1];
                    res = Math.max(res, dp[i][j]);
                } else
                    dp[i][j] = 0;
            }
        }

        return res;
    }
}
