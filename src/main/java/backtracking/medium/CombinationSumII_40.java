package backtracking.medium;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

// https://leetcode.com/problems/combination-sum-ii/description/
public class CombinationSumII_40 {
    public static List<List<Integer>> combinationSum2(int[] candidates, int target) {
        // time O(2^n * height)
        // O(height), didn't count res list

        List<List<Integer>> res = new ArrayList<>();
        Arrays.sort(candidates);
        backtracking(candidates, target, res, new ArrayList<>(), 0, 0);

        return res;
    }

    private static void backtracking(int[] candidates, int target, List<List<Integer>> res, List<Integer> curr, int sum, int start) {
        if(sum == target) {
            res.add(new ArrayList<>(curr));
            return;
        }
        if(sum > target) return;

        for(int i = start; i < candidates.length; i++) {
            if(i > start && candidates[i] == candidates[i - 1]) continue; // avoid duplicates

            curr.add(candidates[i]);
            sum += candidates[i];

            backtracking(candidates, target, res, curr, sum, i + 1);

            curr.remove(curr.size() - 1);
            sum -= candidates[i];

            if(sum > target)
                return;
        }
    }

    public static void main(String[] args) {
        System.out.println(combinationSum2(new int[]{10,1,2,7,6,1,5}, 8));
    }
}
