package linkedList.medium;

import linkedList.ListNode;

// https://leetcode.com/problems/remove-nth-node-from-end-of-list/description/
public class RemoveNthNodeFromEndList_19 {
    public ListNode removeNthFromEnd(ListNode head, int n) {
        // time O(n)
        // space O(1)

        ListNode dummy = new ListNode();
        dummy.next = head;
        ListNode p1 = dummy;
        ListNode p2 = head;
        while(n-- != 0)
            p2 = p2.next;

        while(p2 != null) {
            p1 = p1.next;
            p2 = p2.next;
        }

        ListNode next = p1.next.next;
        p1.next = next;

        return dummy.next;
    }
}
