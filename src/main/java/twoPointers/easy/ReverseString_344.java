package twoPointers.easy;

import java.util.ArrayDeque;
import java.util.Deque;

// https://leetcode.com/problems/reverse-string/description/
public class ReverseString_344 {
    public void reverseString1(char[] s) {
        // time O(n)
        // space O(1)

        int l = 0;
        int r = s.length - 1;
        while(l < r) {
            char temp = s[l];
            s[l] = s[r];
            s[r] = temp;
            l++;
            r--;
        }
    }

    public void reverseString2(char[] s) {
        // recursive
        // time O(n)
        // space O(n)

        helper(0, s.length - 1, s);
    }

    private void helper(int l, int r, char[] s) {
        if(l >= r)
            return;

        char temp = s[l];
        s[l] = s[r];
        s[r] = temp;
        helper(++l, --r, s);
    }

    public void reverseString(char[] s) {
        // with stack
        // time O(n)
        // space O(n)

        Deque<Character> stack = new ArrayDeque<>();
        for(char ch : s)
            stack.addFirst(ch);

        for(int i = 0; i < s.length; i++)
            s[i] = stack.removeFirst();
    }
}
