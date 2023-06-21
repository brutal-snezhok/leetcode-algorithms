package arraysHashing.cyclic_sort;

// https://leetcode.com/problems/first-missing-positive/description/
public class FirstMissingPositive_41 {
    // solution1
    public int firstMissingPositive1(int[] nums) {
        // cyclic sort
        // time O(n)
        // space O(1)

        int i = 0;
        int n = nums.length;
        while(i < n) {
            if(nums[i] <= 0 || nums[i] > n) {
                i++;
                continue;
            }

            int j = nums[i] - 1;
            if(nums[i] != nums[j])
                swap(nums, i, j);
            else
                i++;
        }

        for(i = 0; i < n; i++)
            if(nums[i] - 1 != i)
                return i + 1;

        return n + 1;
    }

    private void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }

    // solution2
    public int firstMissingPositive(int[] nums) {
        // time O(n)
        // space O(1)

        int n = nums.length;

        // 1. mark numbers (num < 0) and (num > n) with a special marker number (n+1)
        for(int i = 0; i < n; i++) {
            if(nums[i] <= 0 || nums[i] > n)
                nums[i] = n + 1;
        }
        // note: all number in the array are now positive, and on the range 1..n+1

        // 2. mark each cell appearing in the array, by converting the index for that number to negative
        for(int i = 0; i < n; i++) {
            int absNum = Math.abs(nums[i]);
            if(absNum > n)
                continue;

            int ind = absNum - 1;
            if(nums[ind] > 0)
                nums[ind] *= -1;

        }

        // 3. find the first cell which isn't negative (doesn't appear in the array)
        for(int i = 0; i < n; i++) {
            if(nums[i] > 0)
                return i + 1;
        }

        return n + 1;
    }
}
