package arraysHashing.easy;

import java.util.HashMap;
import java.util.Map;

// https://leetcode.com/problems/longest-harmonious-subsequence/description/
public class LongestHarmoniousSubsequence_594 {
    // solution1
    public int findLHS1(int[] nums) {
        // time O(n)
        // space O(n)

        // count num of each val and save it to map
        // go through vals in map and check
        // if there count for val - 1 and  for val + 1
        // currMax = max(count + countValMins, count + countValPl);
        // res = Math.max(res, currMax)

        int res = 0;
        Map<Integer, Integer> valToCount = new HashMap<>(); // {val, count}
        for(int num : nums)
            valToCount.put(num, valToCount.getOrDefault(num, 0) + 1);

        int currMax = 0;
        for(Map.Entry<Integer, Integer> entry : valToCount.entrySet()) {
            int val = entry.getKey();
            int count = entry.getValue();

            int countValMinusOne = valToCount.getOrDefault(val - 1, 0);
            int countValPlusOne = valToCount.getOrDefault(val + 1, 0);
            int first = countValMinusOne == 0 ? 0 : count + countValMinusOne;
            int second = countValPlusOne == 0 ? 0 : count + countValPlusOne;
            currMax = Math.max(first, second);

            res = Math.max(res, currMax);
        }

        return res;
    }

    // solution2
    public int findLHS2(int[] nums) {
        // time O(n)
        // space O(n)
        // single loop

        int res = 0;
        Map<Integer, Integer> map = new HashMap<>();
        for(int num : nums) {
            map.put(num, map.getOrDefault(num, 0) + 1);

            if(map.containsKey(num - 1))
                res = Math.max(res, map.get(num) + map.get(num - 1));
            if(map.containsKey(num + 1))
                res = Math.max(res, map.get(num) + map.get(num + 1));
        }

        return res;
    }
}
