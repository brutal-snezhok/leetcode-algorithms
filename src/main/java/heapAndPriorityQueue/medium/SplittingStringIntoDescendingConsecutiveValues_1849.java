package heapAndPriorityQueue.medium;

// https://leetcode.com/problems/splitting-a-string-into-descending-consecutive-values/description/
public class SplittingStringIntoDescendingConsecutiveValues_1849 {
    public boolean splitString(String s) {
        // time O(n^n)
        // space O(n)

        // to choose first number we van take 0-th digit, 0-th and 1-th, and so on till length - 1
        // and then check with dfs all variants

        for(int i = 0; i < s.length() - 1; i++) {
            double num = Double.parseDouble(s.substring(0, i + 1)); // use double to avoid overflow
            if(dfs(s, i + 1, num))
                return true;
        }

        return false;
    }

    private boolean dfs(String s, int ind, double prev) {
        if(ind == s.length())
            return true;

        for(int j = ind; j < s.length(); j++) {
            double num = Double.parseDouble(s.substring(ind, j + 1));
            if(prev - num == 1 && dfs(s, j + 1, num))
                return true;
        }

        return false;
    }

    public static void main(String[] args) {
        System.out.println(Double.parseDouble("00078")); // 78.0
        System.out.println(Long.parseLong("00078")); // 78
        System.out.println(Integer.parseInt("00078")); // 78
    }
}
