package arraysHashing.easy;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

// https://leetcode.com/problems/keyboard-row/description/
public class KeyboardRow_500 {
    public String[] findWords(String[] words) {
        // time O(n), n - num of characters in all words
        // space O(1), since set will have only lower case letters

        // have 3 sets
        // need to have value from whick set take prev char
        // do check for lowercase letters

        Set<Character> set1 = stringToSet("qwertyuiop");
        Set<Character> set2 = stringToSet("asdfghjkl");
        Set<Character> set3 = stringToSet("zxcvbnm"); // not used

        List<String> list = new ArrayList<>();
        String prev = "";
        boolean isNeedToAdd = true;
        for (String word : words) {
            for (char ch : word.toCharArray()) {
                ch = Character.toLowerCase(ch);
                if (set1.contains(ch)) {
                    if (!prev.equals("") && !prev.equals("FIRST")) {
                        isNeedToAdd = false;
                        break;
                    }
                    prev = "FIRST";
                } else if (set2.contains(ch)) {
                    if (!prev.equals("") && !prev.equals("SECOND")) {
                        isNeedToAdd = false;
                        break;
                    }
                    prev = "SECOND";
                } else {
                    if (!prev.equals("") && !prev.equals("THIRD")) {
                        isNeedToAdd = false;
                        break;
                    }
                    prev = "THIRD";
                }
            }

            if (isNeedToAdd)
                list.add(word);

            isNeedToAdd = true;
            prev = "";
        }

        String[] res = new String[list.size()];
        for (int i = 0; i < list.size(); i++)
            res[i] = list.get(i);

        return res;
    }

    private Set<Character> stringToSet(String s) {
        Set<Character> set = new HashSet<>();
        for (char ch : s.toCharArray())
            set.add(ch);

        return set;
    }
}
