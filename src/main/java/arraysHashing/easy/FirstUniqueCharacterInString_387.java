package arraysHashing.easy;

import java.util.HashMap;
import java.util.Map;

// https://leetcode.com/problems/first-unique-character-in-a-string/description/
public class FirstUniqueCharacterInString_387 {
    public int firstUniqChar(String s) {
        // time O(n)
        // space O(1)

        Map<Character, Pair> map = new HashMap<>(); // char -> {index: , isUniq: }
        for(int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);
            if(map.containsKey(ch))
                map.get(ch).isUniq = false;
            else
                map.put(ch, new Pair(i, true));
        }

        int res = Integer.MAX_VALUE;
        for(Map.Entry<Character, Pair> entry : map.entrySet()) {
            if(entry.getValue().isUniq)
                res = Math.min(res, entry.getValue().index);
        }

        return res == Integer.MAX_VALUE ? -1 : res;
    }

    class Pair {
        int index;
        boolean isUniq;

        public Pair(int index, boolean isUniq) {
            this.index = index;
            this.isUniq = isUniq;
        }
    }
}
