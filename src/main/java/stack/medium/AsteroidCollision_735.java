package stack.medium;

import java.util.ArrayDeque;
import java.util.Deque;

// https://leetcode.com/problems/asteroid-collision/
public class AsteroidCollision_735 {
    public int[] asteroidCollision(int[] asteroids) {
        // time O(n)
        // space O(n)

        Deque<Integer> s = new ArrayDeque<>();
        for(int num : asteroids) {
            while (!s.isEmpty() && num < 0 && s.peek() > 0) {
                int diff = s.peek() + num;
                if (diff < 0)
                    s.removeFirst();
                else if (diff == 0) {
                    s.removeFirst();
                    num = 0;
                } else
                    num = 0;
            }

            if(num != 0)
                s.addFirst(num);
        }

        int[] res = new int[s.size()];
        for(int i = s.size() - 1; i >= 0; i--)
            res[i] = s.removeFirst();

        return res;
    }
}
