package mathGeometry.medium;

// https://leetcode.com/problems/multiply-strings/
public class MultiplyStrings_43 {
    public String multiply(String num1, String num2) {
        // time O(n * m)
        // space O(n + m)

        if (num1.charAt(0) == '0' || num2.charAt(0) == '0')
            return "0";

        int[] arr = new int[num1.length() + num2.length()];
        StringBuilder str1 = new StringBuilder(num1);
        StringBuilder str2 = new StringBuilder(num2);

        str1.reverse();
        str2.reverse();

        for (int i1 = 0; i1 < str1.length(); i1++) {
            for (int i2 = 0; i2 < str2.length(); i2++) {
                int digit = (str1.charAt(i1) - '0') * (str2.charAt(i2) - '0');
                arr[i1 + i2] += digit;
                arr[i1 + i2 + 1] += arr[i1 + i2] / 10;
                arr[i1 + i2] = arr[i1 + i2] % 10;
            }
        }

        reverse(arr);
        int start = 0;
        while (start < arr.length && arr[start] == 0)
            start++;

        StringBuilder res = new StringBuilder();
        for (int i = start; i < arr.length; i++)
            res.append(arr[i]);

        return res.toString();
    }

    private void reverse(int[] arr) {
        int l = 0;
        int r = arr.length - 1;
        while (l < r) {
            int temp = arr[l];
            arr[l] = arr[r];
            arr[r] = temp;
            l++;
            r--;
        }
    }

}
