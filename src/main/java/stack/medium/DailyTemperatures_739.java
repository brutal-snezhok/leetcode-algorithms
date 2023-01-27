package stack.medium;

import java.util.ArrayDeque;
import java.util.Deque;

// https://leetcode.com/problems/daily-temperatures/description/
public class DailyTemperatures_739 {
    // monotonic decreasing stack tasks:
    // https://leetcode.com/problems/daily-temperatures/solutions/109832/java-easy-ac-solution-with-stack/comments/655713

    public int[] dailyTemperatures(int[] temperatures) {
        // time O(n)
        // space O(n)

        int[] res = new int[temperatures.length];
        Deque<Integer> stack = new ArrayDeque<>(); // save indexes of temperatures
        for(int i = 0; i < temperatures.length; i++) {
            while(!stack.isEmpty() && temperatures[stack.peekFirst()] < temperatures[i]) {
                int index = stack.removeFirst();
                res[index] = i - index;
            }
            stack.addFirst(i);
        }

        return res;
    }

    public int[] dailyTemperatures2(int[] temperatures) {
        // time O(n)
        // space O(n)

        int[] res = new int[temperatures.length];
        Deque<Pair> stack = new ArrayDeque<>(); // save indexes of temperatures
        for(int i = 0; i < temperatures.length; i++) {
            while(!stack.isEmpty() && stack.peekFirst().val < temperatures[i]) {
                Pair peek = stack.removeFirst();
                res[peek.ind] = i - peek.ind;
            }
            stack.addFirst(new Pair(temperatures[i], i));
        }

        return res;
    }

    class Pair {
        int val;
        int ind;

        public Pair(int val, int ind) {
            this.val = val;
            this.ind = ind;
        }
    }

}
