package arraysHashing;

// https://leetcode.com/problems/reverse-string-ii/description/
public class ReverseStringII_541 {
    // solution1
    public String reverseStr1(String s, int k) {
        // time O(n)
        // space O(n)

        // n < k -> reverse all n chars
        // k <= n < 2*k -> reverse first k chars

        StringBuilder res = new StringBuilder();
        for(int i = 0; i < s.length(); i += 2 * k) {
            String curr = s.substring(i, Math.min(i + 2 * k, s.length()));

            if(curr.length() < k) {
                for(int j = curr.length() - 1; j >= 0; j--)
                    res.append(curr.charAt(j));
            } else {
                // reverse first k chars
                for(int j = k - 1; j >= 0; j--)
                    res.append(curr.charAt(j));

                // simply add the rest of chars
                for(int l = k; l < curr.length(); l++)
                    res.append(curr.charAt(l));
            }
        }

        return res.toString();
    }

    // solution2
    public String reverseStr2(String s, int k) {
        // time O(n)
        // space O(n)

        // n < k -> reverse all n chars
        // k <= n < 2*k -> reverse first k chars

        char[] chars = s.toCharArray();
        for(int start = 0; start < s.length(); start += 2 * k) {
            int i = start;
            int j = Math.min(i + k - 1, s.length() - 1);

            while(i < j) {
                char temp = chars[i];
                chars[i] = chars[j];
                chars[j] = temp;
                i++;
                j--;
            }
        }

        return new String(chars);
    }
}
