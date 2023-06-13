package mathGeometry.medium;

// https://leetcode.com/problems/last-moment-before-all-ants-fall-out-of-a-plank/description/
public class LastMomentBeforeAllAntsFallOutOfPlank_1503 {
    public int getLastMoment(int n, int[] left, int[] right) {
        // time O(n)
        // space O(1)

        //  main observation here is that we can consider that ants do not change directon after meeting each other

        int res = 0;
        for(int r : right)
            res = Math.max(res, n - r);
        for(int l : left)
            res = Math.max(res, l);

        return res;
    }
}
