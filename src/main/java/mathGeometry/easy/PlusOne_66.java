package mathGeometry.easy;

// https://leetcode.com/problems/plus-one/
public class PlusOne_66 {
    public int[] plusOne(int[] digits) {
        // time O(n)
        // space O(1)

        int n = digits.length;
        if (digits[n - 1] != 9) {
            digits[n - 1] += 1;
            return digits;
        }

        int reminder = 1;
        int i = n - 1;
        while (i >= 0) {
            int newVal = digits[i] + reminder;
            if (newVal != 10) {
                digits[i] = newVal;
                return digits;
            }

            digits[i] = 0;
            reminder = 1;
            i--;
        }

        if (reminder == 1) {
            int[] res = new int[n + 1];
            res[0] = 1;
            return res;
        }

        return digits;
    }
}
