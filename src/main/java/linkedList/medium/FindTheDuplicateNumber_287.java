package linkedList.medium;

// https://leetcode.com/problems/find-the-duplicate-number/description/
public class FindTheDuplicateNumber_287 {
    public int findDuplicate(int[] nums) {
        // time O(n)
        // space O(1)

        int s = nums[0];
        int f = nums[0];

        while (true) {
            s = nums[s];
            f = nums[nums[f]];

            if (s == f) {
                int secondSlow = nums[0];
                while (secondSlow != s) {
                    s = nums[s];
                    secondSlow = nums[secondSlow];
                }

                return s;
            }
        }
    }
}
