package dp.longestCommonSubstring;

import java.util.Arrays;

// https://leetcode.com/problems/number-of-longest-increasing-subsequence/description/
public class NumberOfLongestIncreasingSubsequence_673 {
    public int findNumberOfLIS1(int[] nums) {
        // bottom up, dp
        // time O(n^2)
        // space O(n)

        int n = nums.length;
        int[] lenLIS = new int[n];
        int[] count = new int[n];

        Arrays.fill(lenLIS, 1);
        Arrays.fill(count, 1);

        for (int i = n - 1; i >= 0; i--) {
            for (int j = i + 1; j < n; j++) {
                if (nums[j] > nums[i]) {
                    if (lenLIS[j] + 1 > lenLIS[i]) {
                        lenLIS[i] = lenLIS[j] + 1;
                        count[i] = count[j];
                    } else if (lenLIS[j] + 1 == lenLIS[i])
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

    public int findNumberOfLIS2(int[] nums) {
        // time O(n^2)
        // space O(n)

        int[] lis = new int[nums.length];
        int[] numLIS = new int[nums.length];

        for (int i = 0; i < nums.length; i++) {
            lis[i] = 1;
            numLIS[i] = 1;

            for (int k = 0; k < i; k++) {
                if (nums[i] > nums[k]) {
                    if (lis[k] + 1 > lis[i]) {
                        lis[i] = lis[k] + 1;
                        numLIS[i] = 0;
                    }

                    if (lis[i] - 1 == lis[k])
                        numLIS[i] += numLIS[k];
                }
            }
        }

        int res = 0;
        int max = Integer.MIN_VALUE;

        for (int num : lis)
            if (num > max)
                max = num;

        for (int i = 0; i < numLIS.length; i++)
            if (lis[i] == max)
                res += numLIS[i];

        return res;
    }

    public static void main(String[] args) {
        NumberOfLongestIncreasingSubsequence_673 number = new NumberOfLongestIncreasingSubsequence_673();
        number.findNumberOfLIS2(new int[]{1, 3, 5, 4, 7});
    }
}
