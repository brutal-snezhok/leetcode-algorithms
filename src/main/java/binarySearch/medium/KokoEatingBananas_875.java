package binarySearch.medium;

// https://leetcode.com/problems/koko-eating-bananas/description/
public class KokoEatingBananas_875 {
    public int minEatingSpeed(int[] piles, int h) {
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
}
