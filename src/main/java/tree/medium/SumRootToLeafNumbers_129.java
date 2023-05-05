package tree.medium;

import tree.TreeNode;

import java.util.ArrayDeque;
import java.util.Deque;

// https://leetcode.com/problems/sum-root-to-leaf-numbers/
public class SumRootToLeafNumbers_129 {
    // solution1
    public int sumNumbers1(TreeNode root) {
        // dfs, recursive
        // time O(n)
        // space O(n)

        return sum(root, 0);
    }

    private int sum(TreeNode root, int currSum) {
        if(root == null)
            return 0;

        int newVal = currSum * 10 + root.val;
        if(root.left == null && root.right == null)
            return newVal;

        int left = sum(root.left, newVal);
        int right = sum(root.right, newVal);

        return left + right;
    }

    // solution2
    public int sumNumbers2(TreeNode root) {
        // iterative, dfs
        // time O(n)
        // space O(n)

        Deque<Pair> stack = new ArrayDeque<>();
        stack.addFirst(new Pair(root, root.val));
        int res = 0;

        while(!stack.isEmpty()) {
            Pair p = stack.removeFirst();

            if(p.node.left == null && p.node.right == null) {
                res += p.sum;
                continue;
            }

            if(p.node.left != null)
                stack.addFirst(new Pair(p.node.left, 10 * p.sum + p.node.left.val));

            if(p.node.right != null)
                stack.addFirst(new Pair(p.node.right, 10 * p.sum + p.node.right.val));
        }

        return res;
    }

    class Pair {
        TreeNode node;
        int sum;

        public Pair(TreeNode node, int sum) {
            this.node = node;
            this.sum = sum;
        }
    }
}
