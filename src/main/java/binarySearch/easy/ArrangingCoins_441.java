package binarySearch.easy;

// https://leetcode.com/problems/arranging-coins/description/
public class ArrangingCoins_441 {
    public int arrangeCoins(int n) {
        // time O(logn)
        // space O(1)

        long l = 0;
        long r = n;
        long k, curr;
        while(l <= r) {
            k = l + (r - l) / 2;
            curr = k * (k + 1) / 2;

            if(curr == n)
                return (int)k;
            else if(curr < n)
                l = k + 1;
            else
                r = k - 1;
        }

        return (int)r;
    }
}
