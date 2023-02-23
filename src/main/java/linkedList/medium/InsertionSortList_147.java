package linkedList.medium;

import linkedList.ListNode;

// https://leetcode.com/problems/insertion-sort-list/description/
public class InsertionSortList_147 {
    public ListNode insertionSortList(ListNode head) {
        // time O(n^2)
        // space O(1)

        if (head.next == null)
            return head;

        ListNode dummy = new ListNode(-5001);
        dummy.next = head;
        ListNode prev;
        ListNode curr;

        ListNode restNodes = head.next;
        head.next = null;
        while (restNodes != null) {
            ListNode next = restNodes.next;

            prev = dummy;
            curr = prev.next;
            while (curr != null && curr.val < restNodes.val) {
                prev = curr;
                curr = curr.next;
            }

            prev.next = restNodes;

            if (curr != null)
                restNodes.next = curr;
            else
                restNodes.next = null;
            restNodes = next;
        }

        return dummy.next;
    }
}
