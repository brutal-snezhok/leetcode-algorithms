package binarySearch.medium;

import java.util.Arrays;

// https://leetcode.com/problems/longest-square-streak-in-an-array/description/
public class LongestSquareStreak_2501 {
    public static int longestSquareStreak(int[] nums) {
        // need to be resolved with set
        // time O(nlogn + nlogn) - sorting + binary search
        // space O(n)

        int res = -1;
        Arrays.sort(nums);
        for(int i = 0; i < nums.length; i++) {
            long num = nums[i];
            int currRes = 0;
            do {
                currRes++;
                num *= num;
                num = binarySearch(num, nums, i + 1, nums.length - 1);
            } while(num != -1);

            if(currRes > 1)
                res = Math.max(res, currRes);
        }

        return res;
    }

    private static long binarySearch(long target, int[] nums, int l, int r) {
        while(l <= r) {
            int mid = (l + r) / 2;
            if(nums[mid] == target)
                return target;
            else if(nums[mid] > target)
                r = mid - 1;
            else
                l = mid + 1;
        }

        return -1;
    }

    // [2, 4] - 2
    // [4,3,6,16,8,2] - 3
    // [2,3,5,6,7] - -1
    public static void main(String[] args) {
        System.out.println(longestSquareStreak(new int[]{4,3,6,16,8,2}));
    }
}
