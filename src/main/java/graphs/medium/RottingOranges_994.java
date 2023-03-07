package graphs.medium;

import java.util.LinkedList;
import java.util.Queue;

// https://leetcode.com/problems/rotting-oranges/
public class RottingOranges_994 {
    int[][] directions = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};

    public int orangesRotting(int[][] grid) {
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

    public static void main(String[] args) {
        int[][] arr = new int[][]{
                {2, 1, 1},
                {1, 1, 0},
                {0, 1, 1}
        };

        RottingOranges_994 oranges_994 = new RottingOranges_994();
        System.out.println(oranges_994.orangesRotting(arr));
    }
}
