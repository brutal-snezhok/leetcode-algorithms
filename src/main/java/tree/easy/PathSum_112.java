package tree.easy;

import tree.TreeNode;

import java.util.LinkedList;
import java.util.Queue;

// https://leetcode.com/problems/path-sum/
public class PathSum_112 {
    public boolean hasPathSum(TreeNode root, int targetSum) {
        // recursive
        // time O(n)
        // space O(h)

        if (root == null)
            return false;

        if (targetSum == root.val && root.left == null && root.right == null)
            return true;

        targetSum -= root.val;

        boolean l = hasPathSum(root.left, targetSum);
        boolean r = hasPathSum(root.right, targetSum);

        return l || r;
    }

    public boolean hasPathSum2(TreeNode root, int targetSum) {
        // iterative
        // time O(n)
        // space O(n)

        if (root == null)
            return false;

        Queue<TreeNode> q = new LinkedList<>();
        Queue<Integer> sumQ = new LinkedList<>();
        sumQ.offer(root.val);
        q.offer(root);

        while (!q.isEmpty()) {
            Integer parentValue = sumQ.poll();
            TreeNode node = q.poll();

            if (node != null && node.left != null) {
                q.offer(node.left);
                sumQ.offer(node.left.val + parentValue);
            }
            if (node != null && node.right != null) {
                q.offer(node.right);
                sumQ.offer(node.right.val + parentValue);
            }
            if (node != null && node.left == null && node.right == null && parentValue == targetSum)
                return true;
        }

        return false;
    }
}
