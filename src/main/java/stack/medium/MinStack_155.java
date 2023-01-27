package stack.medium;

import java.util.ArrayDeque;
import java.util.Deque;

// https://leetcode.com/problems/min-stack/description/
public class MinStack_155 {
    // time complexity of all operations is O(1)

    Deque<Pair> s;

    public MinStack_155() {
        s = new ArrayDeque<>();
    }

    public void push(int val) {
        if(s.isEmpty())
            s.addFirst(new Pair(val, val));
        else {
            s.addFirst(new Pair(val, Math.min(s.peekFirst().minVal, val)));
        }
    }

    public void pop() {
        s.removeFirst();
    }

    public int top() {
        return s.peekFirst().val;
    }

    public int getMin() {
        return s.peekFirst().minVal;
    }

    class Pair {
        int val;
        int minVal;

        public Pair(int val, int minVal) {
            this.val = val;
            this.minVal = minVal;
        }
    }
}
