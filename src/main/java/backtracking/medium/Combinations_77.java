package backtracking.medium;

import java.util.ArrayList;
import java.util.List;

// https://leetcode.com/problems/combinations/description/
public class Combinations_77 {
    public List<List<Integer>> combine(int n, int k) {
        // time O(k*Cnk), Cnk = n!/((n -k )! * k!)
        // space O(k), if do not count result array, k - height of decision tree

        List<List<Integer>> res = new ArrayList<>();
        backtracking(n, k, res, new ArrayList<>(), 1);

        return res;
    }

    private void backtracking(int n, int k, List<List<Integer>> res, List<Integer> curr, int start) {
        if(curr.size() == k) {
            res.add(new ArrayList<>(curr));
            return;
        }

        for(int i = start; i <= n; i++) {
            curr.add(i);
            backtracking(n, k, res, curr, i + 1);
            curr.remove(curr.size() - 1);
        }
    }
}
