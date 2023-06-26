package graphs.medium.topologicalSort;

import java.util.*;

// https://leetcode.com/problems/minimum-height-trees/description/
public class MinimumHeightTrees_310 {
    public List<Integer> findMinHeightTrees(int n, int[][] edges) {
        // Kahn's algo, topological sort
        // time O(V + E)
        // space O(V + E)

        List<Integer> res = new ArrayList<>();
        if(n == 1) { // edge case
            res.add(0);
            return res;
        }

        Map<Integer, List<Integer>> map = new HashMap<>();
        Map<Integer, Integer> inDegree = new HashMap<>();

        // init
        for(int i = 0; i < n; i++) {
            map.put(i, new ArrayList<>());
            inDegree.put(i, 0);
        }

        // build
        for(int[] edge : edges) {
            int s = edge[0];
            int d = edge[1];

            map.computeIfAbsent(s, v -> new ArrayList<>()).add(d);
            map.computeIfAbsent(d, v -> new ArrayList<>()).add(s);

            inDegree.put(s, inDegree.get(s) + 1);
            inDegree.put(d, inDegree.get(d) + 1);
        }

        Queue<Integer> leaves = new LinkedList<>();
        for(Map.Entry<Integer, Integer> entry : inDegree.entrySet()) {
            if(entry.getValue() == 1)
                leaves.add(entry.getKey());
        }

        int totalNodes = n;
        while(totalNodes > 2) {
            int size = leaves.size();
            totalNodes -= size;

            for(int i = 0; i < size; i++) {
                int vertex = leaves.poll();
                for(int child : map.get(vertex)) {
                    inDegree.put(child, inDegree.get(child) - 1);
                    if(inDegree.get(child) == 1)
                        leaves.add(child);
                }
            }
        }

        res.addAll(leaves);
        return res;
    }
}
