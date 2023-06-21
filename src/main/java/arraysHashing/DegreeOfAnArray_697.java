package arraysHashing;

import java.util.HashMap;
import java.util.Map;

// https://leetcode.com/problems/degree-of-an-array/description/
public class DegreeOfAnArray_697 {
    public int findShortestSubArray(int[] nums) {
        // time O(n)
        // space O(n)

        Map<Integer, int[]> map = new HashMap<>(); // {val, [startInd, endInd, count]}
        for(int i = 0; i < nums.length; i++) {
            if(map.containsKey(nums[i])) {
                int[] temp = map.get(nums[i]);
                temp[1] = i;
                temp[2] += 1;
                map.put(nums[i], temp);
            } else
                map.put(nums[i], new int[]{i, i, 1});
        }

        int maxDegree = 0;
        for(int[] arr : map.values())
            maxDegree = Math.max(maxDegree, arr[2]);

        int res = Integer.MAX_VALUE;
        for(int[] arr : map.values()) {
            if(arr[2] == maxDegree)
                res = Math.min(res, arr[1] - arr[0] + 1);
        }

        return res;
    }
}
