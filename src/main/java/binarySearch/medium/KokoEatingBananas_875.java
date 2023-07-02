package binarySearch.medium;

// https://leetcode.com/problems/koko-eating-bananas/description/
public class KokoEatingBananas_875 {
    // solution1
    public int minEatingSpeed1(int[] piles, int h) {
        // time O(nlogm), n - number of piles, m - max of piles[i]
        // space O(1)

        // find max of piles[i]
        // do binary search on 1 to max of piles and count curr hours and find min from them

        int maxP = Integer.MIN_VALUE;
        for(int pile : piles)
            maxP = Math.max(maxP, pile);

        int l = 1;
        int r = maxP;
        int res = Integer.MAX_VALUE;
        while(l <= r) {
            int mid = l + (r - l) / 2; // number of bananas per hour

            int currH = 0;
            for(int pile : piles)
                currH += Math.ceil(pile / (double) mid);

            if(currH <= h) {
                res = Math.min(mid, res);
                r = mid - 1;
            } else
                l = mid + 1;
        }

        return res;
    }

    // solution2
    public int minEatingSpeed2(int[] piles, int h) {
        // time O(nlogm), n - number of piles, m - max of piles[i]
        // space O(1)

        /*
            1. l = 1, r = max
            2. binarysearch
        */

        int l = 1;
        int r = Integer.MIN_VALUE;
        for(int pile : piles)
            r = Math.max(r, pile);

        while(l < r) {
            int mid = l + (r - l) / 2;

            if(isPossible(piles, h, mid))
                r = mid;
            else
                l = mid + 1;
        }

        return l;
    }

    private boolean isPossible(int[] nums, int h, int max) {
        int currH = 0;
        for(int num : nums) {
            currH += Math.ceil(num / (double) max);
            if(currH > h)
                return false;
        }

        return true;
    }
}
