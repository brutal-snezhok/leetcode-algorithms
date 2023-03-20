package mathGeometry.easy;

import java.util.HashSet;
import java.util.Set;

// https://leetcode.com/problems/happy-number/
public class HappyNumber_202 {
    // solution1
    public static boolean isHappy(int n) {
        // time O(logn)
        // space O(logn)

        // separate n into digit
        // find sum of digit's in pow of 2
        // put into set number
        // if newNum is num that already are in set

        int newNum = 0;
        Set<Integer> seen = new HashSet<>();
        seen.add(n);

        while (true) {
            while (n != 0) {
                int digit = n % 10;
                n /= 10;
                newNum += digit * digit;
            }

            if (newNum == 1)
                return true;

            if (seen.contains(newNum))
                return false;

            seen.add(newNum);
            n = newNum;
            newNum = 0;
        }
    }

    // solution2
    public boolean isHappy2(int n) {
        // floyds algo
        // time O(logn)
        // space O(1)

        int s = n;
        int f = n;

        while(s != 1 && getNewNum(f) != 1) {
            s = getNewNum(s);
            f = getNewNum(getNewNum(f));

            if(s == f)
                return false;
        }

        return true;
    }

    private int getNewNum(int n) {
        int newNum = 0;

        while(n != 0) {
            int digit = n % 10;
            n /= 10;
            newNum += digit * digit;
        }

        return newNum;
    }

    public static void main(String[] args) {
        System.out.println(isHappy(19));
    }
}
