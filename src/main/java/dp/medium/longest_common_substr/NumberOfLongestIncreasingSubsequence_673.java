package dp.medium.longest_common_substr;

import java.util.Arrays;

// https://leetcode.com/problems/number-of-longest-increasing-subsequence/description/
public class NumberOfLongestIncreasingSubsequence_673 {
    public int findNumberOfLIS(int[] nums) {
        // bottom up, dp
        // time O(n^2)
        // space O(n)

        int n = nums.length;
        int[] lenLIS = new int[n];
        int[] count = new int[n];

        Arrays.fill(lenLIS, 1);
        Arrays.fill(count, 1);

        for(int i = n - 1; i >= 0; i--) {
            for(int j = i + 1; j < n; j++) {
                if(nums[j] > nums[i]) {
                    if(lenLIS[j] + 1 > lenLIS[i]) {
                        lenLIS[i] = lenLIS[j] + 1;
                        count[i] = count[j];
                    } else if(lenLIS[j] + 1 == lenLIS[i])
                        count[i] += count[j];
                }
            }
        }

        int maxLength = 0;
        int result = 0;

        for (int len : lenLIS)
            maxLength = Math.max(maxLength, len);

        for (int i = 0; i < n; i++)
            if (lenLIS[i] == maxLength)
                result += count[i];

        return result;
    }
}
