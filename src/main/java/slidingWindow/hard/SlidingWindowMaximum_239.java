package slidingWindow.hard;

import java.util.ArrayDeque;
import java.util.Deque;

// https://leetcode.com/problems/sliding-window-maximum/description/
public class SlidingWindowMaximum_239 {
    public int[] maxSlidingWindow(int[] nums, int k) {
        // time O(n)
        // space O(k)

        // monotonic decreasing deque
        Deque<Integer> deque = new ArrayDeque<>(); // save indexes
        int[] res = new int[nums.length - k + 1];
        int l = 0;
        int r = 0;
        int i = 0;
        while (r < nums.length) {
            // pop smaller values from deque
            while (!deque.isEmpty() && nums[deque.peekLast()] < nums[r])
                deque.removeLast();

            deque.addLast(r);

            if (r + 1 >= k) {
                res[i++] = nums[deque.peekFirst()];
                l++;
            }

            // remove left value from deque
            if (l > deque.peekFirst())
                deque.removeFirst();

            r++;
        }

        return res;
    }
}
