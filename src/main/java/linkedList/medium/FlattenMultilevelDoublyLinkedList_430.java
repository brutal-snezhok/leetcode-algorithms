package linkedList.medium;

import java.util.ArrayDeque;
import java.util.Deque;

// https://leetcode.com/problems/flatten-a-multilevel-doubly-linked-list/
public class FlattenMultilevelDoublyLinkedList_430 {
    public Node flatten(Node head) {
        // time O(n)
        // space O(n)
        if(head == null)
            return head;

        // traverse list
        // if curr has child -> put curr.next to stack and make child as next node for curr
        // if curr.next == null and stack is not empty -> pop node from stack and make it next node for curr

        Deque<Node> stack = new ArrayDeque<>();
        Node curr = head;
        while(curr != null) {
            if(curr.child != null) {
                if(curr.next != null)
                    stack.addFirst(curr.next);

                curr.next = curr.child;
                curr.next.prev = curr;
                curr.child = null;
            } else if(curr.next == null && !stack.isEmpty()) {
                curr.next = stack.removeFirst();
                curr.next.prev = curr;
            }

            curr = curr.next;
        }

        return head;
    }

    class Node {
        public int val;
        public Node prev;
        public Node next;
        public Node child;
    };
}
