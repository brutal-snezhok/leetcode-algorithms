package linkedList.easy;

import linkedList.ListNode;

// https://leetcode.com/problems/merge-two-sorted-lists/description/
public class MergeTwoSortedLists_21 {
    public ListNode mergeTwoLists(ListNode list1, ListNode list2) {
        // iterative
        // time O(n + m)
        // space O(1)

        if (list1 == null)
            return list2;
        if (list2 == null)
            return list1;

        ListNode dummy = new ListNode();
        ListNode curr = dummy;
        while (list1 != null && list2 != null) {
            if (list1.val < list2.val) {
                curr.next = list1;
                list1 = list1.next;
            } else {
                curr.next = list2;
                list2 = list2.next;
            }
            curr = curr.next;
        }

        while (list1 != null) {
            curr.next = list1;
            list1 = list1.next;
            curr = curr.next;
        }

        while (list2 != null) {
            curr.next = list2;
            list2 = list2.next;
            curr = curr.next;
        }

        return dummy.next;
    }

    public ListNode mergeTwoLists2(ListNode list1, ListNode list2) {
        // recursive
        // time O(n + m)
        // space O(n + m)

        if(list1 == null)
            return list2;
        if(list2 == null)
            return list1;

        if(list1.val < list2.val) {
            list1.next = mergeTwoLists(list1.next, list2);
            return list1;
        } else {
            list2.next = mergeTwoLists(list1, list2.next);
            return list2;
        }
    }
}
