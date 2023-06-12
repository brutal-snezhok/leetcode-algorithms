package graphs.medium;

import java.util.LinkedList;
import java.util.Queue;

// https://leetcode.com/problems/shortest-bridge/description/
public class ShortestBridge_934 {
    int[][] directions = { {1, 0}, {-1, 0}, {0, 1}, {0, -1} };

    public int shortestBridge(int[][] grid) {
        // time O(n*n)
        // space O(n*n)

        /**
         1. find with dfs first island
         2. find with bfs shortest path
         */

        int n = grid.length;
        boolean[][] visited = new boolean[n][n];
        Queue<int[]> q = new LinkedList<>(); // [row, col, length]

        boolean isFoundFirstIsland = false;
        for(int r = 0; r < n; r++)
            for(int c = 0; c < n; c++)
                if(grid[r][c] == 1 && !isFoundFirstIsland) {
                    isFoundFirstIsland = true;
                    dfs(grid, r, c, visited, q);
                }

        return bfs(grid, visited, q);
    }

    private int bfs(int[][] grid, boolean[][] visited, Queue<int[]> q) {
        int n = grid.length;
        while(!q.isEmpty()) {
            int size = q.size();

            for(int i = 0; i < size; i++) {
                int[] curr = q.poll();

                if(grid[curr[0]][curr[1]] == 1)
                    return curr[2] - 1;

                for(int[] dir : directions) {
                    int newX = curr[0] + dir[0];
                    int newY = curr[1] + dir[1];

                    if(newX < n && newX >= 0 && newY < n && newY >= 0 && !visited[newX][newY]) {
                        visited[newX][newY] = true;
                        q.add(new int[]{newX, newY, curr[2] + 1});
                    }
                }
            }
        }

        return -1;
    }

    private void dfs(int[][] grid, int r, int c, boolean[][] visited, Queue<int[]> q) {
        int n = grid.length;
        if(r >= n || r < 0 || c >= n || c < 0 || visited[r][c] || grid[r][c] == 0)
            return;

        visited[r][c] = true;
        grid[r][c] = 0;
        q.add(new int[]{r, c, 0});
        for(int[] dir : directions)
            dfs(grid, r + dir[0], c + dir[1], visited, q);
    }

    public static void main(String[] args) {
        ShortestBridge_934 bridge = new ShortestBridge_934();
//        bridge.shortestBridge(new int[][]{
//                {1,1,1,1,1},
//                {1,0,0,0,1},
//                {1,0,1,0,1},
//                {1,0,0,0,1},
//                {1,1,1,1,1}
//        });

        bridge.shortestBridge(new int[][]{
                {0,1,0,0,0},
                {0,1,0,1,1},
                {0,0,0,0,1},
                {0,0,0,0,0},
                {0,0,0,0,0}
        });
    }
}
