package arraysHashing;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

// https://leetcode.com/problems/make-array-empty/
public class MakeArrayEmpty_2659 {

    // solution: https://leetcode.com/problems/make-array-empty/discuss/3466800/JavaC%2B%2BPython-Easy-Sort-Solution
    public long countOperationsToEmptyArray(int[] nums) {
        // time O(nlogn)
        // space O(n)

        // map val -> index
        // sort arr
        // if (indOfPrevVal > indOfCurrVal)
        //       res += n - i;

        // [4, 3, 2, 1]
        // map: {4 -> 0, 3 -> 1, 2 -> 2, 1 -> 0}
        int n = nums.length;
        Map<Integer, Integer> map = new HashMap<>();
        for(int i = 0; i < n; i++)
            map.put(nums[i], i);

        Arrays.sort(nums);

        // sorted: [1, 2, 3, 4]
        long steps = n;
        for(int i = 1; i < n; i++) {
            int indOfPrevVal = map.get(nums[i - 1]); // 3
            int indOfCurrVal = map.get(nums[i]); // 2

            if (indOfPrevVal > indOfCurrVal)
                steps += n - i; // 8 - 1 = 7
        }

        return steps;
    }

    public static void main(String[] args) {
        MakeArrayEmpty_2659 makeArrayEmpty_2659 = new MakeArrayEmpty_2659();
        makeArrayEmpty_2659.countOperationsToEmptyArray(new int[]{4, 3, 2, 1});
    }
}
