package twoPointers.easy;

// https://leetcode.com/problems/reverse-words-in-a-string-iii/description/
public class ReverseWordsInStringIII_557 {
    public String reverseWords1(String s) {
        // time O(n)
        // space O(1)

        // go until meet space or end of string
        // reversae string with two pointer

        char[] chars = s.toCharArray();
        int lengthOfWord = 0;
        for(int start = 0; start <= s.length(); start++) {
            if(start == s.length() || s.charAt(start) == ' ') {
                int r = start - 1;
                int l = r - lengthOfWord + 1;
                while(l < r) {
                    char temp = chars[l];
                    chars[l] = chars[r];
                    chars[r] = temp;
                    l++;
                    r--;
                }
                lengthOfWord = 0;
            } else
                lengthOfWord++;
        }

        return new String(chars);
    }

    public String reverseWords2(String s) {
        // time O(n)
        // space O(n)

        // split by space
        // reverse each word and add to res

        String[] strs = s.split(" ");
        StringBuilder res = new StringBuilder();
        for(String str : strs) {
            for(int i = str.length() - 1; i >= 0; i--) {
                res.append(str.charAt(i));
            }
            res.append(" ");
        }

        if(res.length() != 0) // remove last space
            res.setLength(res.length() - 1);

        return res.toString();
    }
}
