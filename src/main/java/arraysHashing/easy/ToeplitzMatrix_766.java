package arraysHashing.easy;

// https://leetcode.com/problems/toeplitz-matrix/description/
public class ToeplitzMatrix_766 {
    public boolean isToeplitzMatrix(int[][] matrix) {
        // time O(n * m)
        // space O(1)

        int m = matrix.length;
        int n = matrix[0].length;

        for(int r = 0; r < m; r++)
            for(int c = 0; c < n; c++) {
                int newR = r + 1;
                int newC = c + 1;

                if(isValid(newR, newC, m, n) && matrix[r][c] != matrix[newR][newC])
                    return false;
            }

        return true;
    }

    private boolean isValid(int r, int c, int m, int n) {
        return r >= 0 && r < m && c >= 0 && c < n;
    }
}
