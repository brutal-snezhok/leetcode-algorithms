package stack.easy;

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
}
