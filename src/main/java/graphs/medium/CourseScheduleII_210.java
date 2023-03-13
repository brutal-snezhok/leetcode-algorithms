package graphs.medium;

import java.util.*;

// https://leetcode.com/problems/course-schedule-ii/
public class CourseScheduleII_210 {
    public int[] findOrder(int numCourses, int[][] prerequisites) {
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

    public static void main(String[] args) {
        CourseScheduleII_210 course = new CourseScheduleII_210();
//        course.findOrder(2, new int[][]{
//                {1, 0},
//                {0, 1}
//        });

        course.findOrder(4, new int[][]{
                {1,0},
                {2,0},
                {3,1},
                {3,2}
        });
    }
}
