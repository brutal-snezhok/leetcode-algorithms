package backtracking.medium;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

// https://leetcode.com/problems/permutations-ii/description/
public class PermutationsII_47 {
    public List<List<Integer>> permuteUnique(int[] nums) {
        // https://leetcode.com/problems/permutations-ii/solutions/18594/really-easy-java-solution-much-easier-than-the-solutions-with-very-high-vote/?orderBy=most_votes
        // time O(n! * n)
        // space O(n), didn't count res list

        List<List<Integer>> res = new ArrayList<>();
        Arrays.sort(nums);
        backtracking(nums, res, new ArrayList<>(), new boolean[nums.length]);

        return res;
    }

    private void backtracking(int[] nums, List<List<Integer>> res, List<Integer> curr, boolean[] used) {
        if(nums.length == curr.size()) {
            res.add(new ArrayList<>(curr));
            return;
        }

        for(int i = 0; i < nums.length; i++) {
            if(used[i]) continue;
            if(i > 0 && nums[i - 1] == nums[i] && !used[i - 1]) continue; // when a number has the same value with its previous, we can use this number only if his previous is used

            used[i] = true;
            curr.add(nums[i]);

            backtracking(nums, res, curr, used);

            curr.remove(curr.size() - 1);
            used[i] = false;
        }
    }
}
