package stack.easy;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Stack;

// https://leetcode.com/problems/baseball-game/description/
public class BaseballGame_682 {

    public int calPoints(String[] operations) {
        // time O(n)
        // space O(n)

        Stack<String> s = new Stack<>();
        for(String str : operations) {
            if(str.equals("+")) {
                int val1 = Integer.parseInt(s.pop());
                int val2 = Integer.parseInt(s.peek()) + val1;
                s.push(String.valueOf(val1));
                s.push(String.valueOf(val2));
            } else if(str.equals("D")) {
                int val = Integer.parseInt(s.peek());
                s.push(String.valueOf(val * 2));
            } else if(str.equals("C"))
                s.pop();
            else
                s.push(str);
        }

        int res = 0;
        while(!s.isEmpty())
            res += Integer.parseInt(s.pop());

        return res;
    }


    public int calPoints2(String[] operations) {
        // time O(n)
        // space O(n)

        // From Deque Java doc (https://docs.oracle.com/javase/7/docs/api/java/util/Deque.html):
        // Deques can also be used as LIFO (Last-In-First-Out) stacks. This interface should be used in preference to the legacy Stack class.
        // check comment https://leetcode.com/problems/baseball-game/solutions/127468/baseball-game/comments/815271

        Deque<String> s = new ArrayDeque<>();
        for(String str : operations) {
            if(str.equals("+")) {
                int val1 = Integer.parseInt(s.removeFirst());
                int val2 = Integer.parseInt(s.peekFirst()) + val1;
                s.addFirst(String.valueOf(val1));
                s.addFirst(String.valueOf(val2));
            } else if(str.equals("D")) {
                int val = Integer.parseInt(s.peekFirst());
                s.addFirst(String.valueOf(val * 2));
            } else if(str.equals("C"))
                s.removeFirst();
            else
                s.addFirst(str);
        }

        int res = 0;
        while(!s.isEmpty())
            res += Integer.parseInt(s.removeFirst());

        return res;
    }
}
