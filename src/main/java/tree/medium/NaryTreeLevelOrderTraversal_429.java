package tree.medium;

import tree.Node;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

// https://leetcode.com/problems/n-ary-tree-level-order-traversal/description/
public class NaryTreeLevelOrderTraversal_429 {
    public List<List<Integer>> levelOrder(Node root) {
        // time O(n)
        // space O(n)

        List<List<Integer>> res = new ArrayList<>();
        if(root == null)
            return res;

        Queue<Node> q = new LinkedList<>();
        q.add(root);
        while(!q.isEmpty()) {
            int size = q.size();
            List<Integer> curr = new ArrayList<>();
            for(int i = 0; i < size; i++) {
                Node node = q.poll();
                curr.add(node.val);

                if(node.children != null)
                    for(Node child : node.children)
                        q.add(child);
            }

            res.add(curr);
        }

        return res;
    }
}
