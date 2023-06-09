package graphs.medium;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

// https://leetcode.com/problems/number-of-enclaves/description/
public class NumberOfEnclaves_1020 {
    // solution1
    public int numEnclaves(int[][] grid) {
        // dfs
        // time O(m*n)
        // space O(m*n)

        /*
            do dfs from all 1 which are located on borders
            add them to set
            traverse one more time check if 1 and not in set than res++
         */

        int res = 0;
        final int ROWS = grid.length;
        final int COLS = grid[0].length;
        Set<Pair> visited = new HashSet<>();

        for(int r = 0; r < ROWS; r++) {
            for(int c = 0; c < COLS; c++) {
                if(visited.contains(new Pair(r, c)))
                    continue;

                if(grid[r][c] == 1 && (r == 0 || r == ROWS - 1 || c == 0 || c == COLS - 1))
                    dfs(grid, r, c, visited);
            }
        }

        for(int r = 0; r < ROWS; r++) {
            for(int c = 0; c < COLS; c++) {
                if(grid[r][c] == 1 && !visited.contains(new Pair(r, c)))
                    res++;
            }
        }

        return res;
    }

    int[][] directions = { {1, 0}, {-1, 0}, {0, 1}, {0, -1} };

    private void dfs(int[][] grid, int r, int c, Set<Pair> visited) {
        final int ROWS = grid.length;
        final int COLS = grid[0].length;

        Pair p = new Pair(r, c);
        if(r >= ROWS || r < 0 || c >= COLS || c < 0 || grid[r][c] == 0 || visited.contains(p))
            return;

        visited.add(p);
        for(int[] dir : directions)
            dfs(grid, r + dir[0], c + dir[1], visited);
    }

    class Pair {
        int r;
        int c;

        public Pair(int r, int c) {
            this.r = r;
            this.c = c;
        }

        public boolean equals(Object o) {
            if(this == o)
                return true;
            if(o == null || o.getClass() != this.getClass())
                return false;

            Pair p = (Pair) o;
            return this.r == p.r && this.c == p.c;
        }

        public int hashCode() {
            return Objects.hash(r, c);
        }
    }
}
