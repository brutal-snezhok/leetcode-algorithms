package mathGeometry.medium;

import java.util.Arrays;

// https://leetcode.com/problems/movement-of-robots/description/
public class MovementOfRobots_2731 {
    // TODO : check solutions with prefix sum
    public int sumDistance(int[] nums, String s, int d) {
        // time O(nlogn)
        // space O(1)

        int n = nums.length;

        for(int i = 0; i < n; i++) {
            if(s.charAt(i) == 'R')
                nums[i] +=  d;
            else
                nums[i] -= d;
        }

        final int MOD = 1_000_000_007;
        Arrays.sort(nums);
        long sum = 0;
        /**
         Let's take a look on the example {1,2,3}. All pairs sums will be:
         (1,2) + (1,3) + (2,3) = 2 - 1 + 3 - 1 + 3 - 2. So, we use each number in the array n - 1 times.
         We substtract the number if it is less in the pair (i, j),
         once array is sorrted it means i number will be bigger (added to sum) i times and less
         (substructed from the sum) n - 1 - i times.
         */
        for(int i = 0; i < n; i++) {
            sum += i * (long)nums[i] - (n - 1 - i) * (long)nums[i];;
            sum %= MOD;
        }

        return (int)sum;
    }
}
