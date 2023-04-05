package arraysHashing.easy;

// https://leetcode.com/problems/max-consecutive-ones/
public class MaxConsecutiveOnes_485 {
    public int findMaxConsecutiveOnes(int[] nums) {
        // time O(n)
        // space O(1)

        int res = 0;
        int currL = 0;

        for(int num : nums) {
            if(num == 1)
                currL++;
            else {
                res = Math.max(res, currL);
                currL = 0;
            }
        }

        return Math.max(res, currL);
    }
}
