package binarySearch.easy;

// https://leetcode.com/problems/valid-perfect-square/description/
public class ValidPerfectSquare_367 {
    // solution1
    public boolean isPerfectSquare1(int num) {
        // time O(logn)
        // space O(1)

        int l = 1;
        int r = Integer.MAX_VALUE;
        while(l < r) {
            int mid = l + (r - l) / 2;

            if(mid >= num / mid)
                r = mid;
            else
                l = mid + 1;
        }

        return l * l == num;
    }

    // solution2
    public boolean isPerfectSquare2(int num) {
        // time O(logn)
        // space O(1)

        int l = 1;
        int r = Integer.MAX_VALUE;

        while(l <= r) {
            int mid = l + (r - l) / 2;
            long pow = mid * (long)mid; // mid * mid will cause overflow, that why we need to write mid *(long)mid

            if(pow == num)
                return true;
            else if(pow < num)
                l = mid + 1;
            else
                r = mid - 1;
        }

        return false;
    }
}
