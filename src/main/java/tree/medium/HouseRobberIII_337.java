package tree.medium;

import tree.TreeNode;

// https://leetcode.com/problems/house-robber-iii/description/
public class HouseRobberIII_337 {
    // solution1
    public int rob1(TreeNode root) {
        // recursive, TLE
        // time O(2^n)
        // space O(h)

        return dfs(root, true);
    }

    private int dfs(TreeNode node, boolean canTake) {
        if(node == null)
            return 0;

        int maxProfit = 0;
        if(canTake) {
            maxProfit += node.val;
            maxProfit += dfs(node.left, false);
            maxProfit += dfs(node.right, false);
        }

        return Math.max(maxProfit, dfs(node.left, true) + dfs(node.right, true));
    }

    // solution2
    public int rob2(TreeNode root) {
        // time O(n)
        // space O(h)

        Pair p = dfs(root);
        return Math.max(p.incl, p.excl);
    }

    private Pair dfs(TreeNode node) {
        if(node == null)
            return new Pair(0, 0);

        Pair left = dfs(node.left);
        Pair right = dfs(node.right);

        Pair res = new Pair(node.val + left.excl + right.excl,
                Math.max(left.incl, left.excl) + Math.max(right.incl, right.excl));

        return res;
    }

    class Pair {
        int incl;
        int excl;

        public Pair(int incl, int excl) {
            this.incl = incl;
            this.excl = excl;
        }
    }
}
