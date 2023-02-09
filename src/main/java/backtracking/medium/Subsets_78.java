package backtracking.medium;

import java.util.ArrayList;
import java.util.List;

// https://leetcode.com/problems/subsets/
public class Subsets_78 {
    public static List<List<Integer>> subsets(int[] nums) {
        // time O(2^n*n)
        // space O(n*2^n) - if count res list, and O(n) - if do not count res list

        List<List<Integer>> res = new ArrayList<>();
        backtracking(nums, res, new ArrayList<>(), 0);

        return res;
    }

    // [1,2,3]
    // res = [[]] curr = [1]
    // res = [[], [1]] curr = [1, 2]
    // res = [[], [1], [1, 2]] curr = [1, 2, 3]
    // res = [[], [1], [1, 2], [1, 2, 3]]
    // res = [[], [1], [1, 2], [1, 2, 3]] curr = [1, 3]

    private static void backtracking(int[] nums, List<List<Integer>> res, List<Integer> curr, int start) {
        res.add(new ArrayList<>(curr));
        for(int i = start; i < nums.length; i++) {
            curr.add(nums[i]);

            backtracking(nums, res, curr, i + 1);

            curr.remove(curr.size() - 1);
        }
    }

    public static void main(String[] args) {
        System.out.println(subsets(new int[]{1,2,3}));
    }
}
