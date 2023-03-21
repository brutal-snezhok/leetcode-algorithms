package mathGeometry.easy;

import java.util.ArrayList;
import java.util.List;

// https://leetcode.com/problems/shift-2d-grid/description/
public class Shift2DGrid_1260 {
    public List<List<Integer>> shiftGrid(int[][] grid, int k) {
        // time O(n*m)
        // space O(n*m), if count res list

        final int m = grid.length;
        final int n = grid[0].length;

        List<List<Integer>> res = new ArrayList<>(m);
        // initialize res list, to prevent indexOutOfBoundExc here set(newColumn, grid[r][c])
        for (int i = 0; i < m; i++) {
            List<Integer> list = new ArrayList<>();
            for (int j = 0; j < n; j++) {
                list.add(0);
            }
            res.add(list);
        }

        for (int r = 0; r < m; r++) {
            for (int c = 0; c < n; c++) {
                int newInd = (posToInd(r, c, n) + k) % (m * n);
                int newR = newInd / n;
                int newC = newInd % n;
                res.get(newR).set(newC, grid[r][c]);
            }
        }


        return res;
    }

    private int posToInd(int r, int c, int COLS) {
        return r * COLS + c;
    }
}
