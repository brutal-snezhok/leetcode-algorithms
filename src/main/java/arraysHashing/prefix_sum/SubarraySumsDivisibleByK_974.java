package arraysHashing.prefix_sum;

import java.util.HashMap;
import java.util.Map;

// https://leetcode.com/problems/subarray-sums-divisible-by-k/description/
public class SubarraySumsDivisibleByK_974 {
    public int subarraysDivByK(int[] nums, int k) {
        // time O(n)
        // space O(n)

        /*   0 1 2  3  4 5
            [4,5,0,-2,-3,1], k = 5

            map: {0, 1}, {4, 1}, {}
            currSum: 0    4

            [4,9,9, 7, 4,5] - prefixSum
        */

        Map<Integer, Integer> freqMap = new HashMap<>(Map.of(0, 1)); // {prefixSum % k, freq} = {reminder, freq}
        int currSum = 0;
        int res = 0;
        for(int num : nums) {
            currSum += num;
            int reminder = currSum % k;

            if(reminder < 0)
                reminder += k; // to handle negative values

            if(freqMap.containsKey(reminder))
                res += freqMap.get(reminder);

            freqMap.put(reminder, freqMap.getOrDefault(reminder, 0) + 1);
        }

        return res;
    }
}
