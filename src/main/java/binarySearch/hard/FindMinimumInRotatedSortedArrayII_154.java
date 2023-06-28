package binarySearch.hard;

// https://leetcode.com/problems/find-minimum-in-rotated-sorted-array-ii/description/
public class FindMinimumInRotatedSortedArrayII_154 {
    public int findMin(int[] nums) {
        // time O(logn)
        // space O(1)

        int l = 0;
        int r = nums.length - 1;

        while(l < r) {
            int mid = l + (r - l) / 2;

            // here we can't be sure that the position of minimum in mid's left or right, so just decrease r
            if(nums[mid] == nums[r])
                r--;
            else if(nums[mid] < nums[r])
                r = mid;
            else
                l = mid + 1;
        }

        return nums[l];
    }
}
