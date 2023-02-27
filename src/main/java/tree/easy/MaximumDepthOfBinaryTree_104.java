package tree.easy;

import tree.TreeNode;

import java.util.LinkedList;
import java.util.Queue;

// https://leetcode.com/problems/maximum-depth-of-binary-tree/description/
public class MaximumDepthOfBinaryTree_104 {
    public int maxDepth(TreeNode root) {
        // recursive
        // time O(n)
        // space O(h)

        if (root == null)
            return 0;

        return Math.max(maxDepth(root.left), maxDepth(root.right)) + 1;
    }

    public int maxDepth2(TreeNode root) {
        // iterative bfs
        // time O(n)
        // space O(h)

        if (root == null)
            return 0;

        Queue<TreeNode> q = new LinkedList<>();
        int depth = 0;
        q.add(root);
        while (!q.isEmpty()) {
            int n = q.size();
            for (int i = 0; i < n; i++) {
                TreeNode node = q.poll();
                if (node.left != null)
                    q.add(node.left);

                if (node.right != null)
                    q.add(node.right);
            }
            depth++;
        }

        return depth;
    }
}
