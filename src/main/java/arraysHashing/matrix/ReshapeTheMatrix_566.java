package arraysHashing.matrix;

public class ReshapeTheMatrix_566 {
    public static int[][] matrixReshape(int[][] mat, int r, int c) {
        // time O(n * m)
        // space O(r * c)

        int m = mat.length;
        int n = mat[0].length;
        if(m * n != r * c)
            return mat;

        int[][] res = new int[r][c];
        for(int i = 0; i < m; i++) {
            for(int j = 0; j < n; j++) {
                int indOneDimArr = n * i + j;
                res[indOneDimArr / c][indOneDimArr % c] = mat[i][j];
            }
        }

        return res;
    }

    public static void main(String[] args) {
        matrixReshape(new int[][]{
                {1, 2},
                {3, 4}
        }, 1, 4);
    }
}
