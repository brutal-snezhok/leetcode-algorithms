package graphs.medium;

import java.util.LinkedList;
import java.util.Queue;

// https://leetcode.com/problems/snakes-and-ladders/
public class SnakesAndLadders_909 {
    public int snakesAndLadders(int[][] board) {
        // time O(n^2)
        // space O(n^2)

        int n = board.length;
        reverseBoard(board);
        boolean[] visited = new boolean[n * n + 1];
        Queue<int[]> q = new LinkedList<>(); // numOfSquare, steps
        visited[1] = true;
        q.add(new int[]{1, 0});
        while (!q.isEmpty()) {
            int[] curr = q.poll();
            for (int i = 1; i <= 6; i++) {
                int next = curr[0] + i;
                int[] coorNext = squareToCoor(next, n); // coor[0] = row, coor[1] = col
                int rowNext = coorNext[0];
                int colNext = coorNext[1];

                if (board[rowNext][colNext] != -1)
                    next = board[rowNext][colNext];

                if (next == n * n)
                    return curr[1] + 1;

                if (!visited[next]) {
                    visited[next] = true;
                    q.add(new int[]{next, curr[1] + 1});
                }
            }
        }

        return -1;
    }

    private void reverseBoard(int[][] board) {
        int l = 0;
        int r = board.length - 1;
        while (l < r) {
            int[] temp = board[l];
            board[l] = board[r];
            board[r] = temp;
            l++;
            r--;
        }
    }

    private int[] squareToCoor(int square, int n) {
        int row = (square - 1) / n;
        int col = (square - 1) % n;
        if (row % 2 != 0)
            col = n - 1 - col;

        return new int[]{row, col};
    }
}
