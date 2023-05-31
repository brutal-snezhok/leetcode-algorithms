package graphs.medium;

// https://leetcode.com/problems/number-of-closed-islands/description
public class NumberOfClosedIslands_1254 {
    // solution1
    int[][] directions = { {1, 0}, {-1, 0}, {0, 1}, {0, -1} };

    public int closedIsland(int[][] grid) {
        // dfs
        // time O(n*m)
        // space O(n*m)
        int res = 0;
        final int ROWS = grid.length;
        final int COLS = grid[0].length;

        if(ROWS < 2 || COLS < 2)
            return res;

        boolean[][] visited = new boolean[ROWS][COLS];

        // run dfs on first and last rows
        for(int r = 0; r < ROWS; r += ROWS - 1)
            for(int c = 0; c < COLS; c++)
                if(!visited[r][c] && grid[r][c] == 0)
                    dfs(grid, r, c, visited);

        // run dfs on first and last cols
        for(int c = 0; c < COLS; c += COLS - 1)
            for(int r = 0; r < ROWS; r++)
                if(!visited[r][c] && grid[r][c] == 0)
                    dfs(grid, r, c, visited);


        for(int r = 1; r < ROWS; r++)
            for(int c = 1; c < COLS; c++)
                if(!visited[r][c] && grid[r][c] == 0) {
                    dfs(grid, r, c, visited);
                    res++;
                }


        return res;
    }

    private void dfs(int[][] grid, int r, int c, boolean[][] visited) {
        final int ROWS = grid.length;
        final int COLS = grid[0].length;

        if(r >= ROWS || r < 0 || c >= COLS || c < 0 || visited[r][c] || grid[r][c] == 1)
            return;

        visited[r][c] = true;
        for(int[] dir : directions)
            dfs(grid, r + dir[0], c + dir[1], visited);
    }
}
