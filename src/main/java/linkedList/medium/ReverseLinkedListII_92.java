package linkedList.medium;

import linkedList.ListNode;

// https://leetcode.com/problems/reverse-linked-list-ii/description/
public class ReverseLinkedListII_92 {
    public ListNode reverseBetween(ListNode head, int left, int right) {
        // pay attention that left and right are positions and not values of list
        // time O(n)
        // space O(1)

        if (head.next == null || left == right)
            return head;

        ListNode dummy = new ListNode();
        dummy.next = head;
        ListNode prev = dummy;
        ListNode curr = head;
        int i = 1;

        while (left != i++) {
            prev = curr;
            curr = curr.next;
        }

        ListNode p1 = prev;
        prev = null;
        ListNode lastRotated = curr;
        while (right + 1 != i++) {
            ListNode next = curr.next;
            curr.next = prev;
            prev = curr;
            curr = next;
        }

        ListNode p2 = curr.next;
        curr.next = prev;
        p1.next = curr;
        lastRotated.next = p2;

        return dummy.next;
    }
}
