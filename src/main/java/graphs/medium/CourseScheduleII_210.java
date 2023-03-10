package graphs.medium;

import java.util.*;

// https://leetcode.com/problems/course-schedule-ii/
public class CourseScheduleII_210 {
    int ind = 0;

    public int[] findOrder(int numCourses, int[][] prerequisites) {
        // topological sort
        // time O(E + V)
        // space O(E + V)

        // build adjacency list
        Map<Integer, List<Integer>> prereqMap = new HashMap<>();
        for (int i = 0; i < numCourses; i++)
            prereqMap.put(i, new ArrayList<>());

        for (int[] prereq : prerequisites)
            prereqMap.get(prereq[0]).add(prereq[1]);

        // a course has 3 possible states:
        // visited - crs has been added to output
        // visiting - crs not added to output, but added to cycle
        // unvisited - crs not added to output or cycle

        int[] res = new int[numCourses];
        Set<Integer> visit = new HashSet<>();
        Set<Integer> cycle = new HashSet<>();

        for (int i = 0; i < numCourses; i++)
            if (dfs(visit, cycle, i, prereqMap, res) == false)
                return new int[0];

        return res;
    }

    private boolean dfs(Set<Integer> visit, Set<Integer> cycle, int crs, Map<Integer, List<Integer>> prereqMap, int[] res) {
        if (cycle.contains(crs))
            return false;

        if (visit.contains(crs))
            return true;

        cycle.add(crs);
        for (Integer prereq : prereqMap.get(crs))
            if (dfs(visit, cycle, prereq, prereqMap, res) == false)
                return false;

        cycle.remove(crs);
        visit.add(crs);
        res[ind++] = crs;

        return true;
    }

    public static void main(String[] args) {
        CourseScheduleII_210 course = new CourseScheduleII_210();
        course.findOrder(2, new int[][]{
                {1, 0}
        });
    }
}
