package pramp;

import java.util.*;

/*
* In a given grid of 0s and 1s, we have some starting row and column sr, sc and a target row and column tr, tc.
* Return the length of the shortest path from sr, sc to tr, tc that walks along 1 values only.
* Each location in the path, including the start and the end, must be a 1.
* Each subsequent location in the path must be 4-directionally adjacent to the previous location.
It is guaranteed that grid[sr][sc] = grid[tr][tc] = 1, and the starting and target positions are different.
If the task is impossible, return -1.
* Examples:
input:
grid = [[1, 1, 1, 1], [0, 0, 0, 1], [1, 1, 1, 1]]
sr = 0, sc = 0, tr = 2, tc = 0
output: 8
(The lines below represent this grid:)
1111
0001
1111

grid = [[1, 1, 1, 1], [0, 0, 0, 1], [1, 0, 1, 1]]
sr = 0, sc = 0, tr = 2, tc = 0
output: -1
(The lines below represent this grid:)
1111
0001
1011
*
* Constraints:
[time limit] 5000ms
[input] array.array.integer grid
1 ≤ arr.length = arr[i].length ≤ 10
[input] integer sr
[input] integer sc
[input] integer tr
[input] integer tc
All sr, sc, tr, tc are valid locations in the grid, grid[sr][sc] = grid[tr][tc] = 1, and (sr, sc) != (tr, tc).
[output] integer
* */
public class ShortestCellPath {
    static int shortestCellPath(int[][] grid, int sr, int sc, int tr, int tc) {
        // add source cell to the queue
        // create var for number of layers
        // go in 4 directions
        // if meet 1 then add this point to the queue and mark this cell as visited
        // add check if meet target cell -> return numOfLayer
        // return -1 by default

        Queue<Pair> q = new LinkedList<>();
        q.add(new Pair(sr, sc));
        Set<Pair> visited = new HashSet<>();
        int[][] directions = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
        int num = 0;

        while (!q.isEmpty()) {
            int n = q.size();

            for (int i = 0; i < n; i++) {
                Pair curr = q.poll();

                if (curr.row == tr && curr.col == tc)
                    return num;

                for (int[] dir : directions) {
                    int newR = curr.row + dir[0];
                    int newC = curr.col + dir[1];
                    Pair newP = new Pair(newR, newC);

                    if (newR >= 0 && newR < grid.length && newC >= 0 && newC < grid[0].length && !visited.contains(newP)
                            && grid[newR][newC] == 1) {
                        q.add(newP);
                        visited.add(newP);
                    }
                }
            }

            num++;
        }

        return -1;
    }

    static class Pair {
        int row;
        int col;

        public Pair(int row, int col) {
            this.row = row;
            this.col = col;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Pair pair = (Pair) o;
            return row == pair.row && col == pair.col;
        }

        @Override
        public int hashCode() {
            return Objects.hash(row, col);
        }
    }

}
