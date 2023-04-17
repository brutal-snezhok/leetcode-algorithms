package mathGeometry.easy;

// https://leetcode.com/problems/add-digits/
public class AddDigits_258 {
    // solution1
    public int addDigits1(int num) {
        // time O(logn)
        // space O(1)

        int sum = 0;
        while(num > 0) {
            sum += num % 10;
            num /= 10;

            if(num == 0 && sum > 9) {
                num = sum;
                sum = 0;
            }
        }

        return sum;
    }
    // solution2
    public int addDigits2(int num) {
        // time O(1)
        // space O(1)

        if(num == 0)
            return 0;

        if(num % 9 == 0)
            return 9;

        return num % 9;
    }
}
