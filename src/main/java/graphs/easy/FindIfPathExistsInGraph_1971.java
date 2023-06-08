package graphs.easy;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

// https://leetcode.com/problems/find-if-path-exists-in-graph/description/
public class FindIfPathExistsInGraph_1971 {
    // solution1
    public boolean validPath(int n, int[][] edges, int source, int destination) {
        // dfs
        // time O(n + m), n - num of nodes, m - num of edges
        // space O(n + m)

        // create adjency list
        Map<Integer, List<Integer>> adjMap = new HashMap<>();
        for(int[] pair : edges) {
            int u = pair[0];
            int v = pair[1];
            adjMap.computeIfAbsent(u, val -> new ArrayList<>()).add(v);
            adjMap.computeIfAbsent(v, val -> new ArrayList<>()).add(u);
        }
        boolean[] visited = new boolean[n];

        // do dfs by adjency list
        return dfs(adjMap, visited, source, destination);
    }

    private boolean dfs(Map<Integer, List<Integer>> adjMap, boolean[] visited, int currNode, int dest) {
        if(visited[currNode])
            return false;
        if(currNode == dest)
            return true;

        visited[currNode] = true;

        for(int node : adjMap.get(currNode))
            if(dfs(adjMap, visited, node, dest))
                return true;

        return false;
    }
}
