package twoPointers.medium;

// https://leetcode.com/problems/sort-colors/description/
public class SortColors_75 {
    // solution1
    public void sortColors1(int[] nums) {
        // 1 pass
        // time O(n)
        // space O(1)

        int l = 0;
        int r = nums.length - 1;
        int i = 0;

        while(i <= r) {
            if(nums[i] == 0) {
                swap(nums, l, i);
                l++;
                i++;
            } else if(nums[i] == 2) {
                swap(nums, r, i);
                r--;
            } else
                i++;
        }
    }

    private void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }

    // solution2
    public void sortColors2(int[] nums) {
        // 2 passes
        // time O(n)
        // space O(1)

        int zeroCount = 0;
        int oneCount = 0;
        int twoCount = 0;

        for (int num : nums) {
            if (num == 0)
                zeroCount++;
            else if(num == 1)
                oneCount++;
            else
                twoCount++;
        }

        for (int i = 0; i < zeroCount; i++)
            nums[i] = 0;
        for (int i = zeroCount; i < zeroCount+oneCount; i++)
            nums[i] = 1;
        for (int i = zeroCount+oneCount; i < zeroCount+oneCount+twoCount; i++)
            nums[i] = 2;
    }
}
