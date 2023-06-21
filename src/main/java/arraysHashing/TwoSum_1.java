package arraysHashing;

import java.util.HashMap;
import java.util.Map;

public class TwoSum_1 {
    // approach 1: brute force
    public int[] twoSum(int[] nums, int target) {
        // time O(n^2)
        // space O(1)

        int[] res = new int[2];
        for(int i = 0; i < nums.length - 1; i++) {
            for(int j = i + 1; j < nums.length; j++) {
                if(nums[i] + nums[j] == target) {
                    res[0] = i;
                    res[1] = j;
                    return res;
                }
            }
        }

        return res;
    }

    // approach 2
    public int[] twoSum2(int[] nums, int target) {
        // time O(n)
        // space O(n)

        int[] res = new int[2];
        Map<Integer, Integer> map = new HashMap<>(); // {val, index}
        for(int i = 0; i < nums.length; i++) {
            if(map.containsKey(target - nums[i])) {
                res[0] = i;
                res[1] = map.get(target - nums[i]);
            } else
                map.put(nums[i], i);
        }

        return res;
    }
}
