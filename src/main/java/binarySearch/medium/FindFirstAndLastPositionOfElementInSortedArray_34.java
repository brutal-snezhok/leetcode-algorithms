package binarySearch.medium;

import java.util.Arrays;

// https://leetcode.com/problems/find-first-and-last-position-of-element-in-sorted-array/
public class FindFirstAndLastPositionOfElementInSortedArray_34 {
    // solution1
    public int[] searchRange1(int[] nums, int target) {
        // time O(logn)
        // space O(1)

        // run binary search two times. first - find first position, second - last position
        // modify bs

        int[] res = new int[2];
        res[0] = binarySearch(nums, target, true);
        res[1] = binarySearch(nums, target, false);

        return res;
    }

    private int binarySearch(int[] nums, int target, boolean isLeft) {
        int l = 0;
        int r = nums.length - 1;
        int ind = -1;

        while(l <= r) {
            int mid = l + (r - l) / 2;

            if(nums[mid] > target)
                r = mid - 1;
            else if(nums[mid] < target)
                l = mid + 1;
            else {
                ind  = mid;
                if(isLeft)
                    r = mid - 1;
                else
                    l = mid + 1;
            }
        }

        return ind;
    }

    // solution2
    public int[] searchRange2(int[] nums, int target) {
        // time O(logn)
        // space O(1)

        /*
            1. search left border with bs
            2. search right border with bs
        */

        int[] res = new int[2];
        Arrays.fill(res, -1);
        if(nums.length == 0)
            return res;

        int l = getLeft(nums, target);
        if(l == -1)
            return res;

        int r = getRight(nums, target);
        res[0] = l;
        res[1] = r;

        return res;
    }

    int getLeft(int[] nums, int target) {
        int l = 0;
        int r = nums.length - 1;
        while(l < r) {
            int mid = l + (r - l) / 2;

            if(nums[mid] >= target)
                r = mid;
            else
                l = mid + 1;
        }

        return nums[l] == target ? l : -1;
    }

    int getRight(int[] nums, int target) {
        int l = 0;
        int r = nums.length - 1;

        while(l < r) {
            int mid = l + (r - l) / 2;

            if(nums[mid] > target)
                r = mid;
            else
                l = mid + 1;
        }

        return nums[l] == target ? l : l - 1;
    }
}
