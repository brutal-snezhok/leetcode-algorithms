package arraysHashing;

// https://leetcode.com/problems/largest-number-at-least-twice-of-others/
public class LargestNumberAtLeastTwiceOfOthers_747 {
    public static int dominantIndex(int[] nums) {
        // time O(n)
        // space O(1)

        // find max
        // check condition for every el

        int max = nums[0];
        int ind = 0;
        for(int i = 1; i < nums.length; i++) {
            if(max < nums[i]) {
                max = nums[i];
                ind = i;
            }
        }

        for(int i = 0; i < nums.length; i++) {
            if(nums[i] != 0 && i != ind && max / nums[i] < 2)
                return -1;
        }

        return ind;
    }

    public static void main(String[] args) {
        dominantIndex(new int[]{3,6,1,0});
    }
}
