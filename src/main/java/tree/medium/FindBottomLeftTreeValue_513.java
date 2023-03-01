package tree.medium;

import tree.TreeNode;

import java.util.LinkedList;
import java.util.Queue;

// https://leetcode.com/problems/find-bottom-left-tree-value/description/
public class FindBottomLeftTreeValue_513 {
    // bfs
    public int findBottomLeftValue(TreeNode root) {
        // time O(n)
        // space O(n)

        Queue<TreeNode> q = new LinkedList<>();
        TreeNode res = null;
        q.add(root);
        while(!q.isEmpty()) {
            int size = q.size();
            for(int i = 0; i < size; i++) {
                TreeNode curr = q.poll();
                res = curr;
                if(curr.right != null)
                    q.add(curr.right);
                if(curr.left != null)
                    q.add(curr.left);
            }
        }

        return res.val;
    }
}
