package linkedList.medium;

import linkedList.ListNode;

// https://leetcode.com/problems/partition-list/description/
public class PartitionList_86 {
    public ListNode partition(ListNode head, int x) {
        // time O(n)
        // space O(1)

        if (head == null || head.next == null)
            return head;

        ListNode dummyLess = new ListNode(); // <
        ListNode dummyGreater = new ListNode(); // >=
        ListNode dummyGreaterHead = dummyGreater;
        ListNode res = dummyLess;

        ListNode curr = head;
        while (curr != null) {
            if (curr.val < x) {
                dummyLess.next = curr;
                dummyLess = dummyLess.next;
            } else {
                dummyGreater.next = curr;
                dummyGreater = dummyGreater.next;
            }
            curr = curr.next;
        }
        dummyGreater.next = null;
        dummyLess.next = dummyGreaterHead.next;

        return res.next;
    }
}
