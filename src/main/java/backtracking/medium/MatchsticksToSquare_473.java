package backtracking.medium;

import java.util.Arrays;
import java.util.Comparator;

// https://leetcode.com/problems/matchsticks-to-square/description/
public class MatchsticksToSquare_473 {
    public boolean makesquare(int[] matchsticks) {
        // time O(4^n), on each level have only 4 choices and n levels
        // space O(n), n will be height of decision tree

        int sum = 0;
        for (int num : matchsticks)
            sum += num;

        if (sum % 4 != 0)
            return false;

        Arrays.sort(matchsticks);
        reverse(matchsticks);
        int length = sum / 4;
        int[] sides = new int[4];

        return backtracking(matchsticks, length, sides, 0);
    }

    private void reverse(int[] arr) {
        int l = 0;
        int r = arr.length - 1;
        while (l <= r) {
            int temp = arr[l];
            arr[l] = arr[r];
            arr[r] = temp;
            l++;
            r--;
        }
    }

    private boolean backtracking(int[] matchsticks, int length, int[] sides, int ind) {
        if (ind == matchsticks.length)
            return true;

        for (int i = 0; i < 4; i++) {
            if (sides[i] + matchsticks[ind] <= length) {
                sides[i] += matchsticks[ind];
                if (backtracking(matchsticks, length, sides, ind + 1))
                    return true;

                sides[i] -= matchsticks[ind];
            }
        }

        return false;
    }
}
