package tree.easy;

import tree.TreeNode;

// https://leetcode.com/problems/balanced-binary-tree/description/
public class BalancedBinaryTree_110 {
    public boolean isBalanced(TreeNode root) {
        // time O(n)
        // space O(h)

        if (root == null)
            return true;

        Pair res = dfs(root);
        return res.isBal;
    }

    private Pair dfs(TreeNode node) {
        if (node == null)
            return new Pair(true, 0);

        Pair l = dfs(node.left);
        Pair r = dfs(node.right);

        boolean isBal = l.isBal && r.isBal && Math.abs(l.level - r.level) <= 1;

        return new Pair(isBal, Math.max(l.level, r.level) + 1);
    }

    class Pair {
        boolean isBal;
        int level;

        public Pair(boolean isBal, int level) {
            this.isBal = isBal;
            this.level = level;
        }
    }
}
