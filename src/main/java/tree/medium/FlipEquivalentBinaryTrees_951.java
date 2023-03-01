package tree.medium;

import tree.TreeNode;

// https://leetcode.com/problems/flip-equivalent-binary-trees/description/
public class FlipEquivalentBinaryTrees_951 {
    public boolean flipEquiv(TreeNode root1, TreeNode root2) {
        // time O(min(n1,n2)) n1 - number of vertex of r1, n2 - number of vertex of r2
        // space O(min(h1,h2)) h1 - height of r1, h2 - height of r2

        if(root1 == null && root2 == null)
            return true;
        if(root1 == null && root2 != null || root1 != null && root2 == null)
            return false;
        if(root1.val != root2.val)
            return false;

        return flipEquiv(root1.left, root2.left) && flipEquiv(root1.right, root2.right) ||
                flipEquiv(root1.right, root2.left) && flipEquiv(root1.left, root2.right);
    }
}
