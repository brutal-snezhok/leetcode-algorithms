package binarySearch.easy;

// https://leetcode.com/problems/binary-search/description/
public class BinarySearch_704 {
    public int search(int[] nums, int target) {
        // time O(logn)
        // space O(1)

        return binarySearch(nums, 0, nums.length - 1, target);
    }

    private int binarySearch(int[] nums, int l, int r, int target) {
        while(l <= r) {
            int mid = l + (r - l) / 2; // to prevent overflow
            if(nums[mid] == target)
                return mid;
            else if(nums[mid] > target)
                r = mid - 1;
            else
                l = mid + 1;
        }

        return -1;
    }
}
