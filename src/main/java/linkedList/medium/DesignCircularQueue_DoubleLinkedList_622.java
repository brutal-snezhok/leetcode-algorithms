package linkedList.medium;

// https://leetcode.com/problems/design-circular-queue/description/
public class DesignCircularQueue_DoubleLinkedList_622 {
    // solution1: based on double linked list
    // all methods work with time O(1)
    Node left;
    Node right;
    int cap;
    int size;

    public DesignCircularQueue_DoubleLinkedList_622(int k) {
        left = new Node(null, -1, null);
        right = new Node(left, -1, null);
        left.next = right;
        cap = k;
        size = 0;
    }

    public boolean enQueue(int value) {
        if (size == cap)
            return false;

        Node oldTail = left.next;
        Node node = new Node(left, value, oldTail);
        oldTail.prev = node;
        left.next = node;
        size++;

        return true;
    }

    public boolean deQueue() {
        if (size == 0)
            return false;

        Node toRemoved = right.prev;
        Node beforeRemoved = toRemoved.prev;
        beforeRemoved.next = right;
        right.prev = beforeRemoved;
        size--;

        return true;
    }

    public int Front() {
        if (size == 0)
            return -1;

        return right.prev.val;
    }

    public int Rear() {
        if (size == 0)
            return -1;

        return left.next.val;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public boolean isFull() {
        return size == cap;
    }

    class Node {
        Node prev;
        int val;
        Node next;

        public Node(Node prev, int val, Node next) {
            this.prev = prev;
            this.val = val;
            this.next = next;
        }
    }
}
