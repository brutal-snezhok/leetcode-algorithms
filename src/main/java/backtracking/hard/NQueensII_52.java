package backtracking.hard;

import java.util.*;

// https://leetcode.com/problems/n-queens-ii/description/
public class NQueensII_52 {
    public int totalNQueens(int n) {
        // time O(n!*n)
        // space O(n^2)???

        Set<Integer> cols = new HashSet<>();
        Set<Integer> posDiag = new HashSet<>(); // (r + c)
        Set<Integer> negDiag = new HashSet<>(); // (r - c)

        return backtracking(new ArrayList<>(), cols, posDiag, negDiag, 0, n);
    }

    private int backtracking(List<String> curr, Set<Integer> cols, Set<Integer> posDiag, Set<Integer> negDiag,
                             int r, int n) {
        if (curr.size() == n)
            return 1;

        int count = 0;
        for (int c = 0; c < n; c++) {
            if (cols.contains(c) || posDiag.contains(r + c) || negDiag.contains(r - c))
                continue;

            cols.add(c);
            posDiag.add(r + c);
            negDiag.add(r - c);

            char[] chars = new char[n];
            Arrays.fill(chars, '.');
            chars[c] = 'Q';
            curr.add(new String(chars));

            count += backtracking(curr, cols, posDiag, negDiag, r + 1, n);

            cols.remove(c);
            posDiag.remove(r + c);
            negDiag.remove(r - c);
            curr.remove(curr.size() - 1);
        }

        return count;
    }
}
