package linkedList.easy;

import linkedList.ListNode;

// https://leetcode.com/problems/linked-list-cycle/description/
public class LinkedListCycle_141 {
    public boolean hasCycle(ListNode head) {
        // time O(n)
        // space O(1)

        if(head == null)
            return false;

        ListNode s = head;
        ListNode f = head.next;
        while(f != null && f.next != null) {
            s = s.next;
            f = f.next.next;

            if(s == f)
                return true;
        }

        return false;
    }
}
