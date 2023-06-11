package tree.medium;

import tree.TreeNode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

// https://leetcode.com/problems/binary-tree-right-side-view/
public class BinaryTreeRightSideView_199 {
    // solution1
    public List<Integer> rightSideView1(TreeNode root) {
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

    // solution2
    public List<Integer> rightSideView2(TreeNode root) {
        // time O(n)
        // space O(n)

        List<Integer> res = new ArrayList<>();
        if(root == null)
            return res;

        Queue<TreeNode> q = new LinkedList<>();
        q.add(root);
        while(!q.isEmpty()) {
            int size = q.size();

            for(int i = 0; i < size; i++) {
                TreeNode node = q.poll();
                if(i == size - 1)
                    res.add(node.val);

                if(node.left != null)
                    q.add(node.left);
                if(node.right != null)
                    q.add(node.right);
            }
        }

        return res;
    }
}
