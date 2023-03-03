package tree.medium;

import tree.TreeNode;

// https://leetcode.com/problems/lowest-common-ancestor-of-a-binary-search-tree/
public class LowestCommonAncestorOfABinarySearchTree_235 {
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        // time O(logn)
        // space O(1)

        TreeNode curr = root;
        while(curr != null) {
            if(curr.val > p.val && curr.val > q.val)
                curr = curr.left;
            else if(curr.val < p.val && curr.val < q.val)
                curr = curr.right;
            else
                break;
        }

        return curr;
    }
}
