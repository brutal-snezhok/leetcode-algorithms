package graphs.medium;

import java.util.ArrayList;
import java.util.List;

// https://leetcode.com/problems/all-paths-from-source-to-target/description/
public class AllPathsFromSourceToTarget_797 {
    // TODO: take a look at better solution
    // https://leetcode.com/problems/all-paths-from-source-to-target/solutions/986429/python-iterative-dfs-with-detailed-time-complexity-visuals/

    // solution1
    public List<List<Integer>> allPathsSourceTarget(int[][] graph) {
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
}
