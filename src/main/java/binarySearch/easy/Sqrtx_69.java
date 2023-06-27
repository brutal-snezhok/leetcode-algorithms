package binarySearch.easy;

// https://leetcode.com/problems/sqrtx/description/
public class Sqrtx_69 {
    // solution1
    public static int mySqrt1(int x) {
        // time O(logn)
        // space O(1)

        long l = 0;
        long r = (long)x + 1; // to deal with edge cases x = 0 and x = 1
        while(l < r) {
            long mid = l + (r - l) / 2;

            if(mid * mid > x)
                r = mid;
            else
                l = mid + 1;
        }

        return (int)l - 1;
    }

    // solution2
    public int mySqrt2(int x) {
        if(x == 0) return 0;

        int l = 1;
        int r = x;

        while(l <= r) {
            int mid = (l + r) / 2;

            if(mid <= x / mid && mid+1 > x / (mid+1))
                return mid;
            else if(mid > x / mid)
                r = mid - 1;
            else
                l = mid + 1;
        }

        return l;
    }

    public static void main(String[] args) {
        mySqrt1(8);
    }
}
