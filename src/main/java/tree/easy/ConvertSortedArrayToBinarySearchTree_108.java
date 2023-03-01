package tree.easy;

import tree.TreeNode;

// https://leetcode.com/problems/convert-sorted-array-to-binary-search-tree/description/
public class ConvertSortedArrayToBinarySearchTree_108 {
    public TreeNode sortedArrayToBST(int[] nums) {
        // time O(n)
        // space O(logn), since recursion and tree will be height balanced

        return dfs(nums, 0, nums.length - 1);
    }

    private TreeNode dfs(int[] nums, int l, int r) {
        if (l > r)
            return null;

        int mid = (l + r) / 2;

        TreeNode node = new TreeNode(nums[mid]);
        node.left = dfs(nums, l, mid - 1);
        node.right = dfs(nums, mid + 1, r);

        return node;
    }
}
