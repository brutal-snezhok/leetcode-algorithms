package graphs.medium;

import java.util.*;

// https://leetcode.com/problems/path-with-maximum-probability/description/
public class PathWithMaximumProbability_1514 {
    public double maxProbability(int n, int[][] edges, double[] succProb, int start, int end) {
        // check https://leetcode.com/problems/path-with-maximum-probability/solutions/731767/java-python-3-2-codes-bellman-ford-and-dijkstra-s-algorithm-w-brief-explanation-and-analysis/comments/786512
        // Dijkstra's algorithm on minHeap
        // time O((V + E) * log(V))
        // space O(V + E)

        // probability can't be bigger than 1
        // when use Dijkstra's algo instead of finding the shortest path, find max probability
        // you can achieve this with max heap

        Map<Integer, List<Pair>> adjMap = new HashMap<>();
        for(int i = 0; i < n; i++)
            adjMap.put(i, new ArrayList<>());
        for(int i = 0; i < edges.length; i++) {
            adjMap.get(edges[i][0]).add(new Pair(edges[i][1], succProb[i]));
            adjMap.get(edges[i][1]).add(new Pair(edges[i][0], succProb[i]));
        }

        boolean[] visited = new boolean[n];
        Queue<Pair> maxHeap = new PriorityQueue<>(Comparator.comparingDouble(Pair::getProb).reversed());
        maxHeap.add(new Pair(start, 1.0));

        while(!maxHeap.isEmpty()) {
            Pair pair = maxHeap.poll();

            if(pair.v == end)
                return pair.prob;

            if(visited[pair.v])
                continue;

            visited[pair.v] = true;
            for(Pair curr : adjMap.get(pair.v))
                maxHeap.add(new Pair(curr.v, curr.prob * pair.prob));
        }

        return 0d;
    }

    class Pair {
        int v;
        double prob;

        public Pair(int v, double prob) {
            this.v = v;
            this.prob = prob;
        }

        public double getProb() {
            return prob;
        }
    }
}
