package slidingWindow.medium;

import java.util.HashMap;
import java.util.Map;

// https://leetcode.com/problems/maximum-sum-of-distinct-subarrays-with-length-k/
public class MaximumSumOfDistinctSubarraysWithLengthK_2461 {
    // solution1
    public long maximumSubarraySum(int[] nums, int k) {
        // sliding window + hashMap
        // time O(n)
        // space O(k)

        long res = Long.MIN_VALUE;
        Map<Integer, Integer> map = new HashMap<>(); // {val, count}
        long sum = 0L;

        for(int i = 0; i < nums.length; i++) {
            sum += nums[i];
            map.put(nums[i], map.getOrDefault(nums[i], 0) + 1);

            if(i >= k - 1) {
                if(map.size() == k)
                    res = Math.max(res, sum);

                sum -= nums[i - k + 1];
                int count = map.get(nums[i - k + 1]) - 1;
                if(count > 0)
                    map.put(nums[i - k + 1], count);
                else
                    map.remove(nums[i - k + 1]);
            }
        }

        return res == Long.MIN_VALUE ? 0 : res;
    }
}
