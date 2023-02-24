package linkedList.hard;

import linkedList.ListNode;

// https://leetcode.com/problems/reverse-nodes-in-k-group/
public class ReverseNodesInKGroup_25 {
    public ListNode reverseKGroup(ListNode head, int k) {
        // time O(n)
        // space O(1)

        if (k == 1)
            return head;

        ListNode dummy = new ListNode();
        dummy.next = head;

        ListNode prev = dummy;
        ListNode curr = head;
        ListNode rest = null;
        int count = 1;
        while (curr != null) {
            if (count == k) {
                rest = curr.next;
                curr.next = null;
                ListNode prevHead = prev.next;
                ListNode newHead = reverse(prevHead);
                prev.next = newHead;
                prevHead.next = rest;

                count = 0;
                prev = prevHead;
                curr = prevHead;
            }

            count++;
            curr = curr.next;
        }

        return dummy.next;
    }

    private ListNode reverse(ListNode node) {
        ListNode prev = null;
        ListNode curr = node;
        while (curr != null) {
            ListNode next = curr.next;
            curr.next = prev;
            prev = curr;
            curr = next;
        }

        return prev;
    }

    public static void main(String[] args) {
        ListNode node = new ListNode(1, new ListNode(2, new ListNode(3, new ListNode(4, new ListNode(5)))));
        //reverseKGroup(node, 2);
    }
}
