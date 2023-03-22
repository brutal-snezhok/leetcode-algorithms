package mathGeometry.medium;

// https://leetcode.com/problems/rotate-image/description/
public class RotateImage_48 {
    public void rotate(int[][] m) {
        // time O(n * m)
        // space O(1)

        int l = 0;
        int r = m.length - 1;

        while(l < r) {
            for(int i = 0; i < r - l; i++) {
                int top = l;
                int bot = r;

                int leftUp = m[top][l + i];
                m[top][l + i] = m[bot - i][l];
                m[bot - i][l] = m[bot][r - i];
                m[bot][r - i] = m[top + i][r];
                m[top + i][r] = leftUp;
            }
            l++;
            r--;
        }
    }
}
