package graphs.medium;

import java.util.LinkedList;
import java.util.Queue;

// https://leetcode.com/problems/shortest-bridge/description/
public class ShortestBridge_934 {
    int[][] directions = { {1, 0}, {-1, 0}, {0, 1}, {0, -1} };

    public int shortestBridge(int[][] grid) {
        // time O(n^2)
        // space O(n^2)

        // go through grid and find first island
        // change with dfs 1 to 2 on the first island
        // add all points of second island to queue
        // run bfs search from every point

        boolean isNotFoundFirst = true;
        final int ROWS = grid.length;
        final int COLS = grid[0].length;
        Queue<int[]> q = new LinkedList<>();

        for(int i = 0; i < ROWS; i++) {
            for(int j = 0; j < COLS; j++) {
                if(grid[i][j] == 1 && isNotFoundFirst) {
                    isNotFoundFirst = false;
                    dfs(i, j, grid, ROWS, COLS);
                }

                if(grid[i][j] == 1 && !isNotFoundFirst)
                    q.add(new int[]{i, j});
            }
        }

        return bfs(grid, ROWS, COLS, q);
    }

    private int bfs(int[][] grid, int ROWS, int COLS, Queue<int[]> q) {
        int step = 0;
        while(!q.isEmpty()) {
            int n = q.size();
            for(int i = 0; i < n; i++) {
                int[] point = q.poll();

                // add to q all children of curr point
                for(int[] dir : directions) {
                    int newR = point[0] + dir[0];
                    int newC = point[1] + dir[1];
                    if(newR >= 0 && newR < ROWS && newC >= 0 && newC < COLS) {
                        if(grid[newR][newC] == 2)
                            return step;
                        if(grid[newR][newC] == 0) {
                            grid[newR][newC] = 1;  // change to 1 to not add to q the same point again
                            q.add(new int[]{newR, newC});
                        }
                    }
                }

            }
            step++;
        }

        return -1;
    }

    private void dfs(int r, int c, int[][] grid, int ROWS, int COLS) {
        if(r < 0 || r >= ROWS || c < 0 || c >= COLS || grid[r][c] == 0 || grid[r][c] == 2)
            return;

        grid[r][c] = 2;
        for(int[] dir : directions)
            dfs(r + dir[0], c + dir[1], grid, ROWS, COLS);
    }

    public static void main(String[] args) {
        ShortestBridge_934 bridge = new ShortestBridge_934();
        bridge.shortestBridge(new int[][]{
                {1,1,1,1,1},
                {1,0,0,0,1},
                {1,0,1,0,1},
                {1,0,0,0,1},
                {1,1,1,1,1}
        });
    }
}
