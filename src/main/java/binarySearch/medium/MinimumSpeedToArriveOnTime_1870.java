package binarySearch.medium;

// https://leetcode.com/problems/minimum-speed-to-arrive-on-time/
public class MinimumSpeedToArriveOnTime_1870 {
    public int minSpeedOnTime(int[] dist, double hour) {
        // time O(nlogk), k in [1, 10^7]
        // space O(1)

        int l = 1;
        int r = 10000001; // use 10^7 + 1 because answer can be 10^7. [l,r) should contains all answers
        int res = -1;

        while(l < r) {
            int mid = l + (r - l) / 2;

            if(isFit(dist, hour, mid)) {
                res = mid;
                r = mid;
            }
            else
                l = mid + 1;
        }

        return res;
    }

    private boolean isFit(int[] dist, double h, int v) {
        double currTime = 0.0;

        for(int i = 0; i < dist.length; i++) {
            double t = (double)dist[i] / (double)v;
            currTime += (i == dist.length - 1) ? t : Math.ceil(t);
        }

        return currTime <= h;
    }

    public static void main(String[] args) {
        MinimumSpeedToArriveOnTime_1870 minimum = new MinimumSpeedToArriveOnTime_1870();
        minimum.minSpeedOnTime(new int[]{1,1,100000}, 2.01);
    }
}
