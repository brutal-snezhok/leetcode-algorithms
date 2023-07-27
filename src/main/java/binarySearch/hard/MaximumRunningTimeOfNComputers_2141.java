package binarySearch.hard;

import java.util.Arrays;

// https://leetcode.com/problems/maximum-running-time-of-n-computers/description/
public class MaximumRunningTimeOfNComputers_2141 {
    // solution1
    public long maxRunTime1(int n, int[] batteries) {
        // time O(mlogm), m = batteries.length
        // space O(max(n,m)) m for sorting, n for arr of computers

        Arrays.sort(batteries);

        int[] computers = new int[n];
        System.arraycopy(batteries, batteries.length - n, computers, 0, n);
        long extra = 0L;
        for(int i = 0; i < batteries.length - n; i++)
            extra += batteries[i];

        for(int i = 0; i < n - 1; i++) {
            int diff = computers[i + 1] - computers[i];

            if(extra < (long)(i + 1) * diff)
                return computers[i] + extra / (long)(i + 1);

            extra -= (long)(i + 1) * diff;
        }

        return computers[n - 1] + extra / n;
    }

    // solution2
    public long maxRunTime2(int n, int[] batteries) {
        // binary search
        // time O(mlogk), m = batteries.length, k - max power of battery
        // space O(1)

        long sum = 0L;
        for(int bat : batteries)
            sum += bat;

        long l = 1L;
        long r = sum / n;
        while(l < r) {
            // long mid = l + (r - l) / 2; why TLE here?
            long mid = r - (r - l) / 2;

            if(isFit(batteries, n, mid))
                l = mid; // to find max and not min time
            else
                r = mid - 1;
        }

        return l;
    }

    private boolean isFit(int[] batteries, int n, long target) {
        long extra = 0L;

        for(int bat : batteries)
            extra += Math.min(bat, target);

        return extra >= (long)n * target;
    }
}
