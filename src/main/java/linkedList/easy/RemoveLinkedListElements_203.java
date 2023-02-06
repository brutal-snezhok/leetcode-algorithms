package linkedList.easy;

import linkedList.ListNode;

// https://leetcode.com/problems/remove-linked-list-elements/
public class RemoveLinkedListElements_203 {
    public ListNode removeElements(ListNode head, int val) {
        // time O(n)
        // space O(1)

        ListNode dummy = new ListNode();
        dummy.next = head;
        ListNode prev = dummy;
        ListNode curr = head;
        while(curr != null) {
            ListNode next = curr.next;
            if(curr.val == val) {
                prev.next = next;
                curr = next;
            } else {
                prev = curr;
                curr = next;
            }
        }
        return dummy.next;
    }

    public ListNode removeElements2(ListNode head, int val) {
        // time O(n)
        // space O(1)

        ListNode dummy = new ListNode();
        ListNode prev = dummy;
        ListNode curr = head;
        while(curr != null) {
            ListNode next = curr.next;
            if(curr.val == val) {
                prev.next = next;
                curr = next;
                continue;
            }

            prev.next = curr;
            prev = curr;
            curr = next;
        }

        return dummy.next;
    }

    public ListNode removeElements3(ListNode head, int val) {
        while(head != null && head.val == val) {
            head = head.next;
        }

        ListNode curr = head;
        while(curr != null && curr.next != null) {
            if (curr.next.val == val) {
                curr.next = curr.next.next;
            } else
                curr = curr.next;
        }

        return head;
    }
}
