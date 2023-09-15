package twoPointers.medium;

// https://leetcode.com/problems/container-with-most-water/description/
public class ContainerWithMostWater_11 {
    // solution1
    public int maxArea1(int[] height) {
        // time O(n)
        // space O(1)

        int l = 0;
        int r = height.length - 1;
        int res = 0;

        while(l < r) {
            int currVol = (r - l) * Math.min(height[l], height[r]);
            res = Math.max(currVol, res);
            if(height[l] < height[r])
                l++;
            else
                r--;
        }

        return res;
    }
}
