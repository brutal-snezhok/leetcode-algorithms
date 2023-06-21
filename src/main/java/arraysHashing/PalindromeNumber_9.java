package arraysHashing;

public class PalindromeNumber_9 {
    public boolean isPalindrome(int x) {
        // time O(logx)
        // space O(1)

        // compare half of number with other half

        if(x < 0 || (x % 10 == 0 && x != 0))
            return false;

        int firstHalf = x;
        int secondHalf = 0;

        while(firstHalf > secondHalf) {
            secondHalf = 10 * secondHalf + firstHalf % 10;
            firstHalf /= 10;
        }

        return firstHalf == secondHalf || firstHalf == secondHalf / 10;
    }
}
