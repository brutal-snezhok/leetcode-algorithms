package mathGeometry.easy;

// https://leetcode.com/problems/excel-sheet-column-title/
public class ExcelSheetColumnTitle_168 {
    public String convertToTitle(int n) {
        // time O(logn)
        // space O(1)

        // for String ABZ and its corresponding number n:
        // n = (A+1) * 26^2 + (B+1) * 26^1 + (Z+1) * 26^0
        // n = (A+1) * 26^2 + (B+1) * 26^1 + Z+1
        // n - 1 = (A+1) * 26^2 + (B+1) * 26^1 + Z
        // Z = (n - 1) % 26
        // (n - 1) / 26 = (A+1) * 26^1 + (B+1) * 26^0

        StringBuilder res = new StringBuilder();
        while(n > 0) {
            n--;
            res.append((char)(n % 26 + 'A'));
            n /= 26;
        }

        return res.reverse().toString();
    }
}
