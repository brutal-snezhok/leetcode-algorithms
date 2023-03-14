package graphs.medium;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

// https://leetcode.com/problems/number-of-provinces/
public class NumberOfProvinces_547 {
    // solution1
    public int findCircleNum(int[][] grid) {
        // classical problem of counting number of connected components
        // dfs
        // time (n * n)
        // space O(n * n)

        int n = grid.length;
        if(n == 1)
            return 1;

        Map<Integer, List<Integer>> adjMap = new HashMap<>();
        for(int i = 1; i <= n; i++)
            adjMap.put(i, new ArrayList<>());

        for(int r = 0; r < n; r++) {
            for(int c = 0; c < n; c++) {
                if(grid[r][c] == 1 && r != c)
                    adjMap.get(r + 1).add(c + 1);
            }
        }

        boolean[] visited = new boolean[n + 1];
        int numOfComponents = 0;

        for(int i = 1; i <= n; i++) {
            if(!visited[i]) {
                dfs(i, adjMap, visited);
                numOfComponents++;
            }
        }

        return numOfComponents;
    }

    private void dfs(int v, Map<Integer, List<Integer>> adjMap, boolean[] visited) {
        if(visited[v])
            return;

        visited[v] = true;
        for(int w : adjMap.get(v)) {
            dfs(w, adjMap, visited);
        }
    }



    // solution2
    public int findCircleNum2(int[][] grid) {
        // classical problem of counting number of connected components
        // union-find
        // time (n * n)
        // space O(n * n)

        int n = grid.length;
        UnionFind uf = new UnionFind(n);
        for(int r = 0; r < n; r++) {
            for(int c = r + 1; c < n; c++) {
                if(grid[r][c] == 1)
                    uf.union(r, c);
            }
        }

        return uf.count();
    }

    class UnionFind {
        int[] parent; // parent[i] = parent of i
        int[] rank;
        int count; // number of components

        public UnionFind(int n) {
            count = n;
            parent = new int[n];
            rank = new int[n];

            for(int i = 0; i < n; i++) {
                parent[i] = i;
                rank[i] = 1;
            }
        }

        public int findRoot(int p) {
            while(p != parent[p]) {
                parent[p] = parent[parent[p]]; // path compression by halving
                p = parent[p];
            }

            return p;
        }

        public void union(int p, int q) {
            int rootP = findRoot(p);
            int rootQ = findRoot(q);

            if(rootP == rootQ) return;

            if(rank[rootP] < rank[rootQ]) {
                parent[rootP] = rootQ;
                rank[rootQ] += rank[rootP];
            } else {
                parent[rootQ] = rootP;
                rank[rootP] += rank[rootQ];
            }
            count--;
        }

        public int count() {
            return count;
        }
    }
}
