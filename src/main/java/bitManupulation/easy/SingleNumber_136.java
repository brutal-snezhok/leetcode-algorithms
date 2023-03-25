package bitManupulation.easy;

// https://leetcode.com/problems/single-number/
public class SingleNumber_136 {
    public int singleNumber(int[] nums) {
        // time O(n)
        // space O(1)

        int res = 0;
        for(int num : nums)
            res ^= num;

        return res;
    }
}
