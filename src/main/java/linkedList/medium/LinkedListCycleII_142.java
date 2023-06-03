package linkedList.medium;

import linkedList.ListNode;

// https://leetcode.com/problems/linked-list-cycle-ii/
public class LinkedListCycleII_142  {
    public ListNode detectCycle(ListNode head) {
        // time O(n)
        // space O(1)

        if(head == null)
            return null;

        ListNode s = head;
        ListNode f = head;

        while(f != null && f.next != null) {
            s = s.next;
            f = f.next.next;

            if(s == f) {
                ListNode start = head;
                while(start != s) {
                    s = s.next;
                    start = start.next;
                }

                return start;
            }
        }

        return null;
    }
}
