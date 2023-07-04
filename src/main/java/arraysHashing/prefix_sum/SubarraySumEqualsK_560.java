package arraysHashing.prefix_sum;

import java.util.HashMap;
import java.util.Map;

// https://leetcode.com/problems/subarray-sum-equals-k/description/
public class SubarraySumEqualsK_560 {
    public int subarraySum(int[] nums, int k) {
        // time O(n)
        // space O(n)
        /*
            [1,2,3], k = 3

             0 1 2
            [1,3,6]
            {0, 1}
         */

        Map<Integer, Integer> map = new HashMap<>(Map.of(0, 1)); // {val, freq}

        int sum = 0;
        int res = 0;

        for(int num : nums) {
            sum += num;

            if(map.containsKey(sum - k))
                res += map.get(sum - k); // 1

            map.put(sum, map.getOrDefault(sum, 0) + 1);
        }

        return res;
    }
}
