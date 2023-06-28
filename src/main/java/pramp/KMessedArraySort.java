package pramp;

import java.util.PriorityQueue;
import java.util.Queue;

public class KMessedArraySort {
    /*
        Given an array of integers arr where each element is at most k places away from its sorted position,
         code an efficient function sortKMessedArray that sorts arr.
         For instance, for an input array of size 10 and k = 2, an element belonging to index 6
         in the sorted array will be located at either index 4, 5, 6, 7 or 8 in the input array.

        Analyze the time and space complexities of your solution.

        Example:
        input:  arr = [1, 4, 5, 2, 3, 7, 8, 6, 10, 9], k = 2
        output: [1, 2, 3, 4, 5, 6, 7, 8, 9, 10]
    * */

    /*
        PQ => [1, 4, 5]
        1, PQ=>[4, 5, 2]
        2 , PQ=> [4, 5, 3]
        3, PQ=> [4, 5, 7]
        4, PQ => [5, 7, 8]
    */
    static int[] sortKMessedArray(int[] arr, int k) {
        // time O(nlogk)
        // space O(k)

        Queue<Integer> minHeap = new PriorityQueue<>();
        int n = arr.length;
        int[] res = new int[n];
        int i = 0;
        while (i < k + 1 && i < n)
            minHeap.add(arr[i++]);

        int l = 0;
        for (int j = k + 1; j < n; j++) {
            res[l++] = minHeap.poll();
            minHeap.add(arr[j]);
        }

        while (!minHeap.isEmpty())
            res[l++] = minHeap.poll();

        return res;
    }

    public static void main(String[] args) {

    }
}
