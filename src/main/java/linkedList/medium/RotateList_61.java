package linkedList.medium;

import linkedList.ListNode;

// https://leetcode.com/problems/rotate-list/description/
public class RotateList_61 {
    public ListNode rotateRight(ListNode head, int k) {
        // time O(n)
        // space O(1)

        if (head == null || head.next == null)
            return head;

        int length = getLength(head);
        k = k % length;
        if (k == 0)
            return head;

        ListNode lastNodeOfFirstList = getNode(head, k);
        ListNode l2 = lastNodeOfFirstList.next;
        lastNodeOfFirstList.next = null;
        ListNode newHead = l2;
        while (l2.next != null)
            l2 = l2.next;
        l2.next = head;

        return newHead;
    }

    private int getLength(ListNode head) {
        int count = 0;
        while (head != null) {
            count++;
            head = head.next;
        }

        return count;
    }

    private ListNode getNode(ListNode node, int k) {
        ListNode dummy = new ListNode();
        dummy.next = node;
        ListNode prev = dummy;
        ListNode curr = dummy;

        while (k-- != -1)
            curr = curr.next;

        while (curr != null) {
            curr = curr.next;
            prev = prev.next;
        }

        return prev;
    }
}
