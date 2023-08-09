package graphs.medium;

import java.util.LinkedList;
import java.util.Queue;

// https://leetcode.com/problems/rotting-oranges/
public class RottingOranges_994 {
    // solution1
    int[][] directions = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};

    public int orangesRotting1(int[][] grid) {
        // multi bfs at the same time
        // time O(m * n)
        // space O(m * n)

        // find all rotten oranges and add them to queue
        // run bfs on them

        final int ROWS = grid.length;
        final int COLS = grid[0].length;
        Queue<int[]> q = new LinkedList<>();

        int maxCount = 0;
        for (int r = 0; r < ROWS; r++) {
            for (int c = 0; c < COLS; c++) {
                if (grid[r][c] == 2) {
                    q.add(new int[]{r, c});
                }
            }
        }

        maxCount = Math.max(maxCount, bfs(q, grid, ROWS, COLS));

        for (int r = 0; r < ROWS; r++) {
            for (int c = 0; c < COLS; c++) {
                if (grid[r][c] == 1)
                    return -1;
            }
        }

        return maxCount;
    }

    private int bfs(Queue<int[]> q, int[][] grid, int ROWS, int COLS) {
        int res = -1; // since 0 minute is when first cell is being processed

        while (!q.isEmpty()) {
            int qSize = q.size();

            for (int i = 0; i < qSize; i++) {
                int[] point = q.poll();

                for (int[] dir : directions) {
                    int newR = point[0] + dir[0];
                    int newC = point[1] + dir[1];

                    if (newR >= 0 && newR < ROWS && newC >= 0 && newC < COLS && grid[newR][newC] == 1) {
                        grid[newR][newC] = 2;
                        q.add(new int[]{newR, newC});
                    }
                }
            }

            res++;
        }

        return res;
    }

    // solution2

    public int orangesRotting2(int[][] grid) {
        // time O(m * n)
        // space O(m * n) because of queue

        /**
         1. add all rotten oranges to queue
         2. run bfs
         3. check all grid if fresh exist then return -1
         */
        final int ROWS = grid.length;
        final int COLS = grid[0].length;
        Queue<int[]> q = new LinkedList<>(); // [r, c, level]
        boolean[][] visited = new boolean[ROWS][COLS];

        for(int r = 0; r < ROWS; r++)
            for(int c = 0; c < COLS; c++)
                if(grid[r][c] == 2)
                    q.add(new int[]{r,  c, 0});

        int minutes = bfs(grid, q, ROWS, COLS, visited);

        for(int r = 0; r < ROWS; r++)
            for(int c = 0; c < COLS; c++)
                if(grid[r][c] == 1 && !visited[r][c])
                    return -1;

        return minutes;
    }

    private int bfs(int[][] grid, Queue<int[]> q, int ROWS, int COLS, boolean[][] visited) {
        int minute = 0;
        while(!q.isEmpty()) {
            int[] cell = q.poll();
            int r = cell[0];
            int c = cell[1];
            int level = cell[2];
            if(visited[r][c])
                continue;

            visited[r][c] = true;
            minute = level;

            for(int[] dir : directions) {
                int newR = dir[0] + r;
                int newC = dir[1] + c;

                if(newR >= 0 && newR < ROWS && newC >= 0 && newC < COLS && !visited[newR][newC] && grid[newR][newC] == 1) {
                    q.add(new int[]{newR, newC, level + 1});
                }
            }
        }

        return minute;
    }

    public static void main(String[] args) {
        int[][] arr = new int[][]{
                {2, 1, 1},
                {1, 1, 0},
                {0, 1, 1}
        };

        RottingOranges_994 oranges_994 = new RottingOranges_994();
        System.out.println(oranges_994.orangesRotting2(arr));
    }
}
