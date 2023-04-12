package mathGeometry.easy;

// https://leetcode.com/problems/excel-sheet-column-number/description/
public class ExcelSheetColumnNumber_171 {
    public int titleToNumber(String columnTitle) {
        // time O(n), n - count of chars in string
        // space O(1)

        // numOfChar * 26^n-i-1 + numOfChar * 26^n-i-1 + ...
        // "BCM" the final solution would be (2 x 26 x 26) + (3 x 26) + (13) = 2*26^2 + 3*26^1 + 13*26^0
        // "B" = 2
        // "BC" = (2)26 + 3
        // "BCM" = (2(26) + 3)26 + 13

        int res = 0;
        int n = columnTitle.length();
        for(int i = 0; i < n; i++)
            res += (columnTitle.charAt(i) - 'A' + 1) * Math.pow(26, n - i - 1);

        return res;
    }
}
