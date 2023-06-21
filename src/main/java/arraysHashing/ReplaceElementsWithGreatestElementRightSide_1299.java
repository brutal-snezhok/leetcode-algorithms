package arraysHashing;

// https://leetcode.com/problems/replace-elements-with-greatest-element-on-right-side/description/
public class ReplaceElementsWithGreatestElementRightSide_1299 {
    public int[] replaceElements(int[] arr) {
        // time O(n)
        // space O(1)

        int[] res = new int[arr.length];
        res[arr.length - 1] = -1;
        int currMax = Integer.MIN_VALUE;
        for(int i = arr.length - 1; i >= 1; i--) {
            currMax = Math.max(currMax, arr[i]);
            res[i - 1] = currMax;
        }

        return res;
    }
}
