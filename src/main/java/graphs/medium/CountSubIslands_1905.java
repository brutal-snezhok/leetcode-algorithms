package graphs.medium;

// https://leetcode.com/problems/count-sub-islands/
public class CountSubIslands_1905 {
    boolean isSubIsland = true;

    public int countSubIslands1(int[][] grid1, int[][] grid2) {
        // dfs recursive
        // time O(n*m)
        // space O(n*m)

        int res = 0;
        boolean[][] seen = new boolean[grid2.length][grid2[0].length];
        for (int r = 0; r < grid2.length; r++) {
            for (int c = 0; c < grid2[0].length; c++) {
                if (grid2[r][c] == 1 && !seen[r][c]) {
                    dfs1(grid1, grid2, seen, r, c);
                    if (isSubIsland)
                        res++;

                    isSubIsland = true;
                }
            }
        }

        return res;
    }

    private void dfs1(int[][] grid1, int[][] grid2, boolean[][] seen, int r, int c) {
        if (r < 0 || r >= grid2.length || c < 0 || c >= grid2[0].length || grid2[r][c] == 0 || seen[r][c])
            return;

        if (grid1[r][c] != 1)
            isSubIsland = false;
        seen[r][c] = true;

        dfs1(grid1, grid2, seen, r + 1, c);
        dfs1(grid1, grid2, seen, r - 1, c);
        dfs1(grid1, grid2, seen, r, c + 1);
        dfs1(grid1, grid2, seen, r, c - 1);
    }

    // solution2
    int[][] directions = { {1, 0}, {-1, 0}, {0, 1}, {0, -1} };

    public int countSubIslands2(int[][] grid1, int[][] grid2) {
        // dfs, without using global var
        // time O(n * m)
        // space O(n * m)

        final int ROWS = grid2.length;
        final int COLS = grid2[0].length;
        int res = 0;
        boolean[][] visited = new boolean[ROWS][COLS];

        for(int r = 0; r < ROWS; r++)
            for(int c = 0; c < COLS; c++)
                if(grid2[r][c] == 1 && !visited[r][c] && dfs(grid1, grid2, visited, r, c))
                    res++;

        return res;
    }

    private boolean dfs(int[][] grid1, int[][] grid2, boolean[][] visited, int r, int c) {
        final int ROWS = grid2.length;
        final int COLS = grid2[0].length;

        if(r >= ROWS || r < 0 || c >= COLS || c < 0 || visited[r][c] || grid2[r][c] == 0)
            return true;

        boolean isSub = true;
        if(grid1[r][c] == 0)
            isSub = false;

        visited[r][c] = true;
        for(int[] dir : directions)
            // can't use in this way: isSub = isSub && dfs(grid1, grid2, visited, r + dir[0], c + dir[1]);
            // because if isSub false then dfs will not run and island will not be visited completely
            // but you can do like this: isSub = isSub & dfs(grid1, grid2, visited, r + dir[0], c + dir[1]);
            isSub = dfs(grid1, grid2, visited, r + dir[0], c + dir[1]) && isSub;

        return isSub;
    }
}
