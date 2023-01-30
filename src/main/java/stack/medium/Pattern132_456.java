package stack.medium;

import java.util.ArrayDeque;
import java.util.Deque;

// https://leetcode.com/problems/132-pattern/
public class Pattern132_456 {
    public boolean find132pattern(int[] nums) {
        // time O(n)
        // space O(n)

        // go through arr
        // use monotonic decreasing stack to save pair{val, min}
        // if curr >= stack.peek() -> stack.pop()
        // if curr < stack.peek().val && curr > stack.peek().min -> return true
        // else just add val to stack

        // [-1, 3, 2, 0]
        // [{-1, -1}]

        Deque<Pair> stack = new ArrayDeque<>();
        int minVal = Integer.MAX_VALUE;
        for(int curr : nums) {
            while(!stack.isEmpty() && stack.peekFirst().val <= curr)
                stack.removeFirst();
            if(!stack.isEmpty() && stack.peekFirst().min < curr)
                return true;

            minVal = Math.min(minVal, curr);
            stack.addFirst(new Pair(curr, minVal));
        }

        return false;
    }

    class Pair {
        int val;
        int min;

        public Pair(int val, int min) {
            this.val = val;
            this.min = min;
        }
    }
}
