package tree.easy;

import tree.TreeNode;

import java.util.LinkedList;
import java.util.Queue;

// https://leetcode.com/problems/same-tree/description/
public class SameTree_100 {
    // solution1
    public boolean isSameTree(TreeNode p, TreeNode q) {
        // recursive
        // time O(min(n1, n2))
        // space O(min(h1, h2))

        if (p == null && q == null)
            return true;
        if (p == null || q == null || p.val != q.val)
            return false;

        return isSameTree(p.left, q.left) && isSameTree(p.right, q.right);
    }



    // solution2
    public boolean isSameTree2(TreeNode p, TreeNode q) {
        // iterative
        // time O(n+m)
        // space O(h)
        if(p == null && q == null)
            return true;

        if(p == null || q == null || p.val != q.val)
            return false;

        Queue<TreeNode> q1 = new LinkedList<>();
        Queue<TreeNode> q2 = new LinkedList<>();
        q1.add(p);
        q2.add(q);

        while(!q1.isEmpty() && !q2.isEmpty()) {
            int size1 = q1.size();
            int size2 = q2.size();
            if(size1 != size2)
                return false;

            for(int i = 0; i < size1; i++) {
                TreeNode curr1 = q1.poll(); // 1
                TreeNode curr2 = q2.poll(); // 1

                if(curr1.val != curr2.val)
                    return false;

                if((curr1.left == null && curr2.left != null) || (curr1.left != null && curr2.left == null))
                    return false;

                if((curr1.right == null && curr2.right != null) || (curr1.right != null && curr2.right == null))
                    return false;

                addToQ(q1, curr1.left);
                addToQ(q1, curr1.right);
                addToQ(q2, curr2.left);
                addToQ(q2, curr2.right);
            }
        }

        return true;
    }

    private void addToQ(Queue<TreeNode> q, TreeNode node) {
        if(node != null)
            q.add(node);
    }
}
