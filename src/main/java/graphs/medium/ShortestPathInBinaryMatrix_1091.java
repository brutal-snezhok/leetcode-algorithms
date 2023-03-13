package graphs.medium;

import java.util.LinkedList;
import java.util.Queue;

// https://leetcode.com/problems/shortest-path-in-binary-matrix/
public class ShortestPathInBinaryMatrix_1091 {
    public int shortestPathBinaryMatrix(int[][] grid) {
        // time O(n^2)
        // space O(n^2)

        // start bfs from top-left
        // count number of steps need to go to bottom-right

        if (grid[0][0] == 1)
            return -1;

        final int ROWS = grid.length;
        final int COLS = grid[0].length;
        boolean[][] visited = new boolean[ROWS][COLS];
        int[][] directions = {{1, 0}, {-1, 0}, {0, 1}, {0, -1},
                {1, 1}, {-1, -1}, {-1, 1}, {1, -1}}; // 8 directions

        Queue<int[]> q = new LinkedList<>();
        q.add(new int[]{0, 0, 1}); // r, c, length
        visited[0][0] = true;

        while (!q.isEmpty()) {
            int n = q.size();
            for (int i = 0; i < n; i++) {
                int[] point = q.poll();
                int length = point[2];

                if (point[0] == ROWS - 1 && point[1] == COLS - 1)
                    return length;

                for (int[] dir : directions) {
                    int newR = point[0] + dir[0];
                    int newC = point[1] + dir[1];

                    if (newR >= 0 && newR < ROWS && newC >= 0 && newC < COLS && !visited[newR][newC] && grid[newR][newC] == 0) {
                        visited[newR][newC] = true;
                        q.add(new int[]{newR, newC, length + 1});
                    }
                }
            }
        }

        return -1;
    }

    public static void main(String[] args) {
        ShortestPathInBinaryMatrix_1091 shortest = new ShortestPathInBinaryMatrix_1091();
//        shortest.shortestPathBinaryMatrix(new int[][] {
//                {0,0,0},
//                {1,1,0},
//                {1,1,0}
//        });

        shortest.shortestPathBinaryMatrix(new int[][]{
                {0, 1},
                {1, 0}
        });
    }
}
