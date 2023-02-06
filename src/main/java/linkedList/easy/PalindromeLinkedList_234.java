package linkedList.easy;

import linkedList.ListNode;

// https://leetcode.com/problems/palindrome-linked-list/description/
public class PalindromeLinkedList_234 {
    public boolean isPalindrome(ListNode head) {
        // time O(n)
        // space O(1)

        // find mid
        // reverse second part of linked list
        // compare first and second parts

        ListNode s = head;
        ListNode f = head.next;
        while(f != null && f.next != null) {
            s = s.next;
            f = f.next.next;
        }

        ListNode second = s.next;
        s.next = null;
        second = reverse(second);

        while(second != null) {
            if(head.val != second.val)
                return false;

            head = head.next;
            second = second.next;
        }

        return true;
    }

    private ListNode reverse(ListNode node) {
        ListNode curr = node;
        ListNode prev = null;
        while(curr != null) {
            ListNode next = curr.next;
            curr.next = prev;
            prev = curr;
            curr = next;
        }

        return prev;
    }
}
