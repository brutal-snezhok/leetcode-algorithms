package tree.medium;

import tree.Node;

import java.util.LinkedList;
import java.util.Queue;

// https://leetcode.com/problems/populating-next-right-pointers-in-each-node/description/
public class PopulatingNextRightPointersEachNode_116 {
    // solution1
    public Node connect(Node root) {
        // recursive
        // time O(n)
        // space O(logn), logn - height of perfect binary tree

        traverse(root);
        return root;
    }

    private void traverse(Node root) {
        if(root == null || root.left == null)
            return;

        root.left.next = root.right;
        if(root.next != null)
            root.right.next = root.next.left;

        connect(root.left);
        connect(root.right);
    }

    // solution2
    public Node connect2(Node root) {
        // iterative
        // time O(n)
        // space O(1)
        if(root == null)
            return root;

        Node curr = root;
        Node nextLvl = root.left;
        while(curr != null && nextLvl != null) {
            curr.left.next = curr.right;
            if(curr.next != null)
                curr.right.next = curr.next.left;

            curr = curr.next;
            if(curr == null) {
                curr = nextLvl;
                nextLvl = curr.left;
            }
        }

        return root;
    }

    // solution3
    public Node connect3(Node root) {
        // time O(n)
        // space O(1)

        if(root == null)
            return root;

        Node curr = null;
        Node nextLvl = root;

        while(true) {
            curr = nextLvl;
            if(curr.left != null)
                nextLvl = curr.left;
            else
                break;

            while(curr != null) {
                curr.left.next = curr.right;
                if(curr.next != null)
                    curr.right.next = curr.next.left;

                curr = curr.next;
            }
        }

        return root;
    }

    // solution4
    public Node connect4(Node root) {
        // bfs
        // time O(n)
        // space O(n)

        if(root == null)
            return root;

        Node node = root;
        Queue<Node> q = new LinkedList<>();
        q.add(node);

        while(!q.isEmpty()) {
            int size = q.size();
            Node prev = q.poll();
            if(prev.left != null)
                q.add(prev.left);
            if(prev.right != null)
                q.add(prev.right);

            for(int i = 1; i < size; i++) {
                Node curr = q.poll();
                prev.next = curr;

                if(curr.left != null)
                    q.add(curr.left);
                if(curr.right != null)
                    q.add(curr.right);

                prev = curr;
            }
        }

        return root;
    }
}
