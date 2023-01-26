package stack.easy;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Stack;

// https://leetcode.com/problems/valid-parentheses/
public class ValidParentheses_20 {
    public boolean isValid(String str) {
        // time O(n)
        // space O(n)

        Stack<Character> s = new Stack<>();
        for (char ch : str.toCharArray()) {
            if (ch == '(')
                s.push(')');
            else if (ch == '{')
                s.push('}');
            else if (ch == '[')
                s.push(']');
            else {
                if (s.isEmpty() || s.pop() != ch)
                    return false;
            }
        }

        return s.isEmpty();
    }

    public int calPoints2(String[] operations) {
        // time O(n)
        // space O(n)

        // From Deque Java doc (https://docs.oracle.com/javase/7/docs/api/java/util/Deque.html):
        // Deques can also be used as LIFO (Last-In-First-Out) stacks. This interface should be used in preference to the legacy Stack class.
        // check comment https://leetcode.com/problems/baseball-game/solutions/127468/baseball-game/comments/815271

        Deque<String> s = new ArrayDeque<>();
        for (String str : operations) {
            if (str.equals("+")) {
                int val1 = Integer.parseInt(s.removeFirst());
                int val2 = Integer.parseInt(s.peekFirst()) + val1;
                s.addFirst(String.valueOf(val1));
                s.addFirst(String.valueOf(val2));
            } else if (str.equals("D")) {
                int val = Integer.parseInt(s.peekFirst());
                s.addFirst(String.valueOf(val * 2));
            } else if (str.equals("C"))
                s.removeFirst();
            else
                s.addFirst(str);
        }

        int res = 0;
        while (!s.isEmpty())
            res += Integer.parseInt(s.removeFirst());

        return res;
    }
}
