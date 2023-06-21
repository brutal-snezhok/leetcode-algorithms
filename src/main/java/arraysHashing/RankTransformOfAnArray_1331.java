package arraysHashing;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

// https://leetcode.com/problems/rank-transform-of-an-array/description/
public class RankTransformOfAnArray_1331 {
    public int[] arrayRankTransform(int[] arr) {
        // time O(nlogn)
        // space O(n)

        if (arr.length == 0)
            return new int[0];

        int[] copy = Arrays.copyOf(arr, arr.length);
        Arrays.sort(copy);
        int[] res = new int[arr.length];
        int prev = copy[0];

        Map<Integer, Integer> map = new HashMap<>(); // {val, rank}
        map.put(copy[0], 1);
        for (int i = 1; i < copy.length; i++) {
            if (copy[i] != prev) {
                map.put(copy[i], map.get(prev) + 1);
                prev = copy[i];
            }
        }

        int i = 0;
        for (int num : arr)
            res[i++] = map.get(num);

        return res;
    }
}
