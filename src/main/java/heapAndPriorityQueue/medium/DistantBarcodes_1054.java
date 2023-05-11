package heapAndPriorityQueue.medium;

import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;

// https://leetcode.com/problems/distant-barcodes/description/
public class DistantBarcodes_1054 {
    public int[] rearrangeBarcodes(int[] barcodes) {
        // time O(nlogm), n - number of elements in arr
        // space O(m), m - number of unique elements

        // map {val, freq}
        // create maxHeap from map

        Map<Integer, Integer> map = new HashMap<>();
        for (int num : barcodes)
            map.put(num, map.getOrDefault(num, 0) + 1);

        Queue<Map.Entry<Integer, Integer>> maxHeap = new PriorityQueue<>((e1, e2) -> e2.getValue() - e1.getValue() == 0 ?
                (e2.getKey() - e1.getKey()) : e2.getValue() - e1.getValue());
        maxHeap.addAll(map.entrySet());

        int[] res = new int[barcodes.length];

        int i = 0;
        while (!maxHeap.isEmpty()) {
            Map.Entry<Integer, Integer> entry1 = maxHeap.poll();
            res[i++] = entry1.getKey();

            if (!maxHeap.isEmpty()) {
                Map.Entry<Integer, Integer> entry2 = maxHeap.poll();
                res[i++] = entry2.getKey();

                entry2.setValue(entry2.getValue() - 1);
                if (entry2.getValue() > 0)
                    maxHeap.add(entry2);
            }

            entry1.setValue(entry1.getValue() - 1);
            if (entry1.getValue() > 0)
                maxHeap.add(entry1);

        }

        return res;
    }
}
