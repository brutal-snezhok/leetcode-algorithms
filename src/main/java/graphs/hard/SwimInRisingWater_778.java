package graphs.hard;

import java.util.PriorityQueue;
import java.util.Queue;

// https://leetcode.com/problems/swim-in-rising-water/description/
public class SwimInRisingWater_778 {
    public int swimInWater(int[][] grid) {
        // Dijkstra's algorithm on minHeap
        // time O((V + E) * log(V))
        // space O(V + E)

        int n = grid.length;
        int[][] directions = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
        Queue<int[]> minHeap = new PriorityQueue<>((arr1, arr2) -> arr1[2] - arr2[2]);
        minHeap.add(new int[]{0, 0, grid[0][0]});
        boolean[][] visited = new boolean[n][n];

        while (!minHeap.isEmpty()) {
            int[] point = minHeap.poll();
            int x = point[0];
            int y = point[1];
            int max = point[2];

            if (visited[x][y])
                continue;

            visited[x][y] = true;
            for (int[] dir : directions) {
                int newX = dir[0] + x;
                int newY = dir[1] + y;

                if (newX < 0 || newX >= n || newY < 0 || newY >= n || visited[newX][newY])
                    continue;

                int newMax = Math.max(max, grid[newX][newY]);
                if (newX == n - 1 && newY == n - 1)
                    return newMax;

                minHeap.add(new int[]{newX, newY, newMax});
            }
        }

        return 0;
    }
}
