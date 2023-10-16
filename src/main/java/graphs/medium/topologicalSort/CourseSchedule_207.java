package graphs.medium.topologicalSort;

import java.util.*;

// https://leetcode.com/problems/course-schedule/description/
public class CourseSchedule_207 {
    // solution1
    public boolean canFinish1(int numCourses, int[][] prerequisites) {
        // dfs + visiting + visited arrays
        // time O(V + E)
        // space O(V + E)

        Map<Integer, List<Integer>> adjMap = new HashMap<>();

        for(int[] pre : prerequisites)
            adjMap.computeIfAbsent(pre[0], v -> new ArrayList<>()).add(pre[1]);

        boolean[] visited = new boolean[numCourses];
        boolean[] visiting = new boolean[numCourses];

        for(int i = 0; i < numCourses; i++)
            if(!visited[i] && isCycle(i, adjMap, visited, visiting))
                return false;

        return true;
    }

    private boolean isCycle(int v, Map<Integer, List<Integer>> adjMap, boolean[] visited, boolean[] visiting) {
        if(visiting[v])
            return true;

        if(visited[v])
            return false;

        visiting[v] = true;
        for(Integer neighbour : adjMap.getOrDefault(v, new ArrayList<>()))
            if(isCycle(neighbour, adjMap, visited, visiting))
                return true;

        visiting[v] = false;
        visited[v] = true;
        return false;
    }

    // solution2
    public boolean canFinish2(int numCourses, int[][] prerequisites) {
        // dfs + color array
        // time O(V + E)
        // space O(V + E)

        Map<Integer, List<Integer>> adjMap = new HashMap<>();

        for(int[] pre : prerequisites)
            adjMap.computeIfAbsent(pre[0], v -> new ArrayList<>()).add(pre[1]);

        /* 0 - not visited WHITE
           1 - processing GREY
           2 - processed BLACK */
        int[] color = new int[numCourses];

        for(int i = 0; i < numCourses; i++)
            if(color[i] == 0 && isCycle(i, adjMap, color))
                return false;

        return true;
    }

    private boolean isCycle(int v, Map<Integer, List<Integer>> adjMap, int[] color) {
        if(color[v] == 1)
            return true;
        if(color[v] == 2)
            return false;

        color[v] = 1;
        for(Integer neighbour : adjMap.getOrDefault(v, new ArrayList<>()))
            if(isCycle(neighbour, adjMap, color))
                return true;

        color[v] = 2;

        return false;
    }

    // solution3
    public boolean canFinish3(int n, int[][] prerequisites) {
        // Kahn's BFS Based, topological sort
        // time O(V + E)
        // space O(V + E)

        Map<Integer, List<Integer>> map = new HashMap<>();
        Map<Integer, Integer> inDegree = new HashMap<>();

        // init graph
        for(int i = 0; i < n; i++) {
            map.put(i, new ArrayList<>());
            inDegree.put(i, 0);
        }

        // build graph
        for(int[] pre : prerequisites) {
            int parent = pre[0];
            int child = pre[1];
            map.get(parent).add(child);
            inDegree.put(child, inDegree.get(child) + 1);
        }

        Queue<Integer> sources = new LinkedList<>();
        for(Map.Entry<Integer, Integer> entry : inDegree.entrySet())
            if(entry.getValue() == 0)
                sources.add(entry.getKey());

        int res = 0;
        while(!sources.isEmpty()) {
            int vertex = sources.poll();
            res++;
            for(int child : map.get(vertex)) {
                inDegree.put(child, inDegree.get(child) - 1);
                if(inDegree.get(child) == 0)
                    sources.add(child);
            }

        }

        return res == n;
    }

    public static void main(String[] args) {
        CourseSchedule_207 course = new CourseSchedule_207();
//        course.canFinish(5, new int[][]{
//                {0, 1},
//                {0, 2},
//                {1, 3},
//                {1, 4},
//                {3, 4}
//        });

        course.canFinish1(3, new int[][]{
                {0, 1},
                {1, 2},
                {2, 0}
        });
    }
}
