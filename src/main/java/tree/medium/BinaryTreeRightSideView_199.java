package tree.medium;

import tree.TreeNode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

// https://leetcode.com/problems/binary-tree-right-side-view/
public class BinaryTreeRightSideView_199 {
    public List<Integer> rightSideView(TreeNode root) {
        // bfs
        // time O(n)
        // space O(n)
        List<Integer> values = new ArrayList<>();
        if (root == null)
            return values;

        Queue<TreeNode> q = new LinkedList<>();
        q.add(root);

        while (!q.isEmpty()) {
            int n = q.size();
            TreeNode currNode = null;
            for (int i = 0; i < n; i++) {
                TreeNode qNode = q.poll();
                currNode = qNode;
                if (qNode.left != null)
                    q.add(qNode.left);
                if (qNode.right != null)
                    q.add(qNode.right);
            }
            if (currNode != null)
                values.add(currNode.val);
        }

        return values;
    }
}
