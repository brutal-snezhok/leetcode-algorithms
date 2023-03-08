package tree.medium;

import tree.TreeNode;

import java.util.*;

// https://leetcode.com/problems/binary-tree-level-order-traversal/
public class BinaryTreeLevelOrderTraversal_102 {
    public List<List<Integer>> levelOrder(TreeNode root) {
        // bfs
        // time O(n)
        // space O(n)

        List<List<Integer>> res = new ArrayList<>();
        if(root == null)
            return res;

        Queue<TreeNode> q = new LinkedList<>();
        q.add(root);
        res.add(List.of(root.val));
        while(!q.isEmpty()) {
            int n = q.size();
            List<Integer> list = new ArrayList<>();
            for(int i = 0; i < n; i++) {
                TreeNode node = q.poll();

                if(node.left != null) {
                    list.add(node.left.val);
                    q.add(node.left);
                }

                if(node.right != null) {
                    list.add(node.right.val);
                    q.add(node.right);
                }
            }

            if(!list.isEmpty())
                res.add(list);
        }

        return res;
    }
}
