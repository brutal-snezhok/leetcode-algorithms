package backtracking.medium;

import java.util.Arrays;

// https://leetcode.com/problems/partition-to-k-equal-sum-subsets/description/
public class PartitionToKEqualSumSubsets_698 {
    public boolean canPartitionKSubsets(int[] nums, int k) {
        // time O(k*2^n)
        // space O(n)

        // these manipulations are for speed up solution
        //-----------------------------------
        int sum = 0;
        for(int num : nums)
            sum += num;

        if(sum % k != 0)
            return false;
        int target = sum / k;
        Arrays.sort(nums);
        reverseArr(nums);
        //-----------------------------------

        boolean[] visited = new boolean[nums.length];

        return backtracking(0, k, 0, nums, target, visited);
    }

    private boolean backtracking(int start, int k, int subsetSum, int[] nums, int target, boolean[] visited) {
        if(k == 0)
            return true;
        if(subsetSum == target)
            return backtracking(0, k - 1, 0, nums, target, visited);

        for(int j = start; j < nums.length; j++) {
            if(visited[j] || subsetSum + nums[j] > target) continue;

            visited[j] = true;
            if(backtracking(j + 1, k, subsetSum + nums[j], nums, target, visited))
                return true;

            visited[j] = false;
        }

        return false;
    }

    private void reverseArr(int[] nums) {
        int l = 0;
        int r = nums.length - 1;
        while(l <= r) {
            int temp = nums[l];
            nums[l] = nums[r];
            nums[r] = temp;
            l++;
            r--;
        }
    }
}
