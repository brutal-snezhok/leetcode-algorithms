package graphs.easy;

import java.util.*;

// https://leetcode.com/problems/find-if-path-exists-in-graph/description/
public class FindIfPathExistsInGraph_1971 {
    // solution1
    public boolean validPath1(int n, int[][] edges, int source, int destination) {
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

    // solution2
    public boolean validPath2(int n, int[][] edges, int source, int destination) {
        // bfs
        // time O(n + m), n - num of nodes, m - num of edges
        // space O(n + m)

        Map<Integer, List<Integer>> map = new HashMap<>();
        for(int[] edge : edges) {
            map.computeIfAbsent(edge[0], v -> new ArrayList<>()).add(edge[1]);
            map.computeIfAbsent(edge[1], v -> new ArrayList<>()).add(edge[0]);
        }

        Queue<Integer> q = new LinkedList<>();
        boolean[] visited = new boolean[n];
        q.add(source);
        visited[source] = true;

        while(!q.isEmpty()) {
            int size = q.size();

            for(int i = 0; i < size; i++) {
                int u = q.poll();

                if(u == destination)
                    return true;

                //visited[u] = true; TLE if marked after taking from q

                List<Integer> adjVertex = map.get(u);
                for(int v : adjVertex) {
                    if(!visited[v]) {
                        visited[v] = true;
                        q.add(v);
                    }
                }
            }
        }

        return false;
    }
}
