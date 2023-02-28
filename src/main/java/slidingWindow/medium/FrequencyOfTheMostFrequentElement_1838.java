package slidingWindow.medium;

import java.util.Arrays;

// https://leetcode.com/problems/frequency-of-the-most-frequent-element/description/
public class FrequencyOfTheMostFrequentElement_1838 {
    public int maxFrequency(int[] nums, int k) {
        // time O(nlogn)
        // space O(n), because of sorting

        Arrays.sort(nums);
        long sum = 0;
        int res = 0;
        int l = 0;
        for (int r = 0; r < nums.length; r++) {
            sum += nums[r];
            int windowL = r - l + 1;
            if (sum + k >= (long) nums[r] * windowL)
                res = Math.max(res, windowL);
            else
                sum -= nums[l++];
        }

        return res;
    }
}
