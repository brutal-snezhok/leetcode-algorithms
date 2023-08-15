package backtracking.medium;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

// https://leetcode.com/problems/combination-sum/description/
public class CombinationSum_39 {
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        // picture: https://leetcode.com/problems/combination-sum/solutions/1777569/full-explanation-with-state-space-tree-recursion-and-backtracking-well-explained-c/
        // time complexity: https://leetcode.com/problems/combination-sum/solutions/1755084/Detailed-Time-and-Space-Complecity-analysisc++javabacktracking/

        // time O(2^k), k is the sum of target/candidate[i] from i = 0 to size of candidate - 1
        // space O(length_of_longest_combination)

        List<List<Integer>> res = new ArrayList<>();
        Arrays.sort(candidates);
        backtracking(candidates, target, res, new ArrayList<>(), 0, 0);

        return res;
    }

    private void backtracking(int[] candidates, int target, List<List<Integer>> res, List<Integer> curr, int currSum, int start) {
        if(currSum == target) {
            res.add(new ArrayList<>(curr));
            return;
        }
        if(currSum > target) return;

        for(int i = start; i < candidates.length; i++) {
            curr.add(candidates[i]);
            currSum += candidates[i];

            backtracking(candidates, target, res, curr, currSum, i); // start stays as i because we can use the same num

            curr.remove(curr.size() - 1);
            currSum -= candidates[i];

            if(currSum > target) // just an optimization. Because of sorting there is no sense to check next element, they all will be greater
                return;
        }
    }

    public static void main(String[] args) {
        CombinationSum_39 combination = new CombinationSum_39();
        combination.combinationSum(new int[]{6,3,2,7}, 7);
    }
}
