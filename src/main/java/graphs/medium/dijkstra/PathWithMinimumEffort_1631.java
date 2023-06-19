package graphs.medium.dijkstra;

import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Queue;

public class PathWithMinimumEffort_1631 {
    // solution1
    int[][] directions = { {1, 0}, {-1, 0}, {0, 1}, {0, -1} };
    public int minimumEffortPath(int[][] heights) {
        // dijkstra
        // time O(V + ElogV), E = 4*n*m, V = n * m
        // space O(V + E)

        int n = heights.length;
        int m = heights[0].length;

        int[][] efforts = new int[n][m];
        for (int[] e : efforts) {
            Arrays.fill(e, Integer.MAX_VALUE);
        }
        efforts[0][0] = 0;
        Queue<int[]> minHeap = new PriorityQueue<>((arr1, arr2) -> arr1[0] - arr2[0]); // [effort, x, y]
        minHeap.add(new int[]{0, 0, 0});
        while(!minHeap.isEmpty()) {
            int[] cell = minHeap.poll();
            int currEffort = cell[0];
            int currX = cell[1];
            int currY = cell[2];

            if(currX == n - 1 && currY == m - 1)
                return currEffort;

            for(int[] dir : directions) {
                int newX = dir[0] + currX;
                int newY = dir[1] + currY;

                if(newX >= n || newX < 0 || newY >= m || newY < 0)
                    continue;

                int newEffort = Math.max(currEffort, Math.abs(heights[currX][currY] - heights[newX][newY]));
                if(efforts[newX][newY] > newEffort) {
                    efforts[newX][newY] = newEffort;
                    minHeap.add(new int[]{newEffort, newX, newY});
                }
            }
        }

        return 0;
    }

    public static void main(String[] args) {
        PathWithMinimumEffort_1631 path = new PathWithMinimumEffort_1631();
        path.minimumEffortPath(new int[][]{
                {1,2,2},
                {3,8,2},
                {5,3,5}
        });
    }
}
