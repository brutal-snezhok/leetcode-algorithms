package graphs.unionFind;

// https://leetcode.com/problems/maximum-number-of-fish-in-a-grid/description/
public class MaximumNumberOfFishInGrid_2658 {
    // solution1
    public int findMaxFish1(int[][] grid) {
        // dfs
        // time O(m * n)
        // space O(m * n)

        final int ROWS = grid.length;
        final int COLS = grid[0].length;

        int res = 0;
        for(int r = 0; r < ROWS; r++) {
            for(int c = 0; c < COLS; c++) {
                if(grid[r][c] != 0)
                    res = Math.max(res, dfs(grid, ROWS, COLS, r, c));
            }
        }

        return res;
    }

    private int dfs(int[][] grid, int ROWS, int COLS, int r, int c) {
        if(r >= ROWS || r < 0 || c >= COLS || c < 0 || grid[r][c] == 0)
            return 0;

        int res = grid[r][c];
        grid[r][c] = 0;

        res += dfs(grid, ROWS, COLS, r + 1, c);
        res += dfs(grid, ROWS, COLS, r - 1, c);
        res += dfs(grid, ROWS, COLS, r, c + 1);
        res += dfs(grid, ROWS, COLS, r, c - 1);

        return res;
    }

    // solution2
    public int findMaxFish(int[][] grid) {
        // union find
        // time O(n*m)
        // space O(n*m)

        final int ROWS = grid.length;
        final int COLS = grid[0].length;

        UnionFind uf = new UnionFind(grid);
        int res = 0;
        for(int r = 0; r < ROWS; r++) {
            for(int c = 0; c < COLS; c++) {
                if(grid[r][c] != 0) {
                    if(r != ROWS - 1 && grid[r + 1][c] != 0)
                        uf.union(convertToVal(r, c, COLS), convertToVal(r + 1, c, COLS));

                    if(c != COLS - 1 && grid[r][c + 1] != 0)
                        uf.union(convertToVal(r, c, COLS), convertToVal(r, c + 1, COLS));

                    res = Math.max(res, uf.getFishes(r * COLS + c));
                }
            }
        }

        return res;
    }

    private int convertToVal(int r, int c, int COLS) {
        return r * COLS + c;
    }

    class UnionFind {
        int[] root; // number of component to which belongs cell (r,c); // r * COLS + c
        int[] rate; // depth of subtree
        int[] fishes;

        public UnionFind(int[][] grid) {
            int m = grid.length;
            int n = grid[0].length;
            root = new int[m*n];
            rate = new int[m*n];
            fishes = new int[m*n];

            for(int i = 0; i < m; i++) {
                for(int j = 0; j < n; j++) {
                    fishes[n * i + j] = grid[i][j];
                    root[n * i + j] = n * i + j;
                    rate[n * i + j] = 1;
                }
            }
        }

        public int find(int p) {
            while(p != root[p]) {
                root[p] = root[root[p]];
                p = root[p];
            }

            return p;
        }

        public void union(int p, int q) {
            int rootP = find(p);
            int rootQ = find(q);

            if(rootP == rootQ)
                return;

            if(rate[rootP] > rate[rootQ]) {
                root[rootQ] = rootP;
                fishes[rootP] += fishes[rootQ];
            }
            else if(rate[rootP] < rate[rootQ]) {
                root[rootP] = rootQ;
                fishes[rootQ] += fishes[rootP];
            }
            else {
                root[rootP] = rootQ;
                rate[rootQ] += 1;
                fishes[rootQ] += fishes[rootP];
            }
        }

        public int getFishes(int x) {
            return fishes[find(x)];
        }
    }
}
