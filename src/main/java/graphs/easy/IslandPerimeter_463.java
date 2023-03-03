package graphs.easy;

// https://leetcode.com/problems/island-perimeter/description/
public class IslandPerimeter_463 {
    public static int islandPerimeter(int[][] grid) {
        // recursive
        // time O(n*m)
        // space O(n*m)

        for (int r = 0; r < grid.length; r++) {
            for (int c = 0; c < grid[0].length; c++) {
                if (grid[r][c] == 1)
                    return dfs(grid, new boolean[grid.length][grid[0].length], r, c);
            }
        }

        return 0;
    }

    private static int dfs(int[][] grid, boolean[][] seen, int r, int c) {
        if (r < 0 || c < 0 || r >= grid.length || c >= grid[0].length || grid[r][c] == 0)
            return 1;

        if (seen[r][c])
            return 0;

        seen[r][c] = true;

        int perimeter = 0;
        perimeter += dfs(grid, seen, r + 1, c);
        perimeter += dfs(grid, seen, r - 1, c);
        perimeter += dfs(grid, seen, r, c + 1);
        perimeter += dfs(grid, seen, r, c - 1);

        return perimeter;
    }

    public static void main(String[] args) {
        System.out.println(islandPerimeter(new int[][]{
                {0,1,0,0},
                {1,1,1,0},
                {0,1,0,0},
                {1,1,0,0}
        }));
    }
}
