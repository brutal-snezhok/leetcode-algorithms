package linkedList.easy;

// https://leetcode.com/problems/reverse-linked-list/
public class ReverseLinkedList_206 {
    public ListNode reverseList(ListNode head) {
        // iterative
        // time O(n)
        // space O(1)

        ListNode prev = null;
        ListNode curr = head;
        while(curr != null) {
            ListNode next = curr.next;
            curr.next = prev;
            prev = curr;
            curr = next;
        }

        return prev;
    }

    public ListNode reverseList2(ListNode head) {
        // recursive
        // time O(n)
        // space O(n)

        return reverse(null, head);
    }

    private ListNode reverse(ListNode prev, ListNode head) {
        if (head == null)
            return prev;

        ListNode next = head.next;
        head.next = prev;

        return reverse(head, next);
    }


    public class ListNode {
        int val;
        ListNode next;

        ListNode() {
        }

        ListNode(int val) {
            this.val = val;
        }

        ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }
    }
}
