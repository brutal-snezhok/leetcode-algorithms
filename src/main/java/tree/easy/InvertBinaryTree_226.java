package tree.easy;

import tree.TreeNode;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.LinkedList;
import java.util.Queue;

// https://leetcode.com/problems/invert-binary-tree/description/
public class InvertBinaryTree_226 {
    public TreeNode invertTree(TreeNode root) {
        // recursive
        // time O(n)
        // space O(h), h - height of tree

        if (root == null)
            return root;

        TreeNode temp = root.left;
        root.left = invertTree(root.right);
        root.right = invertTree(temp);

        return root;
    }

    public TreeNode invertTree2(TreeNode root) {
        // iterative bfs
        // time O(n)
        // space O(n)

        if(root == null)
            return root;

        Queue<TreeNode> q = new LinkedList<>();
        q.add(root);
        while(!q.isEmpty()) {
            int n = q.size();
            for(int i = 0; i < n; i++) {
                TreeNode node = q.poll();

                TreeNode temp = node.left;
                node.left = node.right;
                node.right = temp;

                if(node.left != null)
                    q.add(node.left);
                if(node.right != null)
                    q.add(node.right);
            }
        }

        return root;
    }

    public TreeNode invertTree3(TreeNode root) {
        // iterative dfs
        // time O(n)
        // space O(n)

        if(root == null)
            return root;

        Deque<TreeNode> stack = new ArrayDeque<>();
        TreeNode node = root;
        while(!stack.isEmpty() || node != null) {
            while(node != null) {
                stack.addFirst(node);
                node = node.left;
            }
            node = stack.removeFirst();

            TreeNode temp = node.left;
            node.left = node.right;
            node.right = temp;

            node = node.left; // attention, since changed left and right children, here need to go on left side (because it is right before changing)
        }

        return root;
    }
}
