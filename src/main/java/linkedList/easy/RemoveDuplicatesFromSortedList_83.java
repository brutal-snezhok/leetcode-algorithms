package linkedList.easy;

import linkedList.ListNode;

// https://leetcode.com/problems/remove-duplicates-from-sorted-list/
public class RemoveDuplicatesFromSortedList_83 {
    public ListNode deleteDuplicates(ListNode head) {
        // time O(n)
        // space O(1)

        if(head == null)
            return head;

        ListNode dummy = new ListNode(-101);
        dummy.next = head;
        ListNode prev = dummy;
        ListNode curr = head;
        while(curr != null) {
            ListNode next = curr.next;
            if(curr.val == prev.val) {
                prev.next = next;
                curr = next;
            } else {
                prev = curr;
                curr = next;
            }
        }

        return dummy.next;
    }

    public ListNode deleteDuplicates2(ListNode head) {
        // time O(n)
        // space O(1)

        if(head == null)
            return head;

        ListNode prev = null;
        ListNode curr = head;
        while (curr != null) {
            ListNode next = curr.next;
            if(prev != null && curr.val == prev.val) {
                prev.next = next;
                curr = next;
                continue;
            }

            prev = curr;
            curr = next;
        }

        return head;
    }
}
