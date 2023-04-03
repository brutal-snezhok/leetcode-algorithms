package twoPointers.easy;

// https://leetcode.com/problems/valid-palindrome/description/
public class ValidPalindrome_125 {
    public boolean isPalindrome(String s) {
        // time O(n)
        // space O(1)

        // two pointers
        // go from left to right while meet letter char, convert it to lowercase
        // go from right to left while meet letter char, convert to lowercase
        // compare them

        int l = 0;
        int r = s.length() - 1;
        while(l < r) {
            while(l < r && !Character.isLetterOrDigit(s.charAt(l)))
                l++;
            while(l < r && !Character.isLetterOrDigit(s.charAt(r)))
                r--;

            if(l < r) {
                char charL = Character.toLowerCase(s.charAt(l));
                char charR = Character.toLowerCase(s.charAt(r));

                if(charL != charR)
                    return false;
                l++;
                r--;
            }
        }

        return true;
    }
}
