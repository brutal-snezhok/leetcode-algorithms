package arraysHashing.easy;

// https://leetcode.com/problems/teemo-attacking/description/
public class TeemoAttacking_495 {
    public int findPoisonedDuration(int[] timeSeries, int duration) {
        // time O(n)
        // space O(1)

        // res += duration
        // end = nums[i], currEnd = nums[i] + duration - 1
        // overlapping = end >= nums[i] ? end - nums[i] + 1 : 0
        // res += duration -overlapping

        int end = -1;
        int res = 0;
        int overlapping;

        for (int timeSery : timeSeries) {
            int currEnd = timeSery + duration - 1;

            overlapping = end >= timeSery ? end - timeSery + 1 : 0;
            res += duration - overlapping;
            end = currEnd;
        }

        return res;
    }
}
