package graphs.medium;

import java.util.*;

// https://leetcode.com/problems/01-matrix/description/
public class Matrix01_542 {
    int[][] directions = {{1, 0}, {0, 1}, {-1, 0}, {0, -1}};

    public int[][] updateMatrix(int[][] mat) {
        // time O(n*m)
        // space O(n*m) for queue

        /**
         1. find all 0 and add x,y of them to queue
         2. run bfs and put distance in res matrix
         */

        Set<Pair> visited = new HashSet<>();
        Queue<Pair> q = new LinkedList<>();
        final int ROWS = mat.length;
        final int COLS = mat[0].length;

        for (int r = 0; r < ROWS; r++)
            for (int c = 0; c < COLS; c++)
                if (mat[r][c] == 0) {
                    q.add(new Pair(r, c));
                    visited.add(new Pair(r, c));
                }


        int[][] res = new int[ROWS][COLS];
        bfs(res, visited, ROWS, COLS, q);

        return res;
    }

    private void bfs(int[][] res, Set<Pair> visited, int ROWS, int COLS, Queue<Pair> q) {
        while (!q.isEmpty()) {
            Pair curr = q.poll();

            for (int[] dir : directions) {
                int newX = curr.r + dir[0];
                int newY = curr.c + dir[1];

                if (newX < 0 || newX >= ROWS || newY < 0 || newY >= COLS || visited.contains(new Pair(newX, newY)))
                    continue;

                res[newX][newY] = res[curr.r][curr.c] + 1;
                q.add(new Pair(newX, newY));
                visited.add(new Pair(newX, newY));
            }
        }
    }

    class Pair {
        int r;
        int c;

        public Pair(int r, int c) {
            this.r = r;
            this.c = c;
        }

        public boolean equals(Object o) {
            if (o == null || o.getClass() != this.getClass())
                return false;
            if (o == this)
                return true;

            Pair p = (Pair) o;
            return this.r == p.r && this.c == p.c;
        }

        public int hashCode() {
            return Objects.hash(r, c);
        }
    }
}
