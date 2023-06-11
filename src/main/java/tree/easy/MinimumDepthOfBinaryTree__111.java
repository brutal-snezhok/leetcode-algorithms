package tree.easy;

import tree.TreeNode;

import java.util.LinkedList;
import java.util.Queue;

// https://leetcode.com/problems/minimum-depth-of-binary-tree/description/
public class MinimumDepthOfBinaryTree__111 {
    public int minDepth(TreeNode root) {
        // bfs
        // time O(n)
        // space O(n)

        if(root == null)
            return 0;

        int minDepth = 1;
        Queue<TreeNode> q = new LinkedList<>();
        q.add(root);

        while(!q.isEmpty()) {
            int size = q.size();
            for(int i = 0; i < size; i++) {
                TreeNode node = q.poll();
                if(node.left == null && node.right == null)
                    return minDepth;

                if(node.left != null)
                    q.add(node.left);
                if(node.right != null)
                    q.add(node.right);
            }
            minDepth++;
        }

        return minDepth;
    }
}
