package backtracking.medium;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

// https://leetcode.com/problems/permutations/description/
public class Permutations_46 {
    // solution1
    public List<List<Integer>> permute1(int[] nums) {
        // time O(n!*n), n - depth of decision tree(length of arr), n! - number of leaves of this tree
        // space O(n)

        List<List<Integer>> res = new ArrayList<>();
        backtracking(nums, res, new ArrayList<>());

        return res;
    }
    // [1,2,3] curr = []
    // curr = [1]
    //      curr = [1, 2]
    //          curr = [1, 2, 3] -> res = [[1, 2, 3]]
    //      curr = [1, 3]
    //          curr = [1, 3, 2] -> res = [[1, 2, 3], [1, 3, 2]]
    // curr = [2]
    //      curr = [2, 1]
    //          curr = [2, 1, 3] -> res = [[1, 2, 3], [1, 3, 2], [2, 1, 3]]
    //      curr = [2, 3]
    //          curr = [2, 3, 1] - > res = [[1, 2, 3], [1, 3, 2], [2, 1, 3], [2, 3, 1]]
    // curr = [3]
    //      curr = [3, 1]
    //          curr = [3, 1 ,2] -> res = [[1, 2, 3], [1, 3, 2], [2, 1, 3], [2, 3, 1], [3, 1, 2]]
    //      curr = [3, 2]
    //          curr = [3, 2, 1] -> res = [[1, 2, 3], [1, 3, 2], [2, 1, 3], [2, 3, 1], [3, 1, 2], [3, 2, 1]]

    private void backtracking(int[] nums, List<List<Integer>> res, List<Integer> curr) {
        // base case
        if(curr.size() == nums.length) {
            res.add(new ArrayList<>(curr));
            return;
        }

        // exploration with the given choices
        for(int i = 0; i < nums.length; i++) {
            if(curr.contains(nums[i])) continue; // skip if num have been already taken, need to go through whole list
            // explore
            curr.add(nums[i]);

            // recurse
            backtracking(nums, res, curr);

            // backtracking
            curr.remove(curr.size() - 1);
        }
    }

    // solution2
    public List<List<Integer>> permute2(int[] nums) {
        // optimized solution with swaps
        // time O(n!*n)
        // space O(n)

        List<List<Integer>> res = new ArrayList<>();
        backtracking2(nums, res, new ArrayList<>(), 0);

        return res;
    }

    private void backtracking2(int[] nums, List<List<Integer>> res, List<Integer> curr, int start) {
        // base case
        if(curr.size() == nums.length) {
            res.add(new ArrayList<>(curr));
            return;
        }

        // exploration with given choices
        for(int i = start; i < nums.length; i++) {
            // explore
            curr.add(nums[i]);
            swap(nums, start, i);

            // recursive
            backtracking2(nums, res, curr, start + 1);

            // backtracking
            curr.remove(curr.size() - 1);
            swap(nums, start, i);
        }
    }

    private void swap(int[] nums, int start, int i) {
        int temp = nums[start];
        nums[start] = nums[i];
        nums[i] = temp;
    }

    // solution3
    public List<List<Integer>> permute3(int[] nums) {
        // solution with set to check in O(1) if element already in list
        // time O(n*n!)
        // space O(n)

        List<List<Integer>> res = new ArrayList<>();
        backtracking(res, new ArrayList<>(), new HashSet<>(), nums);

        return res;
    }

    private void backtracking(List<List<Integer>> res, List<Integer> curr, Set<Integer> set, int[] nums) {
        if(set.size() == nums.length) {
            res.add(new ArrayList<>(curr));
            return;
        }

        for(int num : nums) {
            if(set.contains(num))
                continue;

            set.add(num);
            curr.add(num);
            backtracking(res, curr, set, nums);
            set.remove(num);
            curr.remove(curr.size() - 1);
        }
    }
}
