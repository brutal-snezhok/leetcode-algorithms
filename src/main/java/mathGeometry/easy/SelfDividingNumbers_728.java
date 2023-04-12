package mathGeometry.easy;

import java.util.ArrayList;
import java.util.List;

// https://leetcode.com/problems/self-dividing-numbers/description/
public class SelfDividingNumbers_728 {
    public List<Integer> selfDividingNumbers(int left, int right) {
        // time O(n), n - number of integers in the range [l, r]
        // space O(n)

        List<Integer> res = new ArrayList<>();
        for (int i = left; i <= right; i++)
            if (isSelfDividing(i))
                res.add(i);

        return res;
    }

    private boolean isSelfDividing(int num) {
        char[] chars = String.valueOf(num).toCharArray();
        for (char ch : chars)
            if (ch == '0' || num % (ch - '0') != 0)
                return false;

        return true;
    }
}
