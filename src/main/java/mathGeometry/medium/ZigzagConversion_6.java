package mathGeometry.medium;

// https://leetcode.com/problems/zigzag-conversion/description/
public class ZigzagConversion_6 {
    public String convert(String s, int numRows) {
        // time O(n)
        // space O(1)

        if(numRows == 1)
            return s;

        StringBuilder res = new StringBuilder();
        for(int r = 0; r < numRows; r++) {
            int incr = 2 * (numRows - 1);
            for(int i = r; i < s.length(); i += incr) {
                res.append(s.charAt(i));

                int midInd = i + incr - 2 * r;
                if(r > 0 && r < numRows - 1 && midInd < s.length())
                    res.append(s.charAt(midInd));
            }
        }

        return res.toString();
    }
}
