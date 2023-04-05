package arraysHashing.easy;

// https://leetcode.com/problems/plus-one/description/
public class PlusOne_66 {
    public int[] plusOne(int[] digits) {
        // time O(n)
        // space O(1)

        int n = digits.length;
        if(digits[n - 1] != 9) {
            digits[n - 1] += 1;
            return digits;
        }

        int carry = 1;
        for(int i = n - 1; i >= 0; i--) {
            int val = digits[i] + carry;
            if(val > 9) {
                digits[i] = 0;
                carry = 1;
            } else {
                digits[i] = val;
                return digits;
            }
        }

        if(carry == 1) {
            int[] res = new int[n + 1];
            res[0] = 1;
            return res;
        }

        return digits;
    }
}
