package graphs.medium;

// https://leetcode.com/problems/surrounded-regions/description/
public class SurroundedRegions_130 {
    public void solve(char[][] board) {
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
}
