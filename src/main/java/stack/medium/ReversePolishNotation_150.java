package stack.medium;

import java.util.ArrayDeque;
import java.util.Deque;

// https://leetcode.com/problems/evaluate-reverse-polish-notation/description/
public class ReversePolishNotation_150 {
    public int evalRPN(String[] tokens) {
        // time O(n)
        // space O(n)

        Deque<Integer> s = new ArrayDeque<>();
        for(String str : tokens) {
            if("+".equals(str)) {
                int val1 = s.removeFirst();
                int val2 = s.removeFirst();
                s.addFirst(val2 + val1);
            } else if ("-".equals(str)) {
                int val1 = s.removeFirst();
                int val2 = s.removeFirst();
                s.addFirst(val2 - val1);
            } else if ("*".equals(str)) {
                int val1 = s.removeFirst();
                int val2 = s.removeFirst();
                s.addFirst(val2 * val1);
            } else if ("/".equals(str)) {
                int val1 = s.removeFirst();
                int val2 = s.removeFirst();
                s.addFirst(val2 / val1);
            } else {
                s.addFirst(Integer.parseInt(str));
            }
        }

        return s.peekFirst();
    }
}
