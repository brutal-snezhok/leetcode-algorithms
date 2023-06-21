package arraysHashing;

// https://leetcode.com/problems/license-key-formatting/description/
public class LicenseKeyFormatting_482 {

    public String licenseKeyFormatting1(String s, int k) {
        // right to left traversal
        // time O(n)
        // space O(1)

        // go from left to right
        // if meet != '-' then append to res
        // after each k chars insert dash
        // reverse string

        StringBuilder res = new StringBuilder();
        int count = 0;
        for (int i = s.length() - 1; i >= 0; i--) {
            char ch = s.charAt(i);
            if (ch == '-')
                continue;

            res.append(Character.toUpperCase(ch));
            count++;
            if (count == k) {
                res.append('-');
                count = 0;
            }
        }

        if (res.length() > 0 && res.charAt(res.length() - 1) == '-')
            res.setLength(res.length() - 1);

        return res.reverse().toString();
    }

    public static String licenseKeyFormatting2(String s, int k) {
        // time O(n)
        // space O(n)

        // split string by dashes
        // join all characters into one String without dashes and in Upper case
        // check if k > len -> then all characters will be in first group
        // if no, then in first group will be len % k and in all other groups ken / k

        String[] strs = s.split("-");
        StringBuilder joined = new StringBuilder();
        for (String str : strs)
            for (char ch : str.toCharArray())
                joined.append(Character.toUpperCase(ch));

        if (k > joined.length())
            return joined.toString();

        int n = joined.length();
        StringBuilder res = new StringBuilder();
        int startPoint = n % k == 0 ? 0 : n % k;
        if (startPoint != 0)
            res.append(joined.substring(0, n % k)).append('-');

        for (int i = startPoint; i < n; i += k)
            res.append(joined.substring(i, Math.min(i + k, n))).append('-');

        if (res.length() > 1)
            res.deleteCharAt(res.length() - 1);

        return res.toString();
    }

    public static void main(String[] args) {

    }
}
