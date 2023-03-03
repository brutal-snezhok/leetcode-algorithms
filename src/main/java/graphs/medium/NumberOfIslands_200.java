package graphs.medium;

import java.util.*;

// https://leetcode.com/problems/number-of-islands/description/
public class NumberOfIslands_200 {
    public int numIslands(char[][] grid) {
        // time O(m*n)
        // space O(m*n)

        int res = 0;
        for (int r = 0; r < grid.length; r++) {
            for (int c = 0; c < grid[0].length; c++) {
                if (grid[r][c] == '1') {
                    dfs(grid, r, c);
                    res++;
                }
            }
        }

        return res;
    }

    private void dfs(char[][] grid, int r, int c) {
        if (r < 0 || r >= grid.length || c < 0 || c >= grid[0].length || grid[r][c] == '0')
            return;

        grid[r][c] = '0';
        dfs(grid, r + 1, c);
        dfs(grid, r - 1, c);
        dfs(grid, r, c + 1);
        dfs(grid, r, c - 1);
    }


    // solution2: bfs
    public int numIslands2(char[][] grid) {
        // time O(m*n)
        // space O(m*n)

        int res = 0;
        Set<Pair> visited = new HashSet<>();
        for(int r = 0; r < grid.length; r++) {
            for(int c = 0; c < grid[0].length; c++) {
                if(grid[r][c] == '1' && !visited.contains(new Pair(r, c))) {
                    bfs(grid, visited, r, c);
                    res++;
                }
            }
        }

        return res;
    }

    private void bfs(char[][] grid, Set<Pair> visited, int r, int c) {
        Queue<Pair> q = new LinkedList<>();
        visited.add(new Pair(r, c));
        q.add(new Pair(r, c));
        while(!q.isEmpty()) {
            Pair p = q.poll();

            int[][] directions = { { 1, 0 }, { -1, 0 }, { 0, 1 }, { 0, -1 } };
            for(int[] dir : directions) {
                int newR = p.r + dir[0];
                int newC = p.c + dir[1];
                Pair newPair = new Pair(newR, newC);

                if(newR >= 0 && newR < grid.length &&
                   newC >= 0 && newC < grid[0].length && grid[newR][newC] == '1' && !visited.contains(newPair)) {
                    visited.add(newPair);
                    q.add(newPair);
                }
            }
        }
    }

    class Pair {
        int r;
        int c;

        public Pair(int r, int c) {
            this.r = r;
            this.c = c;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Pair pair = (Pair) o;
            return r == pair.r && c == pair.c;
        }

        @Override
        public int hashCode() {
            return Objects.hash(r, c);
        }
    }
}

