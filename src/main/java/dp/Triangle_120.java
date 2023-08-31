package dp;

import java.util.Arrays;
import java.util.List;

// https://leetcode.com/problems/triangle
public class Triangle_120 {
    // solution1
    public int minimumTotal1(List<List<Integer>> triangle) {
        // in place bottom-up approach
        // time O(n^2), n - total num of rows in triangle
        // space O(1)

        int size = triangle.size();
        for(int i = 1; i < size; i++) {
            List<Integer> prevRow = triangle.get(i - 1);
            List<Integer> currRow = triangle.get(i);
            for(int j = 0; j <= i; j++) {
                int val1 = j < prevRow.size() ? prevRow.get(j) : prevRow.get(prevRow.size() - 1);
                int val2 = j - 1 >= 0 ? prevRow.get(j - 1) : prevRow.get(0);
                int currVal = currRow.get(j);

                currRow.set(j, Math.min(val1 + currVal, val2 + currVal));
            }
        }

        return triangle.get(size - 1).stream().mapToInt(v -> v).min().getAsInt();
    }

    // solution2
    public int minimumTotal2(List<List<Integer>> triangle) {
        // without mod input
        // time O(n^2), n - total num of rows in triangle
        // space O(n)

        int size = triangle.size();
        int[] dp = new int[size + 1];
        for(int j = size - 1; j >= 0; j--) {
            List<Integer> list = triangle.get(j);
            for(int i = 0; i < list.size(); i++) {
                dp[i] = list.get(i) + Math.min(dp[i], dp[i + 1]);
            }
        }

        return dp[0];
    }

    public static void main(String[] args) {
        Triangle_120 triangle = new Triangle_120();
        triangle.minimumTotal1(Arrays.asList(Arrays.asList(2), Arrays.asList(3, 4),
                Arrays.asList(6, 5, 7), Arrays.asList(4, 1, 8, 3)));
    }
}
