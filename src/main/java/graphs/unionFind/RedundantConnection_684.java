package graphs.unionFind;

// https://leetcode.com/problems/redundant-connection/
public class RedundantConnection_684 {
    public int[] findRedundantConnection(int[][] edges) {
        // union-find
        // time O(n), n - number if vertices
        // space O(n)

        int n = edges.length;
        UnionFind uf = new UnionFind(n);
        for(int[] edge : edges)
            if(uf.union(edge[0], edge[1]))
                return edge;

        return null;
    }

    class UnionFind {
        int[] parent;
        int[] rank;

        public UnionFind(int n) {
            parent = new int[n + 1];
            rank = new int[n + 1];
            for(int i = 1; i <= n; i++) {
                parent[i] = i;
                rank[i] = 1;
            }
        }

        public int findRoot(int p) {
            while(p != parent[p]) {
                parent[p] = parent[parent[p]];
                p = parent[p];
            }

            return p;
        }

        public boolean union(int p, int q) {
            int rootP = findRoot(p);
            int rootQ = findRoot(q);

            if(rootP == rootQ) return true;

            if(rank[rootP] < rank[rootQ]) {
                parent[rootP] = rootQ;
                rank[rootQ] += rank[rootP];
            } else {
                parent[rootQ] = rootP;
                rank[rootP] = rootQ;
            }

            return false;
        }
    }
}
