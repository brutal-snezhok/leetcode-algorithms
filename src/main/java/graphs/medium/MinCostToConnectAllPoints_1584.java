package graphs.medium;

import java.util.*;

// https://leetcode.com/problems/min-cost-to-connect-all-points/description/
public class MinCostToConnectAllPoints_1584 {
    // solution1
    public int minCostConnectPoints1(int[][] points) {
        // Prim's algo, check time complexity of Prim's algo
        // time O(ElogV) = O(n^2 * logn), n - number of points
        // space O(E) = O(n^2)

        /**
         0 - [{node1, dist1}, {node2, dist2}]
         1 - [{node3, dist3}, ...]

         1. start from random node
         2. then add to mst the closest node
         3. then add to mst closest node to mst
         ...
         4. if mst.size == count nodes -> break
         */

        Map<Integer, List<int[]>> adjMap = new HashMap<>(); // // {i, [node, dist]}
        int n = points.length;
        for(int i = 0; i < n; i++) {
            for(int j = i + 1; j < n; j++) {
                int dist = dist(points[i], points[j]);
                adjMap.computeIfAbsent(i, v -> new ArrayList<>()).add(new int[]{j, dist});
                adjMap.computeIfAbsent(j, v -> new ArrayList<>()).add(new int[]{i, dist});
            }
        }


        Queue<int[]> minHeap = new PriorityQueue<>((arr1, arr2) -> arr1[1] - arr2[1]); //[node, dist from mst]
        Set<Integer> mst = new HashSet<>();
        int res = 0;

        minHeap.add(new int[]{0, 0});

        while(!minHeap.isEmpty()) {
            if(mst.size() == n)
                break;

            int[] pair = minHeap.poll();
            if(mst.contains(pair[0]))
                continue;

            res += pair[1];
            mst.add(pair[0]); // put it into minimum spanning tree only after taking from minHeap!

            List<int[]> adjNodes = adjMap.getOrDefault(pair[0], new ArrayList<>());
            for(int[] adjPair : adjNodes){
                if(!mst.contains(adjPair[0]))
                    minHeap.add(new int[]{adjPair[0], adjPair[1]});
            }

        }

        return res;
    }

    private int dist(int[] point1, int[] point2) {
        return Math.abs(point1[0] - point2[0]) + Math.abs(point1[1] - point2[1]);
    }

    // solution2
    public int minCostConnectPoints(int[][] points) {
        // Kruskal's slgo
        // time O(ElogE) = O(n^2 * log(n^2)), n - number of points
        // space O(E) = O(n^2)

        /**
         1. sort all edges(can use minHeap for it)
         2. add to mst vertexes with smallest edge but which not create cycle after it(can use  UnionFind for it)
         */

        int n = points.length;
        Queue<int[]> minHeap = new PriorityQueue<>((arr1, arr2) -> arr1[0] - arr2[0]); // [dist, node1, node2]
        for(int i = 0; i < n; i++) {
            for(int j = i + 1; j < n; j++) {
                int dist = dist(points[i], points[j]);
                minHeap.add(new int[]{dist, i, j});
            }
        }

        UnionFind uf = new UnionFind(n);
        int res = 0;
        while(!minHeap.isEmpty()) {
            if(uf.count == 1) // if all nodes will belong to the same component
                break;
            int[] edge = minHeap.poll();
            int dist = edge[0];
            int node1 = edge[1];
            int node2 = edge[2];

            if(uf.find(node1) != uf.find(node2)) { // if nodes do not create cycle then union them into MST
                res += dist;
                uf.union(node1, node2);
            }
        }

        return res;
    }

    class UnionFind {
        int[] root;
        int[] rank;
        int count;

        public UnionFind(int n) {
            root = new int[n];
            rank = new int[n];
            for(int i = 0; i < n; i++) {
                root[i] = i;
                rank[i] = 1;
            }
            this.count = n;
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

    public static void main(String[] args) {
        MinCostToConnectAllPoints_1584 minCost = new MinCostToConnectAllPoints_1584();
        minCost.minCostConnectPoints1(new int[][]{
                {0,0},
                {2,2},
                {3,10},
                {5,2},
                {7,0}
        });
    }
}
