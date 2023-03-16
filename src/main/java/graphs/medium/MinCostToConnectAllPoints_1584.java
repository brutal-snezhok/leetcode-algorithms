package graphs.medium;

import java.util.*;

// https://leetcode.com/problems/min-cost-to-connect-all-points/description/
public class MinCostToConnectAllPoints_1584 {
    public int minCostConnectPoints(int[][] points) {
        // Prim's algo
        // time O(n^2*logn), n - number of points
        // space O(n^2)

        int n = points.length;
        Map<Integer, List<int[]>> adjMap = new HashMap<>(); // {i, [dist, node]}

        // populate adjMap
        for (int i = 0; i < n; i++) {
            int x1 = points[i][0];
            int y1 = points[i][1];

            for (int j = i + 1; j < n; j++) {
                int x2 = points[j][0];
                int y2 = points[j][1];
                int dist = Math.abs(x1 - x2) + Math.abs(y1 - y2);
                adjMap.computeIfAbsent(i, k -> new ArrayList<>()).add(new int[]{dist, j});
                adjMap.computeIfAbsent(j, k -> new ArrayList<>()).add(new int[]{dist, i});
            }
        }

        int res = 0;
        Set<Integer> visited = new HashSet<>();
        Queue<int[]> minHeap = new PriorityQueue<>((arr1, arr2) -> arr1[0] - arr2[0]); // {dist, node}
        minHeap.add(new int[]{0, 0});

        while (!minHeap.isEmpty()) {
            if (visited.size() == n)
                break;

            int[] point = minHeap.poll();
            int dist = point[0];
            int v = point[1];

            if (visited.contains(v))
                continue;

            visited.add(v);
            res += dist;

            for (int[] curr : adjMap.getOrDefault(point[1], new ArrayList<>())) {
                if (visited.contains(curr[1]))
                    continue;

                minHeap.add(new int[]{curr[0], curr[1]});
            }
        }

        return res;
    }
}
