package binarySearch.easy;

// https://leetcode.com/problems/first-bad-version/
public class FirstBadVersion_278 {
    public int firstBadVersion(int n) {
        // time O(logn)
        // space O(1)

        int l = 1;
        int r = n;

        while(l < r) {
            int mid = l + (r - l) / 2;

            if(isBadVersion(mid)) {
                r = mid;
            } else
                l = mid + 1;
        }

        return l;
    }

    // some API
    private boolean isBadVersion(int version) {
        return version > 5;
    }
}
