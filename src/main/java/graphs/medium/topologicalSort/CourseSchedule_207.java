package graphs.medium.topologicalSort;

import java.util.*;

// https://leetcode.com/problems/course-schedule/description/
public class CourseSchedule_207 {
    // solution1
    public boolean canFinish1(int numCourses, int[][] prerequisites) {
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

    // solution2
    public boolean canFinish2(int n, int[][] prerequisites) {
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
