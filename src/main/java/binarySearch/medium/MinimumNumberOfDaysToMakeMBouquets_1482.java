package binarySearch.medium;

// https://leetcode.com/problems/minimum-number-of-days-to-make-m-bouquets/description/
public class MinimumNumberOfDaysToMakeMBouquets_1482 {
    // solution1
    public int minDays(int[] bloomDay, int m, int k) {
        // time O(nlogd) d - max num of day
        // space O(1)

        if(bloomDay.length < (long)m * k)
            return -1;

        int l = 1;
        int r = (int)1e9;
        //int r = Integer.MIN_VALUE;
        // for(int day : bloomDay)
        //     r = Math.max(r, day);

        while(l < r) {
            int mid = (r - l) / 2 + l;

            if(isPossible(bloomDay, m, k, mid))
                r = mid;
            else
                l = mid + 1;
        }

        return l;
    }

    private boolean isPossible(int[] bloomDay, int m, int k, int day) {
        int numBuq = 0;
        int flowers = 0;
        for(int bloomD : bloomDay) {
            if(bloomD > day) {
                flowers = 0;
                continue;
            }

            flowers += 1;
            if(flowers == k) {
                numBuq++;
                flowers = 0;
            }
        }

        return numBuq >= m;
    }
}
