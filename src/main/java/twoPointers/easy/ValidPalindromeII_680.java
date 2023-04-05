package twoPointers.easy;

// https://leetcode.com/problems/valid-palindrome-ii/description/
public class ValidPalindromeII_680 {
    public boolean validPalindrome(String s) {
        // time O(n)
        // space O(1)

        // compare left and right chars
        // if they equal then l++; r--;
        // if no then run palindrome func for string where removed l and for string where removed right char

        int l = 0;
        int r = s.length() - 1;
        while(l < r) {
            if(s.charAt(l) != s.charAt(r))
                return palindrome(s, l, r - 1) || palindrome(s, l + 1, r);

            l++;
            r--;
        }

        return true;
    }

    private boolean palindrome(String s, int l, int r) {
        while(l < r) {
            if(s.charAt(l) != s.charAt(r))
                return false;
            l++;
            r--;
        }

        return true;
    }
}
