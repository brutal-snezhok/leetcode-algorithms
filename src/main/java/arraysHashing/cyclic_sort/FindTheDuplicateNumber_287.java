package arraysHashing.cyclic_sort;

// https://leetcode.com/problems/find-the-duplicate-number/
public class FindTheDuplicateNumber_287 {
    // TODO: check binary search solution
    // solution1
    public int findDuplicate1(int[] nums) {
        // cyclic sort
        // time O(n)
        // space O(1)

        int n = nums.length;
        int i = 0;
        while(i < n) {
            int j = nums[i] - 1;
            if(nums[i] != nums[j])
                swap(nums, i, j);
            else
                i++;
        }

        for(i = 0; i < n; i++)
            if(nums[i] != i + 1)
                return nums[i];

        return -1;
    }

    private void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }

    // solution2
    public int findDuplicate2(int[] nums) {
        // fast, slow pointers
        // time O(n)
        // space O(1)

        int s = nums[0];
        int f = nums[0];

        while(true) {
            s = nums[s];
            f = nums[nums[f]];

            if(s == f) {
                int secondSlow = nums[0];
                while(secondSlow != s) {
                    s = nums[s];
                    secondSlow = nums[secondSlow];
                }

                return s;
            }
        }
    }
}
