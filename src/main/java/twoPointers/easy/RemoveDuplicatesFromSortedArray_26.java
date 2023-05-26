package twoPointers.easy;

// https://leetcode.com/problems/remove-duplicates-from-sorted-array/description/
public class RemoveDuplicatesFromSortedArray_26 {
    // solution1
    public int removeDuplicates1(int[] nums) {
        // time O(n)
        // space O(1)

        int insertInd = 1;
        for(int i = 1; i < nums.length; i++) {
            if(nums[i] != nums[i - 1]) {
                nums[insertInd] = nums[i];
                insertInd++;
            }
        }

        return insertInd;
    }

    // solution2
    public int removeDuplicates2(int[] nums) {
        // time O(n)
        // space O(1)

        int lastCorrectInd = 0;
        for(int i = 1; i < nums.length; i++) {
            if(nums[i] != nums[lastCorrectInd]) {
                lastCorrectInd++;
                nums[lastCorrectInd] = nums[i];
            }
        }

        return lastCorrectInd + 1;
    }
}
