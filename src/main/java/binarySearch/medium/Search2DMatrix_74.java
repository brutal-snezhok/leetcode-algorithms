package binarySearch.medium;

// https://leetcode.com/problems/search-a-2d-matrix/description/
public class Search2DMatrix_74 {
    public boolean searchMatrix1(int[][] matrix, int target) {
        // time O(log(n*m))
        // space O(1)

        // find row where target is
        // mid = (top + bot) / 2 -> if(matrix[mid][0] < target && matrix[mid][n - 1] > target) -> break; mid will be our row
        // else if(matrix[mid][0] > target) r = mid - 1; else if(matrix[mid][n - 1] < target) l = mid + 1;
        // example: mid = 1 -> r = mid - 1 = 0

        int rows = matrix.length;
        int cols = matrix[0].length;
        int top = 0;
        int bot = rows - 1;
        int mid = 0;
        while(top <= bot) {
            mid = (top + bot) / 2;

            if(matrix[mid][0] <= target && matrix[mid][cols - 1] >= target)
                break; // have just found need row
            else if(matrix[mid][0] > target)
                bot = mid - 1;
            else if(matrix[mid][cols - 1] < target)
                top = mid + 1;
        }

        int l = 0;
        int r = cols - 1;
        int row = mid;
        while(l <= r) {
            int avg = (l + r) / 2;

            if(matrix[row][avg] == target)
                return true;
            else if(matrix[row][avg] < target)
                l = avg + 1;
            else
                r = avg - 1;
        }

        return false;
    }

    // solution2
    public boolean searchMatrix2(int[][] matrix, int target) {
        // time O(log(m*n))
        // space O(1)

        // convert matrix to arr. matrix[x][y] => a[x * m + y]
        // and array to matrix. a[x] => matrix[x / m][x % m];

        int n = matrix.length;
        int m = matrix[0].length;

        int l = 0;
        int r = n * m - 1;

        while(l < r) {
            int mid = l + (r - l) / 2;

            if(matrix[mid / m][mid % m] >= target)
                r = mid;
            else
                l = mid + 1;
        }

        return matrix[l / m][l % m] == target;
    }
}
