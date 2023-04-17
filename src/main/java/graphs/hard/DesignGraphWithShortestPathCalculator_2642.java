package graphs.hard;

import java.util.*;

// https://leetcode.com/problems/design-graph-with-shortest-path-calculator/description/
public class DesignGraphWithShortestPathCalculator_2642 {
    Map<Integer, List<int[]>> adjMap = new HashMap<>(); // {from, [to, edgeCost]}
    int n;

    public DesignGraphWithShortestPathCalculator_2642(int n, int[][] edges) {
        this.n = n;
        for(int i = 0; i < n; i++)
            adjMap.put(i, new ArrayList<>());

        for(int[] edge : edges) {
            int[] pair = new int[2];
            pair[0] = edge[1];
            pair[1] = edge[2];

            adjMap.get(edge[0]).add(pair);
        }
    }

    public void addEdge(int[] edge) {
        int[] pair = new int[2];
        pair[0] = edge[1];
        pair[1] = edge[2];

        adjMap.get(edge[0]).add(pair);
    }

    public int shortestPath(int node1, int node2) {
        // time O(E*logV), V is the number of vertices
        // E is the total number of edges
        // space O(E + V), due to adjMap

        Queue<int[]> minHeap = new PriorityQueue<>((arr1, arr2) -> arr1[0] - arr2[0]); // [cost, vertex]
        minHeap.add(new int[]{0, node1});
        int res = Integer.MAX_VALUE;
        Set<Integer> visited = new HashSet<>();

        while(!minHeap.isEmpty()) {
            int[] pair = minHeap.poll();
            int edgeCost = pair[0];
            int vertex = pair[1];

            if(vertex == node2) {
                res = edgeCost;
                break;
            }

            if(visited.contains(vertex))
                continue;

            visited.add(vertex);
            for(int[] child : adjMap.get(vertex))
                minHeap.add(new int[]{edgeCost + child[1], child[0]});
        }

        return res == Integer.MAX_VALUE ? -1 : res;
    }
}
