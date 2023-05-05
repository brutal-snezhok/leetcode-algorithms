package tree.medium;

import tree.TreeNode;

import java.util.ArrayList;
import java.util.List;

// https://leetcode.com/problems/path-sum-ii/
public class PathSumII_113 {
    // solution1
    public List<List<Integer>> pathSum(TreeNode root, int targetSum) {
        // time O(n^2): O(n) - go to the leaf, O(n) - copy path to res
        // space O(n^2)

        List<List<Integer>> res = new ArrayList<>();
        dfs(root, targetSum, res, new ArrayList<>());

        return res;
    }

    private void dfs(TreeNode root, int targetSum, List<List<Integer>> res, List<Integer> curr) {
        if(root == null)
            return;

        curr.add(root.val);

        if(root.val == targetSum && root.left == null && root.right == null) {
            res.add(new ArrayList<>(curr));
            curr.remove(curr.size() - 1);
            return;
        }

        dfs(root.left, targetSum - root.val, res, curr);
        dfs(root.right, targetSum - root.val, res, curr);
        curr.remove(curr.size() - 1);
    }
}
