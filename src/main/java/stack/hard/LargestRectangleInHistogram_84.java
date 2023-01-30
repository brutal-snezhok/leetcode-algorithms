package stack.hard;

import java.util.ArrayDeque;
import java.util.Deque;

// https://leetcode.com/problems/largest-rectangle-in-histogram/
public class LargestRectangleInHistogram_84 {
    public int largestRectangleArea(int[] heights) {
        // time O(n)
        // space O(n)

        // height values in stack will be in increasing order
        Deque<Pair> stack = new ArrayDeque<>();
        int res = 0;
        for(int i = 0; i < heights.length; i++) {
            int start = i;
            while(!stack.isEmpty() && stack.peekFirst().height > heights[i]) {
                Pair p = stack.removeFirst();
                res = Math.max(res, p.height * (i - p.index));
                start = p.index;
            }
            stack.addFirst(new Pair(start, heights[i]));
        }

        while(!stack.isEmpty()) {
            Pair p = stack.removeFirst();
            res = Math.max(res, p.height * (heights.length - p.index));
        }

        return res;
    }

    class Pair {
        int index;
        int height;

        public Pair(int index, int height) {
            this.index = index;
            this.height = height;
        }
    }
}
