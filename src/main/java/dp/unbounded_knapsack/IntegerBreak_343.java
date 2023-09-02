package dp.unbounded_knapsack;

// https://leetcode.com/problems/integer-break/description/
public class IntegerBreak_343 {
    int res;

    public int integerBreak(int n) {
        // time O(2^n)
        // space O(n)

        res = 1;
        backtracking(n, 1, 0, 1);
        return res;
    }

    private void backtracking(int n, int ind, int currSum, int currProduct) {
        if(currSum == n) {
            res = Math.max(res, currProduct);
            return;
        }
        if(currSum > n || ind >= n)
            return;

        // take
        if(currSum + ind <= n)
            backtracking(n, ind, currSum + ind, currProduct * ind);

        // not take
        backtracking(n, ind + 1, currSum, currProduct);
    }
}
