package stack.medium;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;

// https://leetcode.com/problems/next-greater-element-ii/description/
public class NextGreaterElementII_503 {
    public int[] nextGreaterElements(int[] nums) {
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
}
