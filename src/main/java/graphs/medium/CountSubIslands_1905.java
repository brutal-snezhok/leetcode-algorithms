package graphs.medium;

// https://leetcode.com/problems/count-sub-islands/
public class CountSubIslands_1905 {
    boolean isSubIsland = true;

    public int countSubIslands(int[][] grid1, int[][] grid2) {
        // dfs recursive
        // time O(n*m)
        // space O(n*m)

        int res = 0;
        boolean[][] seen = new boolean[grid2.length][grid2[0].length];
        for (int r = 0; r < grid2.length; r++) {
            for (int c = 0; c < grid2[0].length; c++) {
                if (grid2[r][c] == 1 && !seen[r][c]) {
                    dfs(grid1, grid2, seen, r, c);
                    if (isSubIsland)
                        res++;

                    isSubIsland = true;
                }
            }
        }

        return res;
    }

    private void dfs(int[][] grid1, int[][] grid2, boolean[][] seen, int r, int c) {
        if (r < 0 || r >= grid2.length || c < 0 || c >= grid2[0].length || grid2[r][c] == 0 || seen[r][c])
            return;

        if (grid1[r][c] != 1)
            isSubIsland = false;
        seen[r][c] = true;

        dfs(grid1, grid2, seen, r + 1, c);
        dfs(grid1, grid2, seen, r - 1, c);
        dfs(grid1, grid2, seen, r, c + 1);
        dfs(grid1, grid2, seen, r, c - 1);
    }
}
