package linkedList.medium;

import linkedList.ListNode;

// https://leetcode.com/problems/add-two-numbers/
public class AddTwoNumbers_2 {
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        // time O(n + m)
        // space O(1)

        int carry = 0;
        ListNode dummy = new ListNode();
        ListNode curr = null;
        ListNode prev = dummy;
        while (l1 != null || l2 != null) {
            int val1 = l1 == null ? 0 : l1.val;
            int val2 = l2 == null ? 0 : l2.val;

            int sum = val1 + val2 + carry;
            carry = sum / 10;
            curr = new ListNode(sum % 10);
            prev.next = curr;
            prev = curr;

            if (l1 != null)
                l1 = l1.next;
            if (l2 != null)
                l2 = l2.next;
        }

        if (carry != 0) {
            curr = new ListNode(carry);
            prev.next = curr;
        }


        return dummy.next;
    }

    public ListNode addTwoNumbers2(ListNode l1, ListNode l2) {
        // time O(n + m)
        // space O(1)

        int carry = 0;
        ListNode dummy = new ListNode();
        ListNode curr = dummy;
        while(l1 != null || l2 != null || carry != 0) {
            int sum = (l1 != null ? l1.val : 0) + (l2 != null ? l2.val : 0) + carry;
            carry = sum / 10;
            sum = sum % 10;

            curr.next = new ListNode(sum);
            curr = curr.next;
            l1 = l1 != null ? l1.next : null;
            l2 = l2 != null ? l2.next : null;
        }


        return dummy.next;
    }
}
