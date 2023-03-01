package tree.hard;

import tree.TreeNode;

// https://leetcode.com/problems/binary-tree-maximum-path-sum/description/
public class BinaryTreeMaximumPathSum_124 {
    public int maxPathSum(TreeNode root) {
        // time O(n)
        // space O(h)

        Pair res = dfs(root);
        return res.pathSum;
    }

    private Pair dfs(TreeNode node) {
        if(node == null)
            return new Pair(Integer.MIN_VALUE, 0);

        Pair l = dfs(node.left);
        Pair r = dfs(node.right);
        Pair res = new Pair(Math.max(Math.max(l.pathSum, r.pathSum), node.val + l.maxSum + r.maxSum),
                Math.max(0, node.val + Math.max(l.maxSum, r.maxSum)));

        return res;
    }

    class Pair {
        int pathSum;
        int maxSum;

        public Pair(int pathSum, int maxSum) {
            this.pathSum = pathSum;
            this.maxSum = maxSum;
        }
    }



    // second solution: with global variable
    int maxPath = Integer.MIN_VALUE;

    public int maxPathSum2(TreeNode root) {
        // time O(n)
        // space O(h)
        dfs2(root);

        return maxPath;
    }

    private int dfs2(TreeNode node) {
        if(node == null)
            return 0;

        int l = dfs2(node.left);
        int r = dfs2(node.right);

        l = Math.max(0, l);
        r = Math.max(0, r);
        maxPath = Math.max(maxPath, node.val + l + r);

        return node.val + Math.max(l, r);
    }
}
