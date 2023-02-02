package binarySearch.medium;

// https://leetcode.com/problems/capacity-to-ship-packages-within-d-days/description/
public class CapacityToShipPackagesWithinDDays_1011 {
    public int shipWithinDays(int[] weights, int days) {
        // time O(nlogn)
        // space O(1)

        // do binary search where l = maxW, r = sum(weight[i])
        // find min capacity

        int maxW = Integer.MIN_VALUE;
        int sumW = 0;
        for(int weight : weights) {
            sumW += weight;
            maxW = Math.max(maxW, weight);
        }

        int l = maxW;
        int r = sumW;
        int res = Integer.MAX_VALUE;
        while(l <= r) {
            int mid = (l + r) / 2;

            int needDays = 1;
            int currCap = 0;
            for(int weight : weights) {
                if(currCap + weight > mid) {
                    needDays++;
                    currCap = 0;
                }
                currCap += weight;
            }

            if(needDays > days)
                l = mid + 1;
            else {
                res = Math.min(res, mid);
                r = mid - 1;
            }
        }

        return res;
    }
}
