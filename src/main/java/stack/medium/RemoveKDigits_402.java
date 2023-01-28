package stack.medium;

import java.util.ArrayDeque;
import java.util.Deque;

// https://leetcode.com/problems/remove-k-digits/description/
public class RemoveKDigits_402 {
    public String removeKdigits(String num, int k) {
        // time O(n)
        // space O(n)

        // values in stack will be in increasing order
        Deque<Integer> stack = new ArrayDeque<>();
        for(char ch : num.toCharArray()) {
            int n = ch - '0';
            while(!stack.isEmpty() &&  k != 0 && stack.peek() > n) {
                stack.removeFirst();
                k--;
            }
            stack.addFirst(n);
        }

        // handle case [1,2,3,4,5], when need to remove from end
        while(k != 0 && !stack.isEmpty())
            stack.removeFirst();

        StringBuilder res = new StringBuilder();
        while(!stack.isEmpty())
            res.insert(0, stack.removeFirst());

        // remove all the 0 at the head of res
        while(res.length() > 1 && res.charAt(0) == '0')
            res.deleteCharAt(0);

        return res.toString().isEmpty() ? "0" : res.toString();
    }
}
