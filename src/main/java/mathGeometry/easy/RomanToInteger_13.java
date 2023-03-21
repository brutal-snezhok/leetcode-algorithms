package mathGeometry.easy;

import java.util.HashMap;
import java.util.Map;

// https://leetcode.com/problems/roman-to-integer/description/
public class RomanToInteger_13 {
    public int romanToInt(String s) {
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
}
