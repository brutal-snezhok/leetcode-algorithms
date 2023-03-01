package tree.easy;

import tree.TreeNode;

// https://leetcode.com/problems/subtree-of-another-tree/
public class SubtreeOfAnotherTree_572 {
    // TODO: take a look at Knuth-Morris-Pratt algorithm for this task
    public boolean isSubtree(TreeNode root, TreeNode subRoot) {
        // solution1: recursive
        // time O(m * n)
        // space O(m + n)

        if(root == null)
            return false;

        if(isSameTree(root, subRoot))
            return true;

        return isSubtree(root.left, subRoot) || isSubtree(root.right, subRoot);
    }

    public boolean isSameTree(TreeNode p, TreeNode q) {
        // time O(min(n1, n2))
        // space O(min(h1, h2))

        if(p == null && q == null)
            return true;
        if(p == null && q != null || p != null && q == null || p.val != q.val)
            return false;

        return isSameTree(p.left, q.left) && isSameTree(p.right, q.right);
    }
}
