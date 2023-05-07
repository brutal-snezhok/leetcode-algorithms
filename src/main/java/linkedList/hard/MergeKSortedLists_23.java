package linkedList.hard;

import linkedList.ListNode;

import java.util.PriorityQueue;
import java.util.Queue;

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

    // solution3
    public ListNode mergeKLists3(ListNode[] lists) {
        // time O(k + nlogk) = O(nlogk), k - number of elements in heap
        // space O(k)

        // add all root nodes to minHeap
        // take smallest from heap and add it to the result

        Queue<ListNode> minHeap = new PriorityQueue<>((n1, n2) -> n1.val - n2.val);
        ListNode dummy = new ListNode();
        ListNode curr = dummy;

        // add started node to minHeap
        for(ListNode node : lists)
            if(node != null)
                minHeap.add(node);

        while(!minHeap.isEmpty()) {
            ListNode node = minHeap.poll();
            curr.next = node;
            curr = curr.next;

            if(node.next != null)
                minHeap.add(node.next);
        }

        return dummy.next;
    }
}
