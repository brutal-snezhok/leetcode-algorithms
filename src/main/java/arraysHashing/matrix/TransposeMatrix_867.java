package arraysHashing.matrix;

// https://leetcode.com/problems/transpose-matrix/description/
public class TransposeMatrix_867 {
    public int[][] transpose(int[][] matrix) {
        // time O(n * m)
        // space O(m * n)

        final int ROWS = matrix.length;
        final int COLS = matrix[0].length;

        int[][] res = new int[COLS][ROWS];
        for(int r = 0; r < ROWS; r++)
            for(int c = 0; c < COLS; c++)
                res[c][r] = matrix[r][c];

        return res;
    }
}
