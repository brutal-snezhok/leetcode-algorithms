package graphs.medium.topologicalSort;

import java.util.*;

// https://leetcode.com/problems/course-schedule-ii/
public class CourseScheduleII_210 {
    // solution1
    public int[] findOrder1(int n, int[][] prerequisites) {
        // dfs + visited + visiting arrays, topological sort
        // time O(V + E)
        // space O(V + E)

        // a course has 3 possible states:
        // visited - crs has been added to output
        // visiting - crs not added to output, but added to cycle
        // unvisited - crs not added to output or cycle

        Map<Integer, List<Integer>> adjMap = new HashMap<>();
        for (int i = 0; i < n; i++)
            adjMap.put(i, new ArrayList<>());

        // populate adjMap
        for (int[] pre : prerequisites)
            adjMap.get(pre[0]).add(pre[1]);

        boolean visiting[] = new boolean[n];
        boolean visited[] = new boolean[n];
        List<Integer> rightOrder = new ArrayList<>();

        for (int v = 0; v < n; v++)
            if (!visited[v] && isCycle(v, adjMap, visiting, visited, rightOrder))
                return new int[]{};

        int[] res = new int[n];
        for (int i = 0; i < n; i++)
            res[i] = rightOrder.get(i);

        return res;
    }

    private boolean isCycle(int v, Map<Integer, List<Integer>> adjMap,
                            boolean[] visiting, boolean[] visited, List<Integer> rightOrder) {
        if (visiting[v])
            return true;
        if (visited[v])
            return false;

        visiting[v] = true;
        for (int child : adjMap.getOrDefault(v, new ArrayList<>()))
            if (isCycle(child, adjMap, visiting, visited, rightOrder))
                return true;
        visiting[v] = false;
        visited[v] = true;
        rightOrder.add(v);

        return false;
    }

    // solution2
    public int[] findOrder2(int n, int[][] prerequisites) {
        // dfs + color array, topological sort
        // time O(V + E)
        // space O(V + E)

        Map<Integer, List<Integer>> adjMap = new HashMap<>();
        for(int i = 0; i < n; i++)
            adjMap.put(i, new ArrayList<>());

        // populate adjMap
        for(int[] pre : prerequisites)
            adjMap.get(pre[0]).add(pre[1]);

        /* 0 - not visited WHITE
           1 - processing GREY
           2 - processed BLACK */
        int[] color = new int[n];
        List<Integer> rightOrder = new ArrayList<>();

        for(int v = 0; v < n; v++)
            if(color[v] == 0 && isCycle(v, adjMap, color, rightOrder))
                return new int[]{};

        int[] res = new int[n];
        for(int i = 0; i < n; i++)
            res[i] = rightOrder.get(i);

        return res;
    }

    private boolean isCycle(int v, Map<Integer, List<Integer>> adjMap, int[] color, List<Integer> rightOrder) {
        if(color[v] == 1)
            return true;
        if(color[v] == 2)
            return false;

        color[v] = 1;
        for(int child : adjMap.getOrDefault(v, new ArrayList<>()))
            if(isCycle(child, adjMap, color, rightOrder))
                return true;
        color[v] = 2;
        rightOrder.add(v);

        return false;
    }

    // solution3
    public int[] findOrder3(int n, int[][] prerequisites) {
        // bfs, topological sort
        // time O(V + E)
        // space O(V + E)

        Map<Integer, List<Integer>> adjMap = new HashMap<>();
        Map<Integer, Integer> inDegree = new HashMap<>();
        for(int i = 0; i < n; i++) {
            adjMap.put(i, new ArrayList<>());
            inDegree.put(i, 0);
        }

        // populate adjMap
        for(int[] pre : prerequisites) {
            adjMap.get(pre[0]).add(pre[1]);
            inDegree.put(pre[1], inDegree.get(pre[1]) + 1);
        }

        Queue<Integer> q = new LinkedList<>();
        for(Map.Entry<Integer, Integer> entry : inDegree.entrySet())
            if(entry.getValue() == 0)
                q.add(entry.getKey());

        List<Integer> resList = new ArrayList<>();

        while(!q.isEmpty()) {
            int vertex = q.poll();
            resList.add(vertex);

            for(int child : adjMap.getOrDefault(vertex, new ArrayList<>())) {
                inDegree.put(child, inDegree.get(child) - 1);

                if(inDegree.get(child) == 0)
                    q.add(child);
            }
        }

        if (resList.size() != n)
            return new int[0];

        int[] resArr = new int[n];
        int j = 0;
        for (int i = n - 1; i >= 0; i--)
            resArr[j++] = resList.get(i);

        return resArr;
    }

    public static void main(String[] args) {
        CourseScheduleII_210 course = new CourseScheduleII_210();
//        course.findOrder(2, new int[][]{
//                {1, 0},
//                {0, 1}
//        });

        course.findOrder2(4, new int[][]{
                {1, 0},
                {2, 0},
                {3, 1},
                {3, 2}
        });
    }
}
