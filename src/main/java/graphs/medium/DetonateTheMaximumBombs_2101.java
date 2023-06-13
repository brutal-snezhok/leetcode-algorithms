package graphs.medium;

import java.util.*;

// https://leetcode.com/problems/detonate-the-maximum-bombs/description/
public class DetonateTheMaximumBombs_2101 {
    public int maximumDetonation(int[][] bombs) {
        // time O(n^3), O(n^2) - bfs * O(n) - iterate through all nodes
        // space O(n^2)

        Map<Integer, List<Integer>> map = new HashMap<>();
        for (int i = 0; i < bombs.length; i++) {
            for (int j = 0; j < bombs.length; j++) {
                if (i == j)
                    continue;
                if (length(bombs[i], bombs[j]) <= (long) bombs[i][2] * bombs[i][2])
                    map.computeIfAbsent(i, v -> new ArrayList<>()).add(j);
            }
        }

        int res = 0;
        for (int i = 0; i < bombs.length; i++)
            res = Math.max(res, bfs(map, i));


        return res;
    }

    private int bfs(Map<Integer, List<Integer>> map, int i) {
        Queue<Integer> q = new LinkedList<>();
        Set<Integer> visited = new HashSet<>();
        q.add(i);
        visited.add(i);

        while (!q.isEmpty()) {
            int node = q.poll();

            List<Integer> children = map.getOrDefault(node, new ArrayList<>());
            for (int child : children) {
                if (!visited.contains(child)) {
                    q.add(child);
                    visited.add(child);
                }
            }
        }

        return visited.size();
    }

    private long length(int[] b1, int[] b2) {
        return (long) (b1[0] - b2[0]) * (b1[0] - b2[0]) + (long) (b1[1] - b2[1]) * (b1[1] - b2[1]);
    }
}
