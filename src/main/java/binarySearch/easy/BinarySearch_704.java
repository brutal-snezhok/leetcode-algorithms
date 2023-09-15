package binarySearch.easy;

// https://leetcode.com/problems/binary-search/description/
public class BinarySearch_704 {

    // solution1
    public int search1(int[] nums, int target) {
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

    // solution2
    public int search2(int[] nums, int target) {
        // find lower bound
        // time O(logn)
        // space O(1)

        int l = 0;
        int r = nums.length - 1;
        while(l < r) {
            int mid = l + (r - l) / 2;

            if(nums[mid] >= target)
                r = mid;
            else
                l = mid + 1;
        }

        // l - minimal val satisfying the condition in if statement
        return (l < nums.length && nums[l] == target) ? l : -1;
    }

    // solution3
    public int search3(int[] nums, int target) {
        // time O(logn)
        // space O(1)

        // pay attention on borders
        int l = -1;
        int r = nums.length;

        while(r - l > 1) {
            int mid = l + (r - l) / 2;

            if(nums[mid] >= target)
                r = mid;
            else
                l = mid;
        }

        return (r == nums.length || nums[r] != target) ? -1 : r;
    }
}
