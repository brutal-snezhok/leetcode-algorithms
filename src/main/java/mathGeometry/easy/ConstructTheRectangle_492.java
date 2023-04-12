package mathGeometry.easy;

// https://leetcode.com/problems/construct-the-rectangle/description/
public class ConstructTheRectangle_492 {
    public int[] constructRectangle(int area) {
        // time O(sqrt(n))
        // space O(1)

        // W is in the range [1, sqrt(area)]
        int[] res = new int[2];
        int sqrt = (int)Math.sqrt(area);
        res[0] = area;
        res[1] = 1;

        for(int w = 2; w <= sqrt; w++) {
            if(area % w == 0 && area / w - w < res[0] - res[1]) {
                res[0] = area / w;
                res[1] = w;
            }
        }

        return res;
    }
}
