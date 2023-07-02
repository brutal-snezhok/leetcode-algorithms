package arraysHashing.prefix_sum;

// https://leetcode.com/problems/range-sum-query-2d-immutable
public class RangeSumQuery2DImmutable_304 {
    int ROWS;
    int COLS;
    int[][] m;

    public RangeSumQuery2DImmutable_304(int[][] matrix) {
        // time O(m*n) precomputation
        // space O(m*n)
        ROWS = matrix.length;
        COLS = matrix[0].length;
        m = new int[ROWS][COLS];

        for (int r = 0; r < ROWS; r++) {
            int prevSum = 0; // sum of all values from the same row lefter of curr cell

            for (int c = 0; c < COLS; c++) {
                int upSum = r == 0 ? 0 : m[r - 1][c]; // sum of all values which upper
                m[r][c] = upSum + prevSum + matrix[r][c];
                prevSum += matrix[r][c];
            }
        }
    }

    public int sumRegion(int r1, int c1, int r2, int c2) {
        // time O(1)
        // space O(1)
        // res = bigRec - upRec - leftRec + overlapRec
        // res = m[r2][c2] - m[r1 - 1][c2] - m[r2][c1 - 1] + m[r1 - 1][c1 - 1]

        int upRec = r1 - 1 >= 0 ? m[r1 - 1][c2] : 0;
        int leftRec = c1 - 1 >= 0 ? m[r2][c1 - 1] : 0;
        int overlapRec = (r1 - 1 >= 0 && c1 - 1 >= 0) ? m[r1 - 1][c1 - 1] : 0;

        return m[r2][c2] - upRec - leftRec + overlapRec;
    }

    public static void main(String[] args) {
        int[][] matrix = new int[][]{
                {3, 0, 1, 4, 2},
                {5, 6, 3, 2, 1},
                {1, 2, 0, 1, 5},
                {4, 1, 0, 1, 7},
                {1, 0, 3, 0, 5}
        };

        RangeSumQuery2DImmutable_304 range = new RangeSumQuery2DImmutable_304(matrix);
        range.sumRegion(2, 1, 4, 3);

    }
}
