package arraysHashing.easy.matrix;

// https://leetcode.com/problems/matrix-diagonal-sum/description/
public class MatrixDiagonalSum_1572 {
    // solution1
    public int diagonalSum1(int[][] mat) {
        // time O(n * m)
        // space O(1)

        // r == c -> primary diag
        // r + c = n - 1 -> secondary diag

        int sum = 0;
        final int n = mat.length;

        for(int r = 0; r < n; r++)
            for(int c = 0; c < n; c++)
                if(r == c || r + c == n - 1)
                    sum += mat[r][c];

        return sum;
    }

    // solution2
    public int diagonalSum2(int[][] mat) {
        // time O(n), n - number of els in row
        // space O(1)

        // r == c -> primary diag
        // r + c = n - 1 -> secondary diag

        int sum = 0;
        final int n = mat.length;

        for(int i = 0; i < n; i++) {
            sum += mat[i][i];
            sum += mat[i][n - 1 - i];
        }

        if(n % 2 == 1)
            sum -= mat[n / 2][n / 2];

        return sum;
    }
}
