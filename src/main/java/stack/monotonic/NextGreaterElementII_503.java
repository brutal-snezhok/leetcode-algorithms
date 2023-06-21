package stack.monotonic;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;
import java.util.Stack;

// https://leetcode.com/problems/next-greater-element-ii/description/
public class NextGreaterElementII_503 {
    // solution1
    public int[] nextGreaterElements1(int[] nums) {
        // monotonic stack
        // time O(n)
        // space O(n)

        int[] res = new int[nums.length];
        Deque<Pair> stack = new ArrayDeque<>();
        Arrays.fill(res, -1);
        int n = nums.length;
        for(int i = 0; i < 2 * n; i++) {
            int index = i % n;
            while(!stack.isEmpty() && stack.peekFirst().val < nums[index]) {
                Pair p = stack.removeFirst();
                res[p.ind] = nums[index];
            }

            stack.addFirst(new Pair(nums[index], index));
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

    // solution2
    public int[] nextGreaterElements2(int[] nums) {
        // monotonic stack
        // time O(n)
        // space O(n)

        int n = nums.length;
        Stack<int[]> s = new Stack<>(); // [val, ind], non increasing
        int[] res = new int[n];
        Arrays.fill(res, -1);
        for(int i = 0; i < 2 * n; i++) {
            int ind = i % n;
            while(!s.isEmpty() && s.peek()[0] < nums[ind]) {
                int[] poped = s.pop();
                res[poped[1]] = nums[ind];
            }
            s.push(new int[]{nums[ind], ind});
        }

        return res;
    }
}
