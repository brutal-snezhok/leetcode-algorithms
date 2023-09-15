package binarySearch.easy;

// https://leetcode.com/problems/guess-number-higher-or-lower/description/
public class GuessNumberHigherOrLower_374 {
    // solution1
    public int guessNumber1(int n) {
        int l = 1;
        int r = n;

        while(l <= r) {
            int mid = l + (r - l) / 2;
            int guessN = guess(mid);
            if(guessN == 0)
                return mid;
            else if(guessN == -1)
                r = mid - 1;
            else
                l = mid + 1;
        }

        return -1;
    }

    // solution2
    public int guessNumber2(int n) {
        int l = 1;
        int r = n;

        while(l < r) {
            int mid = l + (r - l) / 2;

            if(guess(mid) == 0 || guess(mid) == -1)
                r = mid;
            else
                l = mid + 1;
        }

        return l; // l - minimal val satisfying the condition in if statement
    }

    // solution3
    public int guessNumber3(int n) {
        // time O(logn)
        // space O(1)

        long l = -1;
        long r = n + 1L;;

        while(r - l > 1) {
            long mid = l + (r - l) / 2;

            int guess = guess((int)mid);
            if(guess == -1 || guess == 0)
                r = mid;
            else
                l = mid;
        }

        return (int)r;
    }

    /**
     * Forward declaration of guess API.
     * @param  num   your guess
     * @return 	     -1 if num is higher than the picked number
     *			      1 if num is lower than the picked number
     *               otherwise return 0
     * int guess(int num);
     */
    private int guess(int num) {
        if(num == 5)
            return 0;
        if(num > 5)
            return -1;

        return 1;
    }
}
