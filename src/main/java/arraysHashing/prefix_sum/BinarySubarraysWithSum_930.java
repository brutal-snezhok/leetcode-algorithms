package arraysHashing.prefix_sum;

// https://leetcode.com/problems/binary-subarrays-with-sum/description/
public class BinarySubarraysWithSum_930 {
    // solution1
    public int numSubarraysWithSum1(int[] nums, int goal) {
        // sliding window
        // time O(n)
        // space O(1)

        int l = 0;
        int currSum = 0;
        int res = 0;

        for(int r = 0; r < nums.length; r++) {
            currSum += nums[r];

            while(currSum > goal && l < r) {
                currSum -= nums[l];
                l++;
            }

            while(currSum == goal) {
                res++;
                l++;
                currSum -= nums[r];
                r--;
            }
        }

        return res;
    }

    // solution2
    public int numSubarraysWithSum2(int[] nums, int goal) {
        // TODO: check this solution again
        // sliding window atMost
        // time O(n)
        // space O(1)

        return atMost(nums, goal) - atMost(nums, goal - 1);
    }

    private int atMost(int[] nums, int goal) {
        if(goal < 0)
            return 0;

        int res = 0;
        int l = 0;
        for(int r = 0; r < nums.length; r++) {
            goal -= nums[r];

            while(goal < 0) {
                goal += nums[l];
                l++;
            }

            res += r - l + 1;
        }

        return res;
    }
}
