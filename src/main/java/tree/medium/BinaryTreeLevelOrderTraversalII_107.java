package tree.medium;

import tree.TreeNode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

// https://leetcode.com/problems/binary-tree-level-order-traversal-ii/description/
public class BinaryTreeLevelOrderTraversalII_107 {
    public List<List<Integer>> levelOrderBottom(TreeNode root) {
        // bfs
        // time O(n), n - num of all nodes on tree
        // space O(n)

        List<List<Integer>> res = new LinkedList<>();
        if(root == null)
            return res;

        Queue<TreeNode> q = new LinkedList<>();
        q.add(root);
        while(!q.isEmpty()) {
            int size = q.size();
            List<Integer> curr = new ArrayList<>();
            for(int i = 0; i < size; i++) {
                TreeNode node = q.poll();
                curr.add(node.val);

                if(node.left != null)
                    q.add(node.left);

                if(node.right != null)
                    q.add(node.right);
            }
            res.add(0, curr);
        }

        return res;
    }
}
