package tree.easy;

import tree.TreeNode;

// https://leetcode.com/problems/merge-two-binary-trees/description/
public class MergeTwoBinaryTrees_617 {
    public TreeNode mergeTrees(TreeNode r1, TreeNode r2) {
        // recursive
        // time O(n + m)
        // space O(max(h1, h2))

        if (r1 == null && r2 == null)
            return null;

        int val = 0;
        val += r1 == null ? 0 : r1.val;
        val += r2 == null ? 0 : r2.val;

        TreeNode node = new TreeNode(val);
        node.left = mergeTrees(r1 == null ? null : r1.left, r2 == null ? null : r2.left);
        node.right = mergeTrees(r1 == null ? null : r1.right, r2 == null ? null : r2.right);

        return node;
    }
}
