package graphs.medium;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

// https://leetcode.com/problems/pacific-atlantic-water-flow/description/
public class PacificAtlanticWaterFlow_417 {
    public List<List<Integer>> pacificAtlantic(int[][] heights) {
        // time O(n*m)
        // space O(n*m)

        // find all cells from each can be reachable pacific ocean
        // find all cells from each can be reachable atlantic ocean

        List<List<Integer>> res = new ArrayList<>();
        final int ROWS = heights.length;
        final int COLS = heights[0].length;

        boolean[][] pacific = new boolean[ROWS][COLS];
        boolean[][] atlantic = new boolean[ROWS][COLS];

        for (int c = 0; c < COLS; c++)
            dfs(heights, pacific, 0, c, 0, c); // start search cells from first row, since all cells from this row can reach pacific ocean
        for (int r = 0; r < ROWS; r++) // do search for first column
            dfs(heights, pacific, r, 0, r, 0);
        for (int c = 0; c < COLS; c++) // do search for last row and atlantic ocean
            dfs(heights, atlantic, ROWS - 1, c, ROWS - 1, c);
        for (int r = 0; r < ROWS; r++) // do search for last column
            dfs(heights, atlantic, r, COLS - 1, r, COLS - 1);

        for (int r = 0; r < ROWS; r++) {
            for (int c = 0; c < COLS; c++) {
                if (pacific[r][c] && atlantic[r][c]) // if from curr cell water can reach pacific and atlantic -> then add cell to the result
                    res.add(Arrays.asList(r, c));
            }
        }

        return res;
    }

    private void dfs(int[][] heights, boolean[][] visited, int r, int c, int prevR, int prevC) {
        if (r < 0 || r >= heights.length || c < 0 || c >= heights[0].length || visited[r][c]
                || heights[r][c] < heights[prevR][prevC])
            return;

        visited[r][c] = true;
        dfs(heights, visited, r + 1, c, r, c);
        dfs(heights, visited, r - 1, c, r, c);
        dfs(heights, visited, r, c + 1, r, c);
        dfs(heights, visited, r, c - 1, r, c);
    }
}
