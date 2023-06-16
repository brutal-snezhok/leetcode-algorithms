package graphs.medium.dijkstra;

import java.util.*;

// https://leetcode.com/problems/network-delay-time/
public class NetworkDelayTime_743 {
    // solution1
    public int networkDelayTime(int[][] times, int n, int k) {
        // Dijkstra's algorithm on minHeap
        // time O(V + ElogV)
        // space O(V + E)

        Map<Integer, List<Pair>> map = new HashMap<>();
        for(int i = 1; i <= n; i++)
            map.put(i, new ArrayList<>());

        // populate adjMap
        for(int[] time : times)
            map.get(time[0]).add(new Pair(time[1], time[2]));

        Queue<Pair> minHeap = new PriorityQueue<>((p1, p2) -> p1.weight - p2.weight);
        minHeap.add(new Pair(k, 0));
        Set<Integer> visited = new HashSet<>();
        int res = 0;

        while(!minHeap.isEmpty()) {
            Pair p = minHeap.poll();
            if(visited.contains(p.v))
                continue;

            visited.add(p.v);
            res = Math.max(res, p.weight);

            // go through all neighbors
            for(Pair curr : map.get(p.v)) {
                if(!visited.contains(curr.v))
                    minHeap.add(new Pair(curr.v, p.weight + curr.weight));
            }
        }

        return visited.size() == n ? res : -1;
    }

    class Pair {
        int v;
        int weight;

        public Pair(int v, int weight) {
            this.v = v;
            this.weight = weight;
        }
    }

    /*
    * variant without Pair but with arr int[]
    * public int networkDelayTime(int[][] times, int n, int k) {
        // Dijkstra on minHeap
        // time O(ElogV)
        // space O(V + E)

        Map<Integer, List<int[]>> adjMap = new HashMap<>(); // node1 -> [node2, weight]
        for(int[] time : times)
            adjMap.computeIfAbsent(time[0], v -> new ArrayList<>()).add(new int[]{time[1], time[2]});

        // [node, currWeight]
        Queue<int[]> minHeap = new PriorityQueue<>((arr1, arr2) -> arr1[1] - arr2[1]);
        minHeap.add(new int[]{k, 0});
        Set<Integer> visited = new HashSet<>();
        int res = 0;

        while(!minHeap.isEmpty()) {
            int[] node = minHeap.poll();

            if(visited.contains(node[0]))
                continue;
            visited.add(node[0]);
            res = Math.max(res, node[1]);

            List<int[]> adjNodes = adjMap.getOrDefault(node[0], new ArrayList<>());
            for(int[] adjNode : adjNodes) {
                if(visited.contains(adjNode[0]))
                    continue;

                minHeap.add(new int[]{adjNode[0], node[1] + adjNode[1]});
            }
        }

        return visited.size() == n ? res : -1;
    }
    * */


    // solution2
    public int networkDelayTime2(int[][] times, int n, int k) {
        // https://www.geeksforgeeks.org/dijkstras-shortest-path-algorithm-greedy-algo-7/
        // Dijkstra's algorithm on array
        // time O(V^2)
        // space O(V + E)

        Map<Integer, List<Pair>> map = new HashMap<>();
        for(int i = 1; i <= n; i++)
            map.put(i, new ArrayList<>());

        for(int[] time : times)
            map.get(time[0]).add(new Pair(time[1], time[2]));

        Set<Integer> visited = new HashSet<>();
        int[] dist = new int[n + 1];
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[k] = 0;

        for (int vertex = 1; vertex <= n; vertex++) {
            // Pick the minimum distance vertex from the set
            // of vertices not yet processed. u is always
            // equal to src in first iteration.
            int u = minDistance(dist, visited, n);

            // Mark the picked vertex as processed
            visited.add(u);

            // Update dist value of the adjacent vertices of
            // the picked vertex.
            for (Pair curr : map.get(u))
                if(!visited.contains(curr.v) && dist[curr.v] > dist[u] + curr.weight)
                    dist[curr.v] = dist[u] + curr.weight;
        }

        int res = 0;
        for(int i = 1; i <= n; i++)
            res = Math.max(res, dist[i]);

        return res == Integer.MAX_VALUE ? -1 : res;
    }

    /* variant without Pair but with arr int[]
    *  public int networkDelayTime(int[][] times, int n, int k) {
        // Dijkstra on array
        // time O(V^2)
        // space O(V + E)

        Map<Integer, List<int[]>> adjMap = new HashMap<>(); // node1 -> [node2, weight]
        for(int[] time : times)
            adjMap.computeIfAbsent(time[0], v -> new ArrayList<>()).add(new int[]{time[1], time[2]});

        int[] dist = new int[n + 1];
        Arrays.fill(dist, Integer.MAX_VALUE);
        Set<Integer> visited = new HashSet<>();
        dist[k] = 0;

        for(int v = 1; v <= n; v++) {
            int u = getVertexWithMinDistance(dist, visited, n);

            visited.add(u);

            for(int[] curr : adjMap.getOrDefault(u, new ArrayList<>()))
                if(!visited.contains(curr[0]) && dist[curr[0]] > dist[u] + curr[1])
                    dist[curr[0]] = dist[u] + curr[1];
        }

        int res = 0;
        for(int i = 1; i <= n; i++)
            res = Math.max(res, dist[i]);

        return res == Integer.MAX_VALUE ? -1 : res;
    }
    * */

    private int minDistance(int dist[], Set<Integer> visited, int n) {
        // Initialize min value
        int min = Integer.MAX_VALUE, min_index = -1;

        for (int v = 1; v <= n; v++)
            if(!visited.contains(v) && dist[v] <= min) {
                min = dist[v];
                min_index = v;
            }

        return min_index;
    }

    public static void main(String[] args) {
        NetworkDelayTime_743 network = new NetworkDelayTime_743();
        network.networkDelayTime(new int[][]{
                {2,1,1},
                {2,3,1},
                {3,4,1}
        }, 4, 2);

    }
}
