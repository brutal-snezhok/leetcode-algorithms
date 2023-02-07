package linkedList.medium;

import linkedList.ListNode;

// https://leetcode.com/problems/reorder-list/description/
public class ReorderList_143 {
    public void reorderList(ListNode head) {
        // time O(n)
        // space O(1)

        ListNode s = head;
        ListNode f = head.next;
        while(f != null && f.next != null) {
            s = s.next;
            f = f.next.next;
        }

        ListNode second = s.next;
        s.next = null;
        ListNode l2 = reverse(second);
        ListNode l1 = head;

        while(l2 != null) {
            ListNode nextL1 = l1.next;
            l1.next = l2;
            ListNode nextL2 = l2.next;
            l2.next = nextL1;
            l1 = nextL1;
            l2 = nextL2;
        }
    }

    private ListNode reverse(ListNode node) {
        ListNode prev = null;
        ListNode curr = node;
        while(curr != null) {
            ListNode next = curr.next;
            curr.next = prev;
            prev = curr;
            curr = next;
        }

        return prev;
    }
}
