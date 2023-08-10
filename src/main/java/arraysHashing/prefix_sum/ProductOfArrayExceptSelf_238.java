package arraysHashing.prefix_sum;

// https://leetcode.com/problems/product-of-array-except-self/description/
public class ProductOfArrayExceptSelf_238 {
    // solution1
    public int[] productExceptSelf1(int[] nums) {
        // time O(n)
        // space O(n)

        /**
         for each index of arr find product that is lefter and righter
         1. create prefix & suffix product arr
         2. res will be product of them
         */

        int n = nums.length;
        int[] prefix = new int[n];
        int[] suffix = new int[n];
        prefix[0] = 1;
        suffix[n - 1] = 1;

        for(int i = 1; i < nums.length; i++)
            prefix[i] = prefix[i - 1] * nums[i - 1];

        for(int i = n - 2; i >= 0; i--)
            suffix[i] = suffix[i + 1] * nums[i + 1];

        int[] res = new int[n];
        for(int i = 0; i < n; i++)
            res[i] = prefix[i] * suffix[i];

        return res;
    }

    // solution2
    public int[] productExceptSelf2(int[] nums) {
        // time O(n)
        // space O(1)

        /**
         1. compute prefix to res (1 pass)
         2. then go backword and multiply elements by suffix var
         */

        int n = nums.length;
        int[] res = new int[n];
        res[0] = 1;

        for(int i = 1; i < n; i++)
            res[i] = res[i - 1] * nums[i - 1];

        int suffix = 1;
        for(int i = n - 1; i >= 0; i--) {
            res[i] = res[i] * suffix;
            suffix *= nums[i];
        }

        return res;
    }
}
