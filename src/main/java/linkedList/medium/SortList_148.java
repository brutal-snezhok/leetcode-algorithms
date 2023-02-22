package linkedList.medium;

import linkedList.ListNode;

// https://leetcode.com/problems/sort-list/description/
public class SortList_148 {
    public ListNode sortList(ListNode head) {
        // recursive
        // time O(n)
        // space O(logn)

        // divide list into two smaller lists
        // sort them
        // merge two sorted lists

        if (head == null || head.next == null)
            return head;

        ListNode dummy = new ListNode();
        dummy.next = head;
        ListNode s = dummy;
        ListNode f = dummy;
        while (f != null && f.next != null) {
            s = s.next;
            f = f.next.next;
        }

        ListNode l1 = head;
        ListNode l2 = s.next;
        s.next = null;

        ListNode sortedL1 = sortList(l1);
        ListNode sortedL2 = sortList(l2);

        return mergeTwoLists(sortedL1, sortedL2);
    }

    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        // time O(n + m)
        // space O(1)

        if (l1 == null)
            return l2;
        if (l2 == null)
            return l1;

        ListNode dummy = new ListNode();
        ListNode curr = dummy;

        while (l1 != null && l2 != null) {
            if (l1.val < l2.val) {
                curr.next = l1;
                curr = curr.next;
                l1 = l1.next;
            } else {
                curr.next = l2;
                curr = curr.next;
                l2 = l2.next;
            }
        }

        if (l1 != null)
            curr.next = l1;
        if (l2 != null)
            curr.next = l2;

        return dummy.next;
    }
}
