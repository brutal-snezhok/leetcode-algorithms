package graphs.medium.topologicalSort;

import java.util.*;

// https://leetcode.com/problems/course-schedule-ii/
public class CourseScheduleII_210 {
    // solution1
    public int[] findOrder1(int numCourses, int[][] prerequisites) {
        // topological sort
        // time O(V + E)
        // space O(V + E)

        boolean[] visited = new boolean[numCourses];
        boolean[] visiting = new boolean[numCourses];

        Map<Integer, List<Integer>> preMap = new HashMap<>();
        for (int i = 0; i < numCourses; i++)
            preMap.put(i, new ArrayList<>());

        for (int[] p : prerequisites)
            preMap.get(p[0]).add(p[1]);

        Deque<Integer> stack = new ArrayDeque<>();
        for (int i = 0; i < numCourses; i++) {
            if (visited[i])
                continue;
            if (isCyclic(i, visited, visiting, preMap, stack))
                return new int[0];
        }

        int[] res = new int[numCourses];
        int i = 0;
        while (!stack.isEmpty())
            res[i++] = stack.removeLast();

        return res;
    }

    private boolean isCyclic(int v, boolean[] visited, boolean[] visiting, Map<Integer, List<Integer>> preMap, Deque<Integer> stack) {
        if (visiting[v])
            return true;
        if (visited[v])
            return false;

        visiting[v] = true;
        for (int child : preMap.get(v)) {
            if (isCyclic(child, visited, visiting, preMap, stack))
                return true;
        }
        visiting[v] = false;
        visited[v] = true;
        stack.addFirst(v);

        return false;
    }

    // solution2
    public int[] findOrder2(int n, int[][] prerequisites) {
        // Kahn's BFS Based, topological sort
        // time O(V + E)
        // space O(V + E)

        Map<Integer, List<Integer>> graph = new HashMap<>(); // {parent, [children]}
        Map<Integer, Integer> indegree = new HashMap<>(); // {node, degree}

        // init graph
        for(int i = 0; i < n; i++)
            indegree.put(i, 0);

        // build graph
        for(int[] pre : prerequisites) {
            int parent = pre[1];
            int child = pre[0];
            graph.computeIfAbsent(parent, v -> new ArrayList<>()).add(child);
            indegree.put(child, indegree.get(child) + 1);
        }

        Queue<Integer> sources = new LinkedList<>();
        // put to queue all sources with degree 0
        for(Map.Entry<Integer, Integer> entry : indegree.entrySet()) {
            if(entry.getValue() == 0)
                sources.add(entry.getKey());
        }

        List<Integer> resList = new ArrayList<>();
        while(!sources.isEmpty()) {
            int vertex = sources.poll();
            resList.add(vertex);
            for(int child : graph.getOrDefault(vertex, new ArrayList<>())) {
                indegree.put(child, indegree.get(child) - 1);
                if(indegree.get(child) == 0)
                    sources.add(child);
            }
        }

        if(resList.size() != n)
            return new int[0];

        int[] resArr = new int[resList.size()];
        for(int i = 0; i < resList.size(); i++)
            resArr[i] = resList.get(i);

        return resArr;
    }

    public static void main(String[] args) {
        CourseScheduleII_210 course = new CourseScheduleII_210();
//        course.findOrder(2, new int[][]{
//                {1, 0},
//                {0, 1}
//        });

        course.findOrder2(4, new int[][]{
                {1,0},
                {2,0},
                {3,1},
                {3,2}
        });
    }
}
