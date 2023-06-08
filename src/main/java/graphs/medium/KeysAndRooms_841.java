package graphs.medium;

import java.util.*;

// https://leetcode.com/problems/keys-and-rooms/description/
public class KeysAndRooms_841 {
     // solution1
     /*     0   1       2   3
        [1,3],[3,0,1],[2],[0]

        0 -> 1,3
        1 -> 0,1,3
        2 -> 2
        3 -> 0

        dfs(0)

        return visited.size() == n
    */
    public boolean canVisitAllRooms(List<List<Integer>> rooms) {
        // dfs
        // time O(n + m), n - num of rooms, m - total num of keys
        // space O(n)

        Map<Integer, List<Integer>> map = new HashMap<>();
        for(int i = 0; i < rooms.size(); i++) {
            List<Integer> room = rooms.get(i);
            map.put(i, room);
        }

        Set<Integer> visited = new HashSet<>();
        dfs(map, visited, 0);

        return visited.size() == rooms.size();
    }

    private void dfs(Map<Integer, List<Integer>> map, Set<Integer> visited, int room) {
        if(visited.contains(room))
            return;

        visited.add(room);
        List<Integer> rooms = map.get(room);
        for(int r : rooms)
            dfs(map, visited, r);
    }
}
