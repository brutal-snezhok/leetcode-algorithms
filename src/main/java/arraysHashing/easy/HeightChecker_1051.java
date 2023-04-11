package arraysHashing.easy;

import java.util.Arrays;

// https://leetcode.com/problems/height-checker/
public class HeightChecker_1051 {

    // solution1
    public int heightChecker1(int[] heights) {
        // time O(nlogn)
        // space O(n)

        int res = 0;
        int[] newArr = Arrays.copyOf(heights, heights.length);
        Arrays.sort(newArr);

        for (int i = 0; i < heights.length; i++) {
            if (heights[i] != newArr[i])
                res++;
        }

        return res;
    }

    // solution2
    public static int heightChecker2(int[] heights) {
        // count sort
        // time O(n)
        // space O(1)

        int[] counts = new int[101];

        for(int num : heights)
            counts[num]++;

        int res = 0;
        int currH = 0;

        for (int height : heights) {
            while (counts[currH] == 0)
                currH++;

            if (currH != height)
                res++;

            counts[currH]--;
        }

        return res;
    }

    public static void main(String[] args) {
        heightChecker2(new int[]{1,1,4,2,1,3});
    }
}
