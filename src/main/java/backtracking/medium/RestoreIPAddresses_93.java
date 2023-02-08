package backtracking.medium;

import java.util.ArrayList;
import java.util.List;

// https://leetcode.com/problems/restore-ip-addresses/description/
public class RestoreIPAddresses_93 {
    public List<String> restoreIpAddresses(String s) {
        // we have 3 choses every time and 4 is max height because if dotNumber > 4 , then return.
        // time O(3^4) = O(1)
        // space O(3*4) = O(1)
        // time O(m^n*n), n - number of integers, m - number of dots
        // space O(m*n)

        List<String> res = new ArrayList<>();
        if (s.length() < 4 || s.length() > 12)
            return res;

        backtracking(res, s, "", 0, 0);

        return res;
    }

    private void backtracking(List<String> res, String s, String curr, int dotNum, int start) {
        if (dotNum > 4) return;

        if (dotNum == 4 && start == s.length()) {
            res.add(curr.substring(0, curr.length() - 1));
            return;
        }

        for (int i = start; i < Math.min(start + 3, s.length()); i++) {
            int num = Integer.parseInt(s.substring(start, i + 1));
            if (num <= 255 && (i == start || s.charAt(start) != '0'))
                backtracking(res, s, curr + s.substring(start, i + 1) + ".", dotNum + 1, i + 1);
        }
    }

    // second variant
    public void backtracking1(List<String> res, String s, String curr, int dotNumber, int startInd) {
        if (dotNumber == 0 && startInd == s.length()) {
            res.add(curr.substring(0, curr.length() - 1));
            return;
        }
        if (dotNumber < 0) return;

        for (int i = startInd; i < Math.min(startInd + 3, s.length()); i++) {
            // 2.5.5.25511135
            int num = Integer.parseInt(s.substring(startInd, i + 1));
            if (num >= 0 && num <= 255 && (startInd == i || s.charAt(startInd) != '0')) {
                backtracking1(res, s, curr + s.substring(startInd, i + 1) + ".", dotNumber - 1, i + 1);
            }
        }
    }

}
