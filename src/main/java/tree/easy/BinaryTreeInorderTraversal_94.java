package tree.easy;

import tree.TreeNode;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

// https://leetcode.com/problems/binary-tree-inorder-traversal/
public class BinaryTreeInorderTraversal_94 {
    public List<Integer> inorderTraversal(TreeNode root) {
        // recursive
        // time O(n)
        // space O(h), h - height of tree, can be n, and can be logn in avg

        List<Integer> res = new ArrayList<>();
        dfs(res, root);

        return res;
    }

    private void dfs(List<Integer> res, TreeNode node) {
        if(node == null)
            return;

        dfs(res, node.left);
        res.add(node.val);
        dfs(res, node.right);
    }

    public List<Integer> inorderTraversal2(TreeNode root) {
        // iterative
        // time O(n)
        // space O(n)

        List<Integer> res = new ArrayList<>();
        Deque<TreeNode> stack = new ArrayDeque<>();
        TreeNode node = root;
        while(!stack.isEmpty() || node != null) {
            while(node != null) {
                stack.addFirst(node);
                node = node.left;
            }
            node = stack.removeFirst();
            res.add(node.val);
            node = node.right;
        }

        return res;
    }
}
