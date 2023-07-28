package dp.medium.game_theory;

import java.util.Arrays;

// https://leetcode.com/problems/predict-the-winner/description/
public class PredictTheWinner_486 {
    // solution1
    public boolean predictTheWinner(int[] nums) {
        // brute force
        // time O(2^n)
        // space O(n)

        return maxDiff(nums, 0, nums.length - 1) >= 0;
    }

    private int maxDiff(int[] nums, int l, int r) {
        if(l == r)
            return nums[l];

        int leftScore = nums[l] - maxDiff(nums, l + 1, r);
        int rightScore = nums[r] - maxDiff(nums, l, r - 1);

        return Math.max(leftScore, rightScore);
    }

    public static void main(String[] args) {
        PredictTheWinner_486 predict = new PredictTheWinner_486();
        predict.predictTheWinner(new int[]{1,5,233,7});
    }

    // solution2
    public boolean PredictTheWinner(int[] nums) {
        // top down
        // time O(n*n)
        // space O(n*n)

        int n = nums.length;
        int[][] memo = new int[n][n];
        for(int[] row : memo)
            Arrays.fill(row, -1);

        return maxDiff(nums, 0, nums.length - 1, memo) >= 0;
    }

    private int maxDiff(int[] nums, int l, int r, int[][] memo) {
        if(memo[l][r] != -1)
            return memo[l][r];

        if(l == r)
            return nums[l];

        int leftScore = nums[l] - maxDiff(nums, l + 1, r, memo);
        int rightScore = nums[r] - maxDiff(nums, l, r - 1, memo);

        return memo[l][r] = Math.max(leftScore, rightScore);
    }
}
