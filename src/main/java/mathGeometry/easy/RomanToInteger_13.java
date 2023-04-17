package mathGeometry.easy;

import java.util.HashMap;
import java.util.Map;

// https://leetcode.com/problems/roman-to-integer/description/
public class RomanToInteger_13 {
    // solution1
    public int romanToInt1(String s) {
        // time O(n)
        // space O(1)

        Map<Character, Integer> map = new HashMap<>();
        map.put('I', 1);
        map.put('V', 5);
        map.put('X', 10);
        map.put('L', 50);
        map.put('C', 100);
        map.put('D', 500);
        map.put('M', 1000);

        int res = 0;
        for(int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);

            if(i + 1 < s.length() && map.get(s.charAt(i + 1)) > map.get(ch))
                res -= map.get(ch);
            else
                res += map.get(ch);
        }

        return res;
    }

    // solution2
    Map<Character, Integer> map = Map.of('I', 1, 'V', 5, 'X', 10, 'L', 50, 'C', 100, 'D', 500, 'M', 1000);

    public int romanToInt2(String s) {
        // time O(n)
        // space O(1)

        // if prevVal < currVal -> res -= 2 * prevVal
        int res = 0;
        for(int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);
            int val = map.get(ch);

            res += val;

            if(i != 0) {
                char prev = s.charAt(i - 1);
                int prevVal = map.get(prev);
                if(val > prevVal)
                    res -= 2 * prevVal;
            }
        }

        return res;
    }
}
