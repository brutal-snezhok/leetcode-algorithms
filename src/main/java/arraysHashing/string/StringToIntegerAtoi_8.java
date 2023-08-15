package arraysHashing.string;

// https://leetcode.com/problems/string-to-integer-atoi/
public class StringToIntegerAtoi_8 {
    public int myAtoi(String s) {
        // time O(n)
        // space O(n)

        if(s.length() == 0)
            return 0;

        int i = 0;
        int sign = 1;
        while(i < s.length() && s.charAt(i) == ' ')
            i++;

        if(i < s.length() && (s.charAt(i) == '-' || s.charAt(i) == '+')) {
            sign = s.charAt(i) == '-' ? -1 : 1;
            i++;
        }

        if(i >= s.length() || !Character.isDigit(s.charAt(i)))
            return 0;

        double res = 0;
        while(i < s.length() && Character.isDigit(s.charAt(i))) {
            res = res * 10 + (s.charAt(i) - '0');
            i++;
        }

        res *= sign;
        if(res < Integer.MIN_VALUE)
            return Integer.MIN_VALUE;
        else if(res > Integer.MAX_VALUE)
            return Integer.MAX_VALUE;
        else
            return Math.toIntExact((int)res);
    }

    public static void main(String[] args) {
        StringToIntegerAtoi_8 atoi = new StringToIntegerAtoi_8();
        atoi.myAtoi("9223372036854775808"); // res should be double because witk long this case doesn't work
        //atoi.myAtoi("+-12");
       // atoi.myAtoi("-91283472332");
       // atoi.myAtoi("   -42");
       // atoi.myAtoi("words and 987");
    }
}
