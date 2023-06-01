package graphs.unionFind.impl;

public class RankedUnionFind {
    int[] root; // root[i] = root of subtree where i belongs to
    int[] rank; // rank[i] - depth of tree where i belongs to
    int count; // number of components

    public RankedUnionFind(int n) {
        root = new int[n];
        rank = new int[n];
        count = n;

        for(int i = 0; i < n; i++) {
            root[i] = i;
            rank[i] = 1;
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

        if(rank[rootP] > rank[rootQ])
            root[rootQ] = rootP;
        else if(rank[rootP] < rank[rootQ])
            root[rootP] = rootQ;
        else {
            root[rootP] = rootQ;
            rank[rootQ] += 1;
        }

        count--;
    }
}
