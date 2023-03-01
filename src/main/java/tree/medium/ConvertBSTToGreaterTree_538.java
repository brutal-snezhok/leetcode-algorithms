package tree.medium;

import tree.TreeNode;

import java.util.ArrayDeque;
import java.util.Deque;

// https://leetcode.com/problems/convert-bst-to-greater-tree/
public class ConvertBSTToGreaterTree_538 {
    // solution1
    int sum = 0;

    public TreeNode convertBST(TreeNode root) {
        // recursive
        // time O(n)
        // space O(h)
        if(root == null)
            return null;

        convertBST(root.right);
        root.val += sum;
        sum = root.val;
        convertBST(root.left);

        return root;
    }

    // solution2
    public TreeNode convertBST2(TreeNode root) {
        // iterative
        // time O(n)
        // space O(n)
        if(root == null)
            return null;

        Deque<TreeNode> stack = new ArrayDeque<>();
        TreeNode curr = root;
        int sum = 0;
        while(!stack.isEmpty() || curr != null) {
            while(curr != null) {
                stack.addFirst(curr);
                curr = curr.right;
            }

            curr = stack.removeFirst();
            curr.val += sum;
            sum = curr.val;
            curr = curr.left;
        }

        return root;
    }
}
