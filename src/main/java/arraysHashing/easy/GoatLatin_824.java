package arraysHashing.easy;

import java.util.HashSet;
import java.util.Set;

// https://leetcode.com/problems/goat-latin/description/
public class GoatLatin_824 {
    public String toGoatLatin(String sentence) {
        // time O(n)
        // space O(n), since we use arr words

        // if first letter ('a', 'e', 'i', 'o', or 'u') then append "ma"
        // if first letter os consonant -> remove first letter and append it to the end and then "ma"
        // append to the and number of 'a' equal to index in sentence

        Set<Character> vowels = new HashSet<>();
        vowels.add('a');
        vowels.add('e');
        vowels.add('i');
        vowels.add('o');
        vowels.add('u');

        String[] words = sentence.split(" ");
        StringBuilder res = new StringBuilder();
        for(int i = 0; i < words.length; i++) {
            String word = words[i];
            char firstLetter = word.charAt(0);
            if(vowels.contains(Character.toLowerCase(firstLetter))) {
                res.append(word);
            } else {
                res.append(word.substring(1, word.length()));
                res.append(firstLetter);
            }

            res.append("ma");
            for(int j = 0; j < i + 1; j++)
                res.append("a");

            res.append(" ");
        }

        if(res.length() != 0)
            res.setLength(res.length() - 1);

        return res.toString();
    }
}
