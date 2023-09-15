package twoPointers;

import java.util.*;

// https://leetcode.com/problems/4sum/description/
public class FourSum_18 {
    // solution1
    public List<List<Integer>> fourSum1(int[] nums, int target) {
        // time O(n^3)
        // space O(n)

        Set<List<Integer>> res = new HashSet<>();
        Arrays.sort(nums);
        for(int i = 0; i < nums.length - 3; i++) {
            for(int j = i + 1; j < nums.length - 2; j++) {

                int l = j + 1;
                int r = nums.length - 1;
                while(l < r) {
                    long curr = (long)nums[i] + nums[j] + nums[l] + nums[r];
                    if(curr == target) {
                        res.add(Arrays.asList(nums[i], nums[j], nums[l], nums[r]));
                        l++;
                    } else if(curr > target) {
                        r--;
                    } else
                        l++;
                }
            }
        }

        return new ArrayList<>(res);
    }

    // solution2
    // general approach for sum questions
    public List<List<Integer>> fourSum2(int[] nums, int target) {
        // time O(n^3)
        // space O(k), k - length of recursion

        List<List<Integer>> res = new ArrayList<>();
        Arrays.sort(nums);
        fourSum(res, nums, new ArrayList<>(), target, 0, 4);
        return res;
    }

    private void fourSum(List<List<Integer>> res, int[] nums, List<Integer> quad, long target, int start, int k) {
        if(k != 2) {
            for(int i = start; i < nums.length - k + 1; i++) {
                if(i > start && nums[i - 1] == nums[i])
                    continue;

                quad.add(nums[i]);
                fourSum(res, nums, quad, target - nums[i], i + 1, k - 1); // here target can be out of bound in int, so need to use long
                quad.remove(quad.size() - 1);
            }
            return;
        }

        int l = start;
        int r = nums.length - 1;
        while(l < r) {
            int sum = nums[l] + nums[r];
            if(sum == target) {
                List<Integer> list = new ArrayList<>();
                list.addAll(quad);
                list.add(nums[l]);
                list.add(nums[r]);
                res.add(list);
                l++;
                while(l < r && nums[l - 1] == nums[l])
                    l++;
            } else if(sum > target)
                r--;
            else
                l++;
        }
    }
}
