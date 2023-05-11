package heapAndPriorityQueue.medium;

import java.util.*;

// https://leetcode.com/problems/find-k-pairs-with-smallest-sums/description/
public class FindKPairsWithSmallestSums_373 {
    // solution1, MLE
    public List<List<Integer>> kSmallestPairs1(int[] nums1, int[] nums2, int k) {
        // time O(n*m + n*m*log(n*m)))
        // space O(n*m)

        Queue<int[]> minHeap = new PriorityQueue<>((a, b) -> nums1[a[0]] + nums2[a[1]] - nums1[b[0]] - nums2[b[1]]); // {i, j}
        for(int i = 0; i < nums1.length; i++) {
            for(int j = 0; j < nums2.length; j++) {
                minHeap.add(new int[]{i, j});
            }
        }

        int count = 0;
        List<List<Integer>> res = new ArrayList<>();
        while(!minHeap.isEmpty() && count != k) {
            int[] peek = minHeap.poll();
            count++;
            res.add(Arrays.asList(nums1[peek[0]], nums2[peek[1]]));
        }

        return res;
    }

    // solution2
    public List<List<Integer>> kSmallestPairs2(int[] nums1, int[] nums2, int k) {
        // time O(n*m + n*m*log(k)))
        // space O(k)

        Queue<List<Integer>> maxHeap = new PriorityQueue<>(k, (p1, p2) -> (p2.get(0) + p2.get(1)) - (p1.get(0) + p1.get(1))); // {i, j}
        List<List<Integer>> res = new ArrayList<>();
        for(int i = 0; i < nums1.length; i++) {
            for(int j = 0; j < nums2.length; j++) {
                if(maxHeap.size() < k)
                    maxHeap.add(Arrays.asList(nums1[i], nums2[j]));
                else {
                    List<Integer> peek = maxHeap.peek();
                    if(nums1[i] + nums2[j] < peek.get(0) + peek.get(1)) {
                        maxHeap.poll();
                        maxHeap.add(Arrays.asList(nums1[i], nums2[j]));
                    } else
                        break;
                }
            }
        }

        return new ArrayList<>(maxHeap);
    }
}
