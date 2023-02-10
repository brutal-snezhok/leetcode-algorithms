package linkedList.medium;

import java.util.HashMap;
import java.util.Map;

// https://leetcode.com/problems/copy-list-with-random-pointer/description/
public class CopyListWithRandomPointer_138 {
    // first approach
    public Node copyRandomList(Node head) {
        // time O(n)
        // space O(n)
        if(head == null)
            return head;

        // create map: {oldNode, newNode}
        // first pass: create new nodes
        // second pass: add links for new list

        Map<Node, Node> map = new HashMap<>();
        Node curr = head;
        while(curr != null) {
            map.put(curr, new Node(curr.val));
            curr = curr.next;
        }

        curr = head;
        while(curr != null) {
            Node newNode = map.get(curr);
            newNode.next = map.get(curr.next);
            newNode.random = map.get(curr.random);
            curr = curr.next;
        }

        return map.get(head);
    }

    // second approach
    public static Node copyRandomList2(Node head) {
        // time O(n)
        // space O(1)
        if(head == null)
            return head;

        // first pass: create new Node right after original one's (A -> A' -> B -> B' -> C -> C')
        // second pass: fill random links for new nodes
        // third pass: fill next links in new nodes to point like (A' -> B' -> C')
        // return head of new list

        // first pass
        Node curr = head;
        while(curr != null) {
            Node next = curr.next;
            Node newNode = new Node(curr.val);
            curr.next = newNode;
            newNode.next = next;
            curr = next;
        }

        // second pass
        curr = head;
        while(curr != null && curr.next != null) {
            if(curr.random != null)
                curr.next.random = curr.random.next;
            curr = curr.next.next;
        }

        // third pass
        Node old = head;
        Node dummy = new Node(0);
        Node newHead = dummy;
        while(old != null) {
            newHead.next = old.next;
            old.next = old.next.next;

            old = old.next;
            newHead = newHead.next;
        }

        return dummy.next;
    }

    static class Node {
        int val;
        Node next;
        Node random;

        public Node(int val) {
            this.val = val;
            this.next = null;
            this.random = null;
        }

        public Node(int val, Node next) {
            this.val = val;
            this.next = next;
            this.random = null;
        }
    }

    public static void main(String[] args) {
        // [[3,null],[3,0],[3,null]]
        Node root = new Node(3);
        Node first = new Node(3);
        Node second = new Node(3);
        root.next = first;
        first.next = second;
        first.random = root;

        copyRandomList2(root);
    }
}
