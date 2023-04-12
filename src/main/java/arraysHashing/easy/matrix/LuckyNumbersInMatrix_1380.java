package arraysHashing.easy.matrix;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

// https://leetcode.com/problems/lucky-numbers-in-a-matrix/
public class LuckyNumbersInMatrix_1380 {
    public static List<Integer> luckyNumbers (int[][] matrix) {
        // time O(n * m)
        // space O(n + m)

        final int ROWS = matrix.length;
        final int COLS = matrix[0].length;

        int[] min = new int[ROWS];
        Arrays.fill(min, Integer.MAX_VALUE);
        int[] max = new int[COLS];
        Arrays.fill(max, Integer.MIN_VALUE);

        for(int r = 0; r < ROWS; r++)
            for(int c = 0; c < COLS; c++) {
                min[r] = Math.min(min[r], matrix[r][c]);
                max[c] = Math.max(max[c], matrix[r][c]);
            }

        Set<Integer> set = Arrays.stream(min).boxed().collect(Collectors.toSet());

        List<Integer> res = new ArrayList<>();
        for(int num : max)
            if(set.contains(num))
                res.add(num);

        return res;
    }

    public static void main(String[] args) {
        System.out.println(luckyNumbers(new int[][]{
                {3,7,8},
                {9,11,13},
                {15,16,17}
        }));
    }
}
