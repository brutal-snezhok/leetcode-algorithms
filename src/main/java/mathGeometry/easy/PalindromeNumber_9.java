package mathGeometry.easy;

// https://leetcode.com/problems/palindrome-number/
public class PalindromeNumber_9 {
    // solution1
    public static boolean isPalindrome(int x) {
        // convert num to string
        // time O(n)
        // space O(n)

        if(x < 0)
            return false;

        String strNum = String.valueOf(x);
        int l = 0;
        int r = strNum.length() - 1;
        while(l < r) {
            if(strNum.charAt(l) != strNum.charAt(r))
                return false;

            l++;
            r--;
        }

        return true;
    }

    // solution2
    public boolean isPalindrome2(int x) {
        // revert half of the number
        // time O(logn) logn base 10
        // space O(1)

        if(x < 0 || (x % 10 == 0 && x != 0))
            return false;

        int revert = 0;
        while(x > revert) {
            revert = 10 * revert + x % 10;
            x /= 10;
        }

        return x == revert || x == revert / 10;
    }

    public static void main(String[] args) {
        System.out.println(isPalindrome(121));
    }
}
