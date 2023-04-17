package mathGeometry.easy;

// https://leetcode.com/problems/rectangle-overlap/description/
public class RectangleOverlap_836 {
    // solution1: check position
    public boolean isRectangleOverlap1(int[] rec1, int[] rec2) {
        // time O(1)
        // space O(1)

        // if the most right x of one rec less then the most left x of other rec then false
        // and check this condition for 4 borders

        // check if rect is not a line
        if(rec1[0] == rec1[3] || rec1[1] == rec1[3] || rec2[0] == rec2[3] || rec2[1] == rec2[3])
            return false;

        return !(rec1[2] <= rec2[0] ||
                rec1[3] <= rec2[1] ||
                rec1[0] >= rec2[2] ||
                rec1[1] >= rec2[3]);
    }

    // solution2: check area
    public boolean isRectangleOverlap2(int[] rec1, int[] rec2) {
        // time O(1)
        // space O(1)

        // overlapping area should be > 0, it means
        // height * width > 0
        // width > 0 when min(x2, x_2) > max(x1, x_1)
        // height > 0 when min(y2, y_2) > max(y1, y_1)

        boolean width = Math.min(rec1[2], rec2[2]) > Math.max(rec1[0], rec2[0]);
        boolean height = Math.min(rec1[3], rec2[3]) > Math.max(rec1[1], rec2[1]);

        return width && height;
    }
}
