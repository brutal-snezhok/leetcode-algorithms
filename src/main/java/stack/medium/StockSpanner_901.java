package stack.medium;

import java.util.ArrayDeque;
import java.util.Deque;

// https://leetcode.com/problems/online-stock-span/description/
public class StockSpanner_901 {
    // use monotonic decrising stack for time: O(n) solution, space: O(n)
    Deque<Pair> stack;

    public StockSpanner_901() {
        stack = new ArrayDeque<>();
    }

    public int next(int price) {
        int count = 1;
        while(!stack.isEmpty() && stack.peek().val <= price) {
            Pair p = stack.removeFirst();
            count += p.span;
        }
        stack.addFirst(new Pair(price, count));

        return count;
    }

    class Pair {
        int val;
        int span;

        public Pair(int val, int span) {
            this.val = val;
            this.span = span;
        }
    }
}
