package graphs.easy;

import java.util.HashMap;
import java.util.Map;

// https://leetcode.com/problems/verifying-an-alien-dictionary/description/
public class VerifyingAnAlienDictionary_953 {
    public static boolean isAlienSorted(String[] words, String order) {
        // time O(m), m - total number of characters in words[]
        // space O(1), hashMap has only 26 characters

        // main idea is to compare adjacent words
        // if all pairs sorted then true
        // if there is a pair of unsorted words then false

        // edge cases:
        // if words[i] is shorter then words[i - 1] return false ("app" should be before "apple")
        // found different letter, but words in correct order -> move to the next pair
        // found different letter, but words are not in correct order -> return false

        if(words.length == 1)
            return true;

        Map<Character, Integer> map = new HashMap<>();
        for(int i = 0; i < order.length(); i++)
            map.put(order.charAt(i), i); // {char, index}

        for(int i = 1; i < words.length; i++) {
            String word1 = words[i - 1];
            String word2 = words[i];

            int minL = Math.min(word1.length(), word2.length());
            for(int j = 0; j < minL; j++) {
                int ind1 = map.get(word1.charAt(j));
                int ind2 = map.get(word2.charAt(j));

                if(ind1 > ind2)
                    return false;
                else if(ind1 < ind2)
                    break;
                else if(j == minL - 1 && word1.length() > word2.length())
                    return false;
            }
        }

        return true;
    }

    public static void main(String[] args) {
        isAlienSorted(new String[]{"hello","leetcode"}, "hlabcdefgijkmnopqrstuvwxyz");
    }
}
