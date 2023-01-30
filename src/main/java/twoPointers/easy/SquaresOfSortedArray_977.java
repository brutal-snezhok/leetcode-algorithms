package twoPointers.easy;

// https://leetcode.com/problems/squares-of-a-sorted-array/description/
public class SquaresOfSortedArray_977 {
    public int[] sortedSquares(int[] nums) {
        // time O(n)
        // space O(1)

        int n = nums.length;
        int[] res = new int[n];
        int l = 0;
        int r = n - 1;
        int index = n - 1;

        while(l <= r) {
            int left = nums[l] * nums[l];
            int right = nums[r] * nums[r];

            if(left < right) {
                res[index] = right;
                r--;
            } else {
                res[index] = left;
                l++;
            }
            index--;
        }

        return res;
    }
}
