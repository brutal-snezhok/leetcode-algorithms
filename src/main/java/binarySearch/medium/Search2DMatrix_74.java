package binarySearch.medium;

// https://leetcode.com/problems/search-a-2d-matrix/description/
public class Search2DMatrix_74 {
    public boolean searchMatrix(int[][] matrix, int target) {
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
}
