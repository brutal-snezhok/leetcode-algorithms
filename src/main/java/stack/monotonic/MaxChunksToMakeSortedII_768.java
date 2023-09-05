package stack.monotonic;

import java.util.ArrayDeque;
import java.util.Deque;

// https://leetcode.com/problems/max-chunks-to-make-sorted-ii/description/
public class MaxChunksToMakeSortedII_768 {
    // TODO
    public int maxChunksToSorted(int[] arr) {
        // time O(n)
        // space O(n)

        int localMax;
        Deque<Integer> s = new ArrayDeque<>(); // monotonic increasing stack

        for(int i = 0; i < arr.length; i++) {
            localMax = arr[i];
            while(!s.isEmpty() && s.peekFirst() > arr[i])
                localMax = Math.max(localMax, s.removeFirst());

            s.addFirst(localMax);
        }

        return s.size();
    }
}
