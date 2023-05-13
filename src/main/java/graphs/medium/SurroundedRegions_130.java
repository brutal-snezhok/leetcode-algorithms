package graphs.medium;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

// https://leetcode.com/problems/surrounded-regions/description/
public class SurroundedRegions_130 {
    // solution1
    public void solve1(char[][] board) {
        // time O(n*m)
        // space O(n*m)

        final int ROWS = board.length;
        final int COLS = board[0].length;

        if (ROWS < 3 || COLS < 3)
            return;

        // 1. run dfs from "O" cells near border and change them into "M" as marked
        for (int r = 0; r < ROWS; r++) {
            for (int c = 0; c < COLS; c++) {
                if (board[r][c] == 'O' && (r == 0 || r == ROWS - 1 || c == 0 || c == COLS - 1))
                    dfs(board, ROWS, COLS, r, c);
            }
        }

        // 2. go through all cells and change all rest "O" into "X"
        for (int r = 0; r < ROWS; r++) {
            for (int c = 0; c < COLS; c++) {
                if (board[r][c] == 'O')
                    board[r][c] = 'X';
            }
        }

        // 3. scan all matrix and convert "M" cells back to O
        for (int r = 0; r < ROWS; r++) {
            for (int c = 0; c < COLS; c++) {
                if (board[r][c] == 'M')
                    board[r][c] = 'O';
            }
        }
    }

    private void dfs(char[][] board, int ROWS, int COLS, int r, int c) {
        if (r < 0 || r >= ROWS || c < 0 || c >= COLS || board[r][c] != 'O')
            return;

        board[r][c] = 'M';
        dfs(board, ROWS, COLS, r + 1, c);
        dfs(board, ROWS, COLS, r - 1, c);
        dfs(board, ROWS, COLS, r, c + 1);
        dfs(board, ROWS, COLS, r, c - 1);
    }

    // solution2, with set
    public void solve2(char[][] matrix) {
        // time O(n*m)
        // space O(n*m)

        final int ROWS = matrix.length;
        final int COLS = matrix[0].length;

        if(ROWS == 0 && COLS == 0)
            return;

        Set<Pair> visited = new HashSet<>(); // {row, col}

        // traverse by first row
        for(int i = 0; i < COLS; i++) {
            if(!visited.contains(new Pair(0, i)) && matrix[0][i] == 'O')
                dfs(matrix, 0, i, visited);
        }

        // by last row
        for(int i = 0; i < COLS; i++) {
            if(!visited.contains(new Pair(ROWS - 1, i)) && matrix[ROWS - 1][i] == 'O')
                dfs(matrix, ROWS - 1, i, visited);
        }

        // by first col
        for(int i = 0; i < ROWS; i++) {
            if(!visited.contains(new Pair(i, 0)) && matrix[i][0] == 'O')
                dfs(matrix, i, 0, visited);
        }

        // last col
        for(int i = 0; i < ROWS; i++) {
            if(!visited.contains(new Pair(i, COLS - 1)) && matrix[i][COLS - 1] == 'O')
                dfs(matrix, i, COLS - 1, visited);
        }

        System.out.println(visited);
        for(int r = 0; r < ROWS; r++) {
            for(int c = 0; c < COLS; c++) {
                if(!visited.contains(new Pair(r, c)) && matrix[r][c] == 'O')
                    matrix[r][c] = 'X';
            }
        }
    }

    int[][] directions = { {1, 0} , {-1, 0}, {0, 1}, {0, -1} };

    private void dfs(char[][] matrix, int r, int c, Set<Pair> visited) {
        final int ROWS = matrix.length;
        final int COLS = matrix[0].length;

        if(r >= ROWS || r < 0 || c >= COLS || c < 0 || visited.contains(new Pair(r, c)) || matrix[r][c] == 'X')
            return;

        visited.add(new Pair(r, c));
        for(int[] dir : directions)
            dfs(matrix, r + dir[0], c + dir[1], visited);
    }

    class Pair {
        int r;
        int c;

        public Pair(int r, int c) {
            this.r = r;
            this.c = c;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Pair pair = (Pair) o;
            return r == pair.r && c == pair.c;
        }

        @Override
        public int hashCode() {
            return Objects.hash(r, c);
        }
    }

    public static void main(String[] args) {
        char[][] matrix = new char[][] {
                {'X','X','X','O'},
                {'X','O','O','X'},
                {'X','X','O','X'},
                {'X','O','X','O'}
        };

        SurroundedRegions_130 surroundedRegions_130 = new SurroundedRegions_130();
        surroundedRegions_130.solve2(matrix);
    }
}
