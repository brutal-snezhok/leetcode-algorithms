package mathGeometry.easy;

// https://leetcode.com/problems/ugly-number/description/
public class UglyNumber_263 {
    // solution1
    public boolean isUgly(int n) {
        // time O(logn)
        // space O(1)

        if(n <= 0)
            return false;

        int[] uglyNums = new int[]{2, 3, 5};
        for(int ugly : uglyNums) {
            while(n % ugly == 0)
                n /= ugly;
        }

        return n == 1;
    }

    // solution2
    public boolean isUgly2(int n) {
        // time O(logn)
        // space O(1)

        if(n == 0)
            return false;
        if(n == 1)
            return true;

        boolean isUgly = false;
        while(n != 1) {
            if(n % 2 == 0) {
                n /= 2;
                isUgly = true;
            } else if(n % 3 == 0) {
                n /= 3;
                isUgly = true;
            } else if(n % 5 == 0){
                n /= 5;
                isUgly = true;
            }

            if(!isUgly)
                return false;

            isUgly = false;
        }

        return true;
    }
}
