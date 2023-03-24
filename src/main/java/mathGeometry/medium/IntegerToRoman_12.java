package mathGeometry.medium;

import java.util.Arrays;
import java.util.List;

// https://leetcode.com/problems/integer-to-roman/description/
public class IntegerToRoman_12 {
    List<Pair> list = Arrays.asList(new Pair("M", 1000), new Pair("CM", 900), new Pair("D", 500), new Pair("CD", 400),
            new Pair("C", 100), new Pair("XC", 90), new Pair("L", 50), new Pair("XL", 40),
            new Pair("X", 10), new Pair("IX", 9), new Pair("V", 5), new Pair("IV", 4), new Pair("I", 1));

    public String intToRoman(int num) {
        // time O(1) since we create already sorted list
        // space O(1)

        StringBuilder res = new StringBuilder();
        while (num != 0) {
            for (int i = 0; i < list.size(); i++) {
                Pair p = list.get(i);
                int curr = num / p.val;
                if (curr == 0)
                    continue;

                num = num - curr * p.val;
                while (curr != 0) {
                    res.append(p.roman);
                    curr--;
                }
            }
        }

        return res.toString();
    }

    class Pair {
        String roman;
        int val;

        public Pair(String roman, int val) {
            this.roman = roman;
            this.val = val;
        }
    }
}
