package arraysHashing.easy.matrix;

// https://leetcode.com/problems/determine-whether-matrix-can-be-obtained-by-rotation/
public class DetermineWhetherMatrixCanBeObtainedByRotation_1886 {
    public boolean findRotation(int[][] mat, int[][] target) {
        // time O(n * n)
        // space O(1)

        // compare matrices
        // rotation on 90 degrees means:
        // 1. reverseRowsOrder
        // 2. transparent matrix

        for (int i = 0; i < 4; i++) {
            if (isEqual(mat, target))
                return true;

            reverseRowsOrder(mat);
            transparent(mat);
        }

        return false;
    }

    private boolean isEqual(int[][] mat, int[][] target) {
        int n = mat.length;

        for (int r = 0; r < n; r++)
            for (int c = 0; c < n; c++)
                if (mat[r][c] != target[r][c])
                    return false;

        return true;
    }

    private void reverseRowsOrder(int[][] mat) {
        int l = 0;
        int r = mat.length - 1;
        while (l < r) {
            int[] temp = mat[l];
            mat[l] = mat[r];
            mat[r] = temp;
            l++;
            r--;
        }
    }

    private void transparent(int[][] mat) {
        int n = mat.length;

        for (int r = 0; r < n; r++)
            for (int c = r; c < n; c++) {
                if(r != c) {
                    int temp = mat[r][c];
                    mat[r][c] = mat[c][r];
                    mat[c][r] = temp;
                }
            }
    }

    public static void main(String[] args) {
        DetermineWhetherMatrixCanBeObtainedByRotation_1886 d = new DetermineWhetherMatrixCanBeObtainedByRotation_1886();
//        d.findRotation(new int[][]{
//                {0,0,0},
//                {0,1,0},
//                {1,1,1}
//        }, new int[][]{
//                {1,1,1},
//                {0,1,0},
//                {0,0,0}
//        });

        d.findRotation(new int[][]{
                {0, 0},
                {0, 1}
        }, new int[][] {
                {0 ,0},
                {1, 0}
        });
    }
}
