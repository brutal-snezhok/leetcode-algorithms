package graphs.medium;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.LinkedList;
import java.util.Queue;

// https://leetcode.com/problems/max-area-of-island/description/
public class MaxAreaOfIsland_695 {
    int res = 0;
    int currCount = 0;

    // solution1
    public int maxAreaOfIsland(int[][] grid) {
        // dfs recursive with changing initial grid
        // time O(m*n)
        // space O(m*n)

        for(int r = 0; r < grid.length; r++) {
            for(int c = 0; c < grid[0].length; c++) {
                if(grid[r][c] == 1) {
                    currCount = 0;
                    dfs(grid, r, c);
                    res = Math.max(res, currCount);
                }
            }
        }

        return res;
    }

    private void dfs(int[][] grid, int r, int c) {
        if(r < 0 || r >= grid.length || c < 0 || c >= grid[0].length || grid[r][c] == 0)
            return;

        currCount++;
        grid[r][c] = 0;
        dfs(grid, r + 1, c);
        dfs(grid, r - 1, c);
        dfs(grid, r, c + 1);
        dfs(grid, r, c - 1);
    }

    // solution2
    public int maxAreaOfIsland2(int[][] grid) {
        // dfs iterative
        // time O(m*n)
        // space O(m*n)

        boolean[][] seen = new boolean[grid.length][grid[0].length];
        int currCount = 0;
        int res = 0;

        for(int r = 0; r < grid.length; r++) {
            for(int c = 0; c < grid[0].length; c++) {
                if(grid[r][c] == 1 && !seen[r][c]) {
                    Deque<int[]> stack = new ArrayDeque<>();
                    stack.add(new int[]{r, c});
                    seen[r][c] = true;

                    while(!stack.isEmpty()) {
                        int[] point = stack.removeFirst();
                        currCount++;
                        int[][] directions = { {1, 0}, {-1, 0}, {0, 1}, {0, -1} };
                        for(int[] dir : directions) {
                            int newR = point[0] + dir[0];
                            int newC = point[1] + dir[1];

                            if(newR >= 0 && newR < grid.length
                                    && newC >= 0 && newC < grid[0].length
                                    && grid[newR][newC] == 1 && !seen[newR][newC]) {
                                stack.addFirst(new int[]{newR, newC});
                                seen[newR][newC] = true;
                            }
                        }
                    }

                    res = Math.max(res, currCount);
                    currCount = 0;
                }
            }
        }

        return res;
    }


    // solution3
    public int maxAreaOfIsland3(int[][] grid) {
        // bfs
        // time O(m*n)
        // space O(m*n)

        boolean[][] seen = new boolean[grid.length][grid[0].length];
        int currCount = 0;
        int res = 0;

        for(int r = 0; r < grid.length; r++) {
            for(int c = 0; c < grid[0].length; c++) {
                if(grid[r][c] == 1 && !seen[r][c]) {
                    Queue<int[]> q = new LinkedList<>();
                    q.add(new int[]{r, c});
                    seen[r][c] = true;

                    while(!q.isEmpty()) {
                        int[] point = q.poll();
                        currCount++;
                        int[][] directions = { {1, 0}, {-1, 0}, {0, 1}, {0, -1} };
                        for(int[] dir : directions) {
                            int newR = point[0] + dir[0];
                            int newC = point[1] + dir[1];

                            if(newR >= 0 && newR < grid.length
                                    && newC >= 0 && newC < grid[0].length
                                    && grid[newR][newC] == 1 && !seen[newR][newC]) {
                                q.add(new int[]{newR, newC});
                                seen[newR][newC] = true;
                            }
                        }
                    }

                    res = Math.max(res, currCount);
                    currCount = 0;
                }
            }
        }

        return res;
    }
}
