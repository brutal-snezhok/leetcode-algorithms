package graphs.medium;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

// https://leetcode.com/problems/all-paths-from-source-to-target/description/
public class AllPathsFromSourceToTarget_797 {
    // TODO: take a look at better solution
    // https://leetcode.com/problems/all-paths-from-source-to-target/solutions/986429/python-iterative-dfs-with-detailed-time-complexity-visuals/

    // solution1
    public List<List<Integer>> allPathsSourceTarget1(int[][] graph) {
        // backtracking
        // time O(2^n)
        // space O(n)

        List<List<Integer>> res = new ArrayList<>();
        int n = graph.length;
        dfs(graph, 0, n - 1, res, new ArrayList<>());

        return res;
    }

    private void dfs(int[][] graph, int currNode, int dest, List<List<Integer>> res, List<Integer> curr) {
        if(currNode == dest) {
            curr.add(currNode);
            res.add(new ArrayList<>(curr));
            curr.remove(curr.size() - 1);
            return;
        }

        curr.add(currNode);
        int[] adjNodes = graph[currNode];
        for(int node : adjNodes) {
            dfs(graph, node, dest, res, curr);
        }

        curr.remove(curr.size() - 1);
    }

    // solution2
    public List<List<Integer>> allPathsSourceTarget2(int[][] graph) {
        // bfs
        // time O(2^n)
        // space O(n)

        List<List<Integer>> res = new ArrayList<>();
        int n = graph.length;
        Queue<List<Integer>> q = new LinkedList<>();

        List<Integer> curr = new ArrayList<>();
        curr.add(0);

        q.add(curr);

        while(!q.isEmpty()) {
            List<Integer> path = q.poll();

            int lastV = path.get(path.size() - 1);
            if(lastV == n - 1) {
                res.add(path);
                continue;
            }

            for(int v : graph[lastV]) {
                List<Integer> newPath = new ArrayList<>(path);
                newPath.add(v);
                q.add(newPath);
            }
        }

        return res;
    }
}
