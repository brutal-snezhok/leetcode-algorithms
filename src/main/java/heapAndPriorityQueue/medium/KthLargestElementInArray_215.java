package heapAndPriorityQueue.medium;

import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Random;

// https://leetcode.com/problems/kth-largest-element-in-an-array/description/
public class KthLargestElementInArray_215 {
    Random rand = new Random();

    public int findKthLargest(int[] nums, int k) {
        // quick select
        // avg time O(n), worst case O(n^2)
        // space O(1)

        // take somehow random el, for example last one
        // then all values that less this number put in left side of arr, others in the right side
        // swap pivot and mid el
        // realize in what part is kth largest located and repeat algorithm

        k = nums.length - k; // kth largest = n - k smallest
        return quickSelect(nums, k, 0, nums.length - 1);
    }

    private int quickSelect(int[] nums, int k, int l, int r) {
        int randomInd = rand.nextInt(r - l + 1) + l; // choose pivot ind as random
        swap(nums, randomInd, r); // and swap it with last value

        int pivot = nums[r];
        int p = l;
        for(int i = l; i < r; i++) {
            if(nums[i] < pivot) {
                swap(nums, i, p);
                p++;
            }
        }

        swap(nums, p, r);

        if(p > k)
            return quickSelect(nums, k, l, p - 1);
        else if(p < k)
            return quickSelect(nums, k, p + 1, r);
        else
            return nums[p];

    }

    private void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }


    // ---------------------------------------------------------

    public int findKthLargest2(int[] nums, int k) {
        // time O(nlogk)
        // space O(k)

        Queue<Integer> q = new PriorityQueue<>(k);
        for(int num : nums) {
            if(q.size() < k) {
                q.add(num);
                continue;
            }

            if(q.peek() < num) {
                q.poll();
                q.add(num);
            }
        }

        return q.peek();
    }
}
