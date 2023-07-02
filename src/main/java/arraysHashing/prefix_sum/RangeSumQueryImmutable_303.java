package arraysHashing.prefix_sum;

// https://leetcode.com/problems/range-sum-query-immutable/description/
public class RangeSumQueryImmutable_303 {
    int n;
    int[] prefixSum;

    public RangeSumQueryImmutable_303(int[] nums) {
        // time O(n) precomputation
        // space O(n)
        n = nums.length;
        prefixSum = new int[n];
        prefixSum[0] = nums[0];

        for(int i = 1; i < n; i++)
            prefixSum[i] = prefixSum[i - 1] + nums[i];
    }

    public int sumRange(int left, int right) {
        // time O(1)
        // space O(1)

        return prefixSum[right] - (left == 0 ? 0 : prefixSum[left - 1]);
    }
}
