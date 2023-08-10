package tree.medium;

import tree.TreeNode;

// https://leetcode.com/problems/validate-binary-search-tree/description/
public class ValidateBinarySearchTree_98 {
    public boolean isValidBST(TreeNode root) {
        // time O(n)
        // space O(n)

        return isValid(root, Long.MIN_VALUE, Long.MAX_VALUE); // need to use long because node.val can be 2^31-1
    }

    private boolean isValid(TreeNode node, Long min, Long max) {
        if(node == null)
            return true;

        if(node.val <= min || node.val >= max)
            return false;

        boolean l = isValid(node.left, min, (long)node.val);
        boolean r = isValid(node.right, (long)node.val, max);

        return l && r;
    }
}
