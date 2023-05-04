package heapAndPriorityQueue.medium;

import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;

// https://leetcode.com/problems/least-number-of-unique-integers-after-k-removals/
public class LeastNumberOfUniqueIntegersAfterKRemovals_1481 {
    // solution1
    public int findLeastNumOfUniqueInts1(int[] arr, int k) {
        // time O(n + klogn)
        // space O(n)

        // create map val -> freq
        // remove values with smal freq first
        // create minHeap and add pairs(val, freq) comparing by freq
        // remove k elements

        Queue<int[]> minHeap = new PriorityQueue<>((arr1, arr2) -> arr1[1] - arr2[1]); // int[0] - val, int[1] - freq
        Map<Integer, Integer> map = new HashMap<>();
        for(int num : arr)
            map.put(num, map.getOrDefault(num, 0) + 1);

        for(Map.Entry<Integer, Integer> entry : map.entrySet())
            minHeap.add(new int[]{entry.getKey(), entry.getValue()});

        boolean needToAdd = false;
        while(k != 0) {
            int[] pair = minHeap.poll();

            if(k >= pair[1])
                k -= pair[1];
            else {
                needToAdd = true;
                break;
            }
        }

        return needToAdd ? minHeap.size() + 1 : minHeap.size();
    }

    // solution2
    public int findLeastNumOfUniqueInts2(int[] arr, int k) {
        // minHeap
        // time O(n + klogn)
        // space O(n)

        Queue<Integer> minHeap = new PriorityQueue<>();
        Map<Integer, Integer> map = new HashMap<>();
        for(int num : arr)
            map.put(num, map.getOrDefault(num, 0) + 1);

        minHeap.addAll(map.values());

        while(k > 0)
            k -= minHeap.poll();

        return k < 0 ? minHeap.size() + 1 : minHeap.size();
    }
}
