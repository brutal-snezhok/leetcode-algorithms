package tree.medium;

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

    class Node {
        public int val;
        public Node left;
        public Node right;
        public Node next;

        public Node() {}

        public Node(int _val) {
            val = _val;
        }

        public Node(int _val, Node _left, Node _right, Node _next) {
            val = _val;
            left = _left;
            right = _right;
            next = _next;
        }
    };
}
