package binarySearch.medium;

// https://leetcode.com/problems/find-peak-element/description/
public class FindPeakElement_162 {
    public int findPeakElement(int[] nums) {
        // time O(logn)
        // space O(1)

        int l = 0;
        int r = nums.length - 1;

        while(l < r) {
            int mid = l + (r - l) / 2;

            if(nums[mid] > nums[mid + 1])
                r = mid;
            else
                l = mid + 1;
        }

        return l;
    }
}
