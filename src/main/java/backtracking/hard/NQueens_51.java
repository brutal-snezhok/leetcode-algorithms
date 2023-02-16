package backtracking.hard;

import java.util.*;

// https://leetcode.com/problems/n-queens/
public class NQueens_51 {
    // visualizer https://haseebq.com/n-queens-visualizer/
    public List<List<String>> solveNQueens(int n) {
        // time O(n!*n)
        // space O(n) ???, didn't count res list

        List<List<String>> res = new ArrayList<>();
        Set<Integer> cols = new HashSet<>();
        Set<Integer> posDiag = new HashSet<>(); // (r + c)
        Set<Integer> negDiag = new HashSet<>(); // (r - c)

        backtracking(res, new ArrayList<>(), n, 0, cols, posDiag, negDiag);

        return res;
    }

    private void backtracking(List<List<String>> res, List<String> curr, int n, int r, Set<Integer> cols,
                              Set<Integer> posDiag, Set<Integer> negDiag) {
        if (curr.size() == n) {
            res.add(new ArrayList<>(curr));
            return;
        }

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
            backtracking(res, curr, n, r + 1, cols, posDiag, negDiag);

            cols.remove(c);
            posDiag.remove(r + c);
            negDiag.remove(r - c);
            curr.remove(curr.size() - 1);
        }
    }
}
