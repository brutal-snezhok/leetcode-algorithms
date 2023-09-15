package binarySearch.medium;

// https://leetcode.com/problems/search-in-rotated-sorted-array-ii/description/
public class SearchInRotatedSortedArrayII_81 {
    public boolean search(int[] nums, int target) {
        // time O(n) - worst case when all numbers the same [2,2,2,2] and target for example 3.
        // time O(logn) - avg case
        // space O(1)
        int l = 0;
        int r = nums.length - 1;

        while(l <= r) {
            int mid = l + (r - l) / 2;
            if(nums[mid] == target)
                return true;

            if(nums[l] < nums[mid]) {
                // we are in left part
                if(target < nums[l] || target > nums[mid])
                    l = mid + 1;
                else
                    r = mid - 1;
            } else if(nums[l] > nums[mid]) {
                // we are in right part
                if(target < nums[mid] || target > nums[r])
                    r = mid - 1;
                else
                    l = mid + 1;
            }  else {
                //                                                   l   m   r
                // we do not know exactly in wich part we are. e.g: [2,2,2,3,1]
                //  l       m       r
                // [2,2,3,1,2,2,2,2,2]
                l++;
            }
        }

        return false;
    }

}
