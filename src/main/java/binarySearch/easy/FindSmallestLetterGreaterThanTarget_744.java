package binarySearch.easy;

// https://leetcode.com/problems/find-smallest-letter-greater-than-target/description/
public class FindSmallestLetterGreaterThanTarget_744 {
    public char nextGreatestLetter(char[] letters, char target) {
        // time O(logn)
        // space O(1)

        int l = 0;
        int r = letters.length - 1;

        while(l < r) {
            int mid = l + (r - l) / 2;

            if(letters[mid] > target)
                r = mid;
            else
                l = mid + 1;
        }

        return letters[l] > target ? letters[l] : letters[0];
    }
}
