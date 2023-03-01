package tree.medium;

import tree.TreeNode;

// https://leetcode.com/problems/trim-a-binary-search-tree/description/
public class TrimABinarySearchTree_669 {
    // solution1
    public TreeNode trimBST(TreeNode root, int low, int high) {
        // time O(n)
        // space O(h)

        if (root == null)
            return root;

        TreeNode l = trimBST(root.left, low, high);
        TreeNode r = trimBST(root.right, low, high);

        if (root.val > high)
            return l;
        if (root.val < low)
            return r;

        root.left = l;
        root.right = r;

        return root;
    }

    // solution2
    public TreeNode trimBST2(TreeNode root, int low, int high) {
        // time O(n)
        // space O(h)

        if(root == null)
            return root;

        if(root.val > high)
            return trimBST2(root.left, low, high);

        if(root.val < low)
            return trimBST2(root.right, low, high);

        root.left = trimBST2(root.left, low, high);
        root.right = trimBST2(root.right, low, high);

        return root;
    }
}
