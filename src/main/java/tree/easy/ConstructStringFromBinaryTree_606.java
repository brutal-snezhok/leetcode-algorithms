package tree.easy;

import tree.TreeNode;

// https://leetcode.com/problems/construct-string-from-binary-tree/description/
public class ConstructStringFromBinaryTree_606 {
    public String tree2str(TreeNode root) {
        // recursive
        // time O(n)
        // space O(h)

        StringBuilder res = new StringBuilder();
        dfs(root, res);

        return res.toString();
    }

    private void dfs(TreeNode node, StringBuilder res) {
        if(node == null)
            return;

        res.append(node.val);
        if(node.left != null) {
            res.append("(");
            dfs(node.left, res);
            res.append(")");
        }

        if(node.right != null) {
            if(node.left == null)
                res.append("()");
            res.append("(");
            dfs(node.right, res);
            res.append(")");
        }
    }
}
