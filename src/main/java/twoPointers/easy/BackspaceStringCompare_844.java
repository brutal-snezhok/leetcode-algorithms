package twoPointers.easy;

import java.util.ArrayDeque;
import java.util.Deque;

// https://leetcode.com/problems/backspace-string-compare/description/
public class BackspaceStringCompare_844 {
    // solution1
    public boolean backspaceCompare1(String s, String t) {
        // time O(n)
        // space O(n)

        /*
            use stack
            if meet char put it into stack
            if meet # if stack is not empty than poll one char
            use second stack and compare chars in it
        */

        Deque<Character> stack1 = new ArrayDeque<>();
        Deque<Character> stack2 = new ArrayDeque<>();

        // handle first string
        for(char ch : s.toCharArray()) {
            if(ch == '#' && !stack1.isEmpty())
                stack1.removeFirst();
            else if(ch != '#')
                stack1.addFirst(ch);
        }

        // handle second string
        for(char ch : t.toCharArray()) {
            if(ch == '#' && !stack2.isEmpty())
                stack2.removeFirst();
            else if(ch != '#')
                stack2.addFirst(ch);
        }

        // compare two stacks
        if(stack1.size() != stack2.size())
            return false;

        while(!stack1.isEmpty()) {
            char ch1 = stack1.removeFirst();
            char ch2 = stack2.removeFirst();

            if(ch1 != ch2)
                return false;
        }

        return true;
    }

    // solution2
    public boolean backspaceCompare2(String s, String t) {
        // use pointers
        // time O(n)
        // space O(1)

        /*
            go from end to begin of two strings
            compare two chars if they equal go further
            if not return false
        */

        int n = s.length() - 1;
        int m = t.length() - 1;

        while(n >= 0 || m >= 0) {
            // skip # in first string
            int i1 = getNextValidInd(s, n);

            // skip # in second string
            int i2 = getNextValidInd(t, m);

            if(i1 == -1 && i2 == -1)
                break;

            if(i1 < 0 || i2 < 0 || s.charAt(i1) != t.charAt(i2))
                return false;

            n = i1 - 1;
            m = i2 - 1;
        }

        return true;
    }

    private int getNextValidInd(String s, int ind) {
        int backSpaceCount = 0;
        while(ind >= 0) {
            if(s.charAt(ind) == '#')
                backSpaceCount++;
            else if(backSpaceCount > 0)
                backSpaceCount--;
            else
                break;

            ind--;
        }

        return ind;
    }
}
