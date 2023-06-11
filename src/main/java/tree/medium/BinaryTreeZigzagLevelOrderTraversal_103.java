package tree.medium;

import tree.TreeNode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

// https://leetcode.com/problems/binary-tree-zigzag-level-order-traversal/description/
public class BinaryTreeZigzagLevelOrderTraversal_103 {
    // solution1
    public List<List<Integer>> zigzagLevelOrder1(TreeNode root) {
        // bfs
        // time O(n)
        // space O(n)

        List<List<Integer>> res = new ArrayList<>();
        if(root == null)
            return res;

        Queue<TreeNode> q = new LinkedList<>();
        int j = 0;
        q.add(root);
        while(!q.isEmpty()) {
            int size = q.size();
            List<Integer> levels = new ArrayList<>();
            for(int i = 0; i < size; i++) {
                TreeNode node = q.poll();
                levels.add(node.val);

                if(node.left != null)
                    q.add(node.left);
                if(node.right != null)
                    q.add(node.right);
            }

            if(j % 2 != 0)
                reverse(levels);
            res.add(levels);
            j++;
        }

        return res;
    }

    private void reverse(List<Integer> levels) {
        int l = 0;
        int r = levels.size() - 1;
        while(l < r) {
            int temp = levels.get(l);
            levels.set(l, levels.get(r));
            levels.set(r, temp);
            l++;
            r--;
        }
    }

    // solution2
    public List<List<Integer>> zigzagLevelOrder2(TreeNode root) {
        // bfs + using linked list
        // time O(n)
        // space O(n)

        List<List<Integer>> res = new ArrayList<>();
        if(root == null)
            return res;

        Queue<TreeNode> q = new LinkedList<>();
        int j = 0;
        q.add(root);
        while(!q.isEmpty()) {
            int size = q.size();
            List<Integer> levels = new LinkedList<>();
            for(int i = 0; i < size; i++) {
                TreeNode node = q.poll();
                if(j % 2 == 0)
                    levels.add(node.val);
                else
                    levels.add(0, node.val);

                if(node.left != null)
                    q.add(node.left);
                if(node.right != null)
                    q.add(node.right);
            }

            res.add(levels);
            j++;
        }

        return res;
    }
}
