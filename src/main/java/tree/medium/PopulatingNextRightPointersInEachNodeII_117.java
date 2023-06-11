package tree.medium;

import tree.Node;

// https://leetcode.com/problems/populating-next-right-pointers-in-each-node-ii/description/
public class PopulatingNextRightPointersInEachNodeII_117 {
    public Node connect(Node root) {
        // time O(n)
        // space O(1)

        if(root == null)
            return root;

        Node curr = root;
        Node dummy = new Node();
        Node p = dummy;

        while(curr != null) {
            if(curr.left != null) {
                p.next = curr.left;
                p = p.next;
            }
            if(curr.right != null) {
                p.next = curr.right;
                p = p.next;
            }

            if(curr.next != null) {
                curr = curr.next;
            } else {
                curr = dummy.next;
                dummy.next = null;
                p = dummy;
            }
        }

        return root;
    }
}
