package graphs.medium;

import java.util.*;

// https://leetcode.com/problems/course-schedule/description/
public class CourseSchedule_207 {
    public boolean canFinish(int numCourses, int[][] prerequisites) {
        // time O(n + m)
        // space O(n + m)

        if (prerequisites.length == 0)
            return true;

        Map<Integer, List<Integer>> preMap = new HashMap<>();
        for (int[] req : prerequisites)
            preMap.computeIfAbsent(req[0], k -> new ArrayList<>()).add(req[1]);

        Set<Integer> visited = new HashSet<>();

        for (int[] req : prerequisites)
            if (!couldCourseBeFinished(req[0], preMap, visited)) // if some course can't be finished
                return false;

        return true;
    }

    // detect cycles, if found cycle return false, else return true
    private boolean couldCourseBeFinished(int crs, Map<Integer, List<Integer>> preMap, Set<Integer> visited) {
        if (visited.contains(crs))
            return false;
        if (!preMap.containsKey(crs) || preMap.get(crs).isEmpty())
            return true;

        visited.add(crs);
        for (int req : preMap.get(crs))
            if (!couldCourseBeFinished(req, preMap, visited))
                return false;
        visited.remove(crs);
        preMap.get(crs).clear();

        return true;
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

        course.canFinish(3, new int[][]{
                {0, 1},
                {1, 2},
                {2, 0}
        });
    }
}
