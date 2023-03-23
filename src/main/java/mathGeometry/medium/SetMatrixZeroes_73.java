package mathGeometry.medium;

// https://leetcode.com/problems/set-matrix-zeroes/
public class SetMatrixZeroes_73 {
    public static void setZeroes(int[][] matrix) {
        // time O(m * n)
        // space O(1)

        // use first cell as marker whether this row and col should be changed to 0

        boolean firstRow = false;
        int m = matrix.length;
        int n = matrix[0].length;

        for (int r = 0; r < m; r++)
            for (int c = 0; c < n; c++) {
                if (matrix[r][c] == 0) {
                    matrix[0][c] = 0;
                    if (r == 0)
                        firstRow = true;
                    else
                        matrix[r][0] = 0;
                }
            }

        for (int r = 1; r < m; r++)
            for (int c = 1; c < n; c++)
                if (matrix[0][c] == 0 || matrix[r][0] == 0)
                    matrix[r][c] = 0;

        if (matrix[0][0] == 0)
            for (int r = 1; r < m; r++)
                matrix[r][0] = 0;

        if (firstRow)
            for (int c = 0; c < n; c++)
                matrix[0][c] = 0;
    }

    public static void main(String[] args) {
        setZeroes(new int[][]{
                {0, 1, 2, 0},
                {3, 4, 5, 2},
                {1, 3, 1, 5}
        });
    }
}
