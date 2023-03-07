package tree.medium;

import tree.TreeNode;

// https://leetcode.com/problems/insert-into-a-binary-search-tree/description/
public class InsertIntoABinarySearchTree_701 {
    public TreeNode insertIntoBST(TreeNode root, int val) {
        // dfs recursive
        // time O(logn) - for balanced tree and O(n) - for unbalanced
        // space O(logn) - for balanced tree and O(n) - for unbalanced

        if(root == null)
            return new TreeNode(val);

        if(root.val > val)
            root.left = insertIntoBST(root.left, val);
        else
            root.right = insertIntoBST(root.right, val);

        return root;
    }


    public TreeNode insertIntoBST2(TreeNode root, int val) {
        // iterative
        // time O(logn) - for balanced tree and O(n) - for unbalanced
        // space O(1)

        if(root == null)
            return new TreeNode(val);

        TreeNode curr = root;
        while(curr != null) {
            if(curr.val > val) {
                if(curr.left == null) {
                    curr.left = new TreeNode(val);
                    break;
                } else
                    curr = curr.left;
            } else {
                if(curr.right == null) {
                    curr.right = new TreeNode(val);
                    break;
                } else
                    curr = curr.right;
            }
        }

        return root;
    }
}
