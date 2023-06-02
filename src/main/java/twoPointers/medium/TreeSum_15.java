package twoPointers.medium;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

// https://leetcode.com/problems/3sum/description/
public class TreeSum_15 {
    public List<List<Integer>> threeSum(int[] nums) {
        // time O(n^2)
        // space O(n), requires for sorting if ignore the output

        List<List<Integer>> res = new ArrayList<>();
        Arrays.sort(nums);
        int n = nums.length;

        for(int k = 0; k < n; k++) {
            if(k != 0 && nums[k] == nums[k - 1])
                continue;

            int target = nums[k] * -1;

            int i = k + 1;
            int j = n - 1;
            while(i < j) {
                int curr = nums[i] + nums[j];
                if(curr == target) {
                    List<Integer> list = Arrays.asList(nums[k], nums[i], nums[j]);
                    res.add(list);
                    i++;
                    j--;

                    while(i < j && nums[i] == nums[i - 1])
                        i++;
                } else if(curr > target) {
                    j--;
                } else
                    i++;
            }
        }

        return res;
    }
}
