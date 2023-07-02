package binarySearch.medium;

// https://leetcode.com/problems/capacity-to-ship-packages-within-d-days/description/
public class CapacityToShipPackagesWithinDDays_1011 {
    // solution1
    public int shipWithinDays1(int[] weights, int days) {
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

    // solution2
    public int shipWithinDays2(int[] weights, int days) {
        // time O(log(sum(weights)) * n)
        // space O(1)

        int l = Integer.MIN_VALUE;
        int r = 0;

        for(int w : weights) {
            r += w;
            l = Math.max(l, w);
        }

        while(l < r) {
            int mid = l + (r - l) / 2;

            if(isShipped(weights, days, mid))
                r = mid;
            else
                l = mid + 1;
        }

        return l;
    }

    private boolean isShipped(int[] weights, int days, int capacity) {
        // time O(n)
        // space O(1)

        int sum = 0;
        int d = 1; // how many days needed
        for(int w : weights) {
            sum += w;
            if(sum > capacity) {
                sum = w;
                d++;

                if(d > days)
                    return false;
            }
        }

        return true;
    }
}
