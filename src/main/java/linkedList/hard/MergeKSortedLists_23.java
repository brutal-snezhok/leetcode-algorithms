package linkedList.hard;

import linkedList.ListNode;

// https://leetcode.com/problems/merge-k-sorted-lists/description/
public class MergeKSortedLists_23 {
    // solution1
    public ListNode mergeKLists(ListNode[] lists) {
        // merge lists one by one: first merge two lists, then merge third list with result of merging previous two etc.
        // time O(k*n), k - number of linked lists, n - total number of nodes in two lists
        // space O(1)

        if(lists.length == 0)
            return null;
        if(lists.length == 1)
            return lists[0];

        ListNode first = lists[0];
        for(int i = 1; i < lists.length; i++) {
            ListNode second = lists[i];
            first = merge(first, second);
        }

        return first;
    }

    private ListNode merge(ListNode l1, ListNode l2) {
        if(l1 == null)
            return l2;
        if(l2 == null)
            return l1;

        ListNode dummy = new ListNode();
        ListNode curr = dummy;
        while(l1 != null && l2 != null) {
            if(l1.val < l2.val) {
                curr.next = l1;
                l1 = l1.next;
            } else {
                curr.next = l2;
                l2 = l2.next;
            }
            curr = curr.next;
        }
        if(l1 == null)
            curr.next = l2;
        if(l2 == null)
            curr.next = l1;

        return dummy.next;
    }

    // solution2
    public ListNode mergeKLists2(ListNode[] lists) {
        // Merge with Divide And Conquer
        // time O(n*logk), k - number of linked lists, n - total number of nodes in two lists
        // space O(1)

        if(lists.length == 0)
            return null;
        if(lists.length == 1)
            return lists[0];

        int interval = 1;
        while(interval < lists.length) {
            for(int i = interval; i < lists.length; i += interval * 2)
                lists[i - interval] = merge(lists[i - interval], lists[i]);

            interval *= 2;
        }

        return lists[0];
    }
}
