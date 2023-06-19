package arraysHashing.easy;

// https://leetcode.com/problems/missing-number/description/
public class MissingNumber_268 {
    // solution1
    public int missingNumber1(int[] nums) {
        // cyclic sort
        // time O(n)
        // space O(1)

        int n = nums.length;
        int i = 0;
        while(i < n) {
            if(nums[i] < n && nums[i] != i)
                swap(nums, i, nums[i]);
            else
                i++;
        }

        for(i = 0; i < n; i++)
            if(nums[i] != i)
                return i;

        return n;
    }

    private void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }

    // solution2
    public int missingNumber2(int[] nums) {
        // calculate sum
        // time O(n)
        // space O(1)

        int n = nums.length;
        int expected = (0 + n) * (n + 1) / 2; // or can calculate with loop from 0 to n
        int actual = 0;
        for (int num : nums)
            actual += num;

        return expected - actual;
    }

    // solution3
    public int missingNumber3(int[] nums) {
        // using xor
        // time O(n)
        // space O(1)

        int n = nums.length;
        int res = 0;
        for(int i = 0; i < n; i++)
            res = res ^ i ^ nums[i];

        res ^= n;

        return res;
    }

    public static void main(String[] args) {
        MissingNumber_268 missing = new MissingNumber_268();
        missing.missingNumber1(new int[]{9,6,4,2,3,5,7,0,1});
    }
}
