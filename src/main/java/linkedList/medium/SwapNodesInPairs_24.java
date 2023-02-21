package linkedList.medium;

import linkedList.ListNode;

// https://leetcode.com/problems/swap-nodes-in-pairs/description/
public class SwapNodesInPairs_24 {
    public ListNode swapPairs(ListNode head) {
        // iterative
        // time O(n)
        // space O(1)

        if (head == null || head.next == null)
            return head;

        ListNode dummy = new ListNode();
        ListNode prev = dummy;
        ListNode curr = head;
        while (curr != null && curr.next != null) {
            ListNode next = curr.next;
            ListNode restNodes = next.next;
            prev.next = curr.next;
            next.next = curr;
            curr.next = restNodes;

            prev = curr;
            curr = restNodes;
        }

        return dummy.next;
    }

    public ListNode swapPairs2(ListNode head) {
        // recursive
        // time O(n)
        // space O(n)

        if (head == null || head.next == null)
            return head;

        ListNode next = head.next;
        ListNode restNodes = next.next;
        next.next = head;
        head.next = swapPairs2(restNodes);

        return next;
    }
}
