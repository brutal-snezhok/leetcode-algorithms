package heapAndPriorityQueue.easy;

import java.util.PriorityQueue;
import java.util.Queue;

// https://leetcode.com/problems/minimum-amount-of-time-to-fill-cups/description/
public class MinimumAmountOfTimeToFillCups_2335 {
    // solution1
    public int fillCups1(int[] amount) {
        // time O(mlogn), n = amount.length, m = max(max(amount[i]), ceil(sum(amount[i] / 2)))
        // space O(1)

        Queue<Integer> maxHeap = new PriorityQueue<>((a1, a2) -> a2 - a1);
        for (int a : amount)
            if (a != 0)
                maxHeap.add(a);

        int res = 0;
        while (!maxHeap.isEmpty()) {
            int amount1 = maxHeap.poll();

            if (maxHeap.isEmpty()) {
                res += amount1;
                break;
            }

            int amount2 = maxHeap.poll();

            res++;
            amount1--;
            amount2--;

            if (amount1 > 0)
                maxHeap.add(amount1);
            if (amount2 > 0)
                maxHeap.add(amount2);
        }

        return res;
    }

    // solution2
    public int fillCups2(int[] amount) {
        /*
        * Necessary conditions (lower bound)

            res >= max(A)
            Because each time,
            one type can reduce at most 1 cup,
            so the final result is bigger or equal to max(A)

            res >= ceil(sum(A) / 2)
            Because each time,
            we can fill up to 2 cups,
            so the final result is bigger or equal to ceil(sum(A) / 2)
        * */
        // time O(n), n = amount.length, m = max(max(amount[i]), ceil(sum(amount[i] / 2)))
        // space O(1)

        int max = 0;
        int sum = 0;
        for (int a : amount) {
            max = Math.max(max, a);
            sum += a;
        }

        return Math.max(max, (sum + 1) / 2);
    }
}
