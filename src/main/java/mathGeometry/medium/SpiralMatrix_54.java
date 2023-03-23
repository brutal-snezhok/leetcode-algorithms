package mathGeometry.medium;

import java.util.ArrayList;
import java.util.List;

// https://leetcode.com/problems/spiral-matrix/
public class SpiralMatrix_54 {
    public List<Integer> spiralOrder(int[][] matrix) {
        // time O(m * m)
        // space O(1), if don't count res

        // go from left to right, t++
        // go from up to down, r--
        // got from right to left, b--
        // go from down to up, l++

        List<Integer> res = new ArrayList<>();
        int t = 0;
        int b = matrix.length - 1;
        int l = 0;
        int r = matrix[0].length - 1;

        while (t <= b && l <= r) {
            // from l to r
            for (int i = l; i <= r; i++)
                res.add(matrix[t][i]);
            t++;
            // from up to down
            for (int i = t; i <= b; i++)
                res.add(matrix[i][r]);
            r--;

            // [1, 2, 3], cases with only one row or column
            // t = 0; b = 0
            // l = 0; r = 2
            if (t > b || l > r)
                break;

            // from r to l
            for (int i = r; i >= l; i--)
                res.add(matrix[b][i]);
            b--;

            // from b to t
            for (int i = b; i >= t; i--)
                res.add(matrix[i][l]);
            l++;
        }

        return res;
    }
}
