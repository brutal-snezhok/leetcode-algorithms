package arraysHashing.easy;

// https://leetcode.com/problems/image-smoother/description/
public class ImageSmoother_661 {
    public int[][] imageSmoother(int[][] img) {
        // time O(m * n)
        // space O(m * n), if count res

        int m = img.length;
        int n = img[0].length;

        int[][] res = new int[m][n];

        for(int r = 0; r < m; r++)
            for(int c = 0; c < n; c++)
                res[r][c] = avgValOfNeighbours(img, r, c);

        return res;
    }

    private int avgValOfNeighbours(int[][] img, int r, int c) {
        int[][] directions = { {1, 0}, {-1, 0}, {0, 1}, {0, -1},
                {1, 1}, {-1, 1}, {1, -1}, {-1, -1} };

        int sum = img[r][c];
        int count = 1;
        for(int[] dir : directions) {
            int newR = r + dir[0];
            int newC = c + dir[1];

            if(newR < 0 || newR >= img.length || newC < 0 || newC >= img[0].length)
                continue;

            sum += img[newR][newC];
            count++;
        }

        return (int)Math.floor(sum / count);
    }
}
