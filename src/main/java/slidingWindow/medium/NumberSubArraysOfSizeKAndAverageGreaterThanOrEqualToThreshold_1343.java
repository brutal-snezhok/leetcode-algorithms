package slidingWindow.medium;

// https://leetcode.com/problems/number-of-sub-arrays-of-size-k-and-average-greater-than-or-equal-to-threshold/description/
public class NumberSubArraysOfSizeKAndAverageGreaterThanOrEqualToThreshold_1343 {
    public int numOfSubarrays(int[] arr, int k, int threshold) {
        // sliding window
        // time O(n)
        // space O(1)

        int l = 0;
        int res = 0;
        int currSum = 0;
        for (int r = 0; r < arr.length; r++) {
            currSum += arr[r];
            if (r - l == k) {
                currSum -= arr[l];
                l++;
            }

            if (r >= k - 1 && currSum / k >= threshold)
                res++;
        }

        return res;
    }
}
