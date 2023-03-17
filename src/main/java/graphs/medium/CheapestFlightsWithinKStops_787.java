package graphs.medium;

import java.util.*;

// https://leetcode.com/problems/cheapest-flights-within-k-stops/description/
public class CheapestFlightsWithinKStops_787 {
    public static int findCheapestPrice(int n, int[][] flights, int src, int dst, int k) {
        // bfs
        // time O(N + E*K)
        // space O(N + E*K)

        Map<Integer, List<int[]>> adjMap = new HashMap<>(); // in list we will have pairs[node, weight]
        for (int[] flight : flights) {
            List<int[]> list = adjMap.getOrDefault(flight[0], new ArrayList<>());
            list.add(new int[]{flight[1], flight[2]});
            adjMap.put(flight[0], list);
        }

        Queue<int[]> q = new LinkedList<>(); // pair[node, weight]
        int[] dist = new int[n];
        Arrays.fill(dist, Integer.MAX_VALUE);

        q.add(new int[]{src, 0});
        int step = 0;

        while (!q.isEmpty()) {
            if (step > k)
                break;

            int size = q.size();

            for (int i = 0; i < size; i++) {
                int[] pair = q.poll();
                int node = pair[0];
                int weight = pair[1];

                // go through children
                for (int[] child : adjMap.getOrDefault(node, new ArrayList<>())) {
                    int neighbor = child[0];
                    int price = child[1];

                    if (price + weight >= dist[neighbor])
                        continue;

                    dist[neighbor] = price + weight;
                    q.add(new int[]{neighbor, dist[neighbor]});
                }
            }

            step++;
        }

        return dist[dst] == Integer.MAX_VALUE ? -1 : dist[dst];
    }

    public int findCheapestPrice2(int n, int[][] flights, int src, int dst, int k) {
        // Bellman Ford
        // time O ((N + E)*K)
        // space O(N)

        int[] dist = new int[n];
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[src] = 0;

        for(int i = 0; i <= k; i++) {
            int[] temp = Arrays.copyOf(dist, n);
            for(int[] flight : flights) {
                if(dist[flight[0]] != Integer.MAX_VALUE)
                    temp[flight[1]] = Math.min(temp[flight[1]], dist[flight[0]] + flight[2]);
            }

            dist = temp;
        }


        return dist[dst] == Integer.MAX_VALUE ? -1 : dist[dst];
    }

    public static void main(String[] args) {
        findCheapestPrice(5, new int[][]{
                {0, 1, 5},
                {1, 2, 5},
                {0, 3, 2},
                {3, 1, 2},
                {1, 4, 1},
                {4, 2, 1}
        }, 0, 2, 2);
    }
}
