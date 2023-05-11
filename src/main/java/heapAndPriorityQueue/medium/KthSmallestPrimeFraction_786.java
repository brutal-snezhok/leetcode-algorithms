package heapAndPriorityQueue.medium;

import java.util.PriorityQueue;
import java.util.Queue;

// https://leetcode.com/problems/k-th-smallest-prime-fraction/
public class KthSmallestPrimeFraction_786 {
    // solution1
    public int[] kthSmallestPrimeFraction1(int[] arr, int k) {
        // time O(n^2)
        // space O(n^2)

        Queue<int[]> minHeap = new PriorityQueue<>((arr1, arr2) -> (arr1[0] / arr1[1]) - (arr2[0] / arr2[1]));
        for(int i = 0; i < arr.length; i++) {
            for(int j = i + 1; j < arr.length; j++) {
                minHeap.add(new int[]{arr[i], arr[j]});
            }
        }

        int count = 0;
        int[] res = null;
        while(!minHeap.isEmpty()) {
            res = minHeap.poll();
            count++;
            if(count == k)
                break;
        }

        return res;
    }

    // solution2
    public int[] kthSmallestPrimeFraction(int[] arr, int k) {
        // time O(min(n, k) + klogn)
        // space O(k)

        int n = arr.length;
        Queue<int[]> minHeap = new PriorityQueue<>(k, (a, b) -> arr[a[0]] * arr[n - 1 - b[1]] - arr[n - 1 - a[1]] * arr[b[0]]); // {row, col}

        for(int i = 0; i < Math.min(k, n); i++)
            minHeap.add(new int[]{i, 0});

        int count = 0;
        int[] res = null;
        while(!minHeap.isEmpty()) {
            int[] peek = minHeap.poll();
            count++;
            res = new int[]{arr[peek[0]], arr[n - 1 - peek[1]]};
            if(count == k)
                break;

            peek[1] += 1;
            if(peek[1] < n)
                minHeap.add(peek);
        }

        return res;
    }

    public static void main(String[] args) {
        KthSmallestPrimeFraction_786 smallest = new KthSmallestPrimeFraction_786();
        smallest.kthSmallestPrimeFraction1(new int[]{1, 29, 47}, 1);
    }
}
