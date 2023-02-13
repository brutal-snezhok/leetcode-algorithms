package backtracking.medium;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

// https://leetcode.com/problems/letter-combinations-of-a-phone-number/description/
public class LetterCombinationsOfPhoneNumber_17 {
    Map<Character, String> map = Map.of(
            '2',
            "abc",
            '3',
            "def",
            '4',
            "ghi",
            '5',
            "jkl",
            '6',
            "mno",
            '7',
            "pqrs",
            '8',
            "tuv",
            '9',
            "wxyz"
    );

    public List<String> letterCombinations(String digits) {
        // time O(4^l*l), l - length of string digits
        // space O(l), didn't count res list

        List<String> res = new ArrayList<>();
        if(digits.length() == 0)
            return res;

        backtracking(digits, res, "", 0);

        return res;
    }

    private void backtracking(String digits, List<String> res, String curr, int start) {
        if(start == digits.length()) {
            res.add(curr);
            return;
        }
        if(start > digits.length())
            return;

        String digitS = map.get(digits.charAt(start));
        for(char ch : digitS.toCharArray())
            backtracking(digits, res, curr + ch, start + 1);
    }




    // --------------------------------------------------------------------
    // optimized solution with StringBuilder
    public List<String> letterCombinations2(String digits) {
        // time O(4^l*l), l - length of string digits
        // space O(l), didn't count res list

        List<String> res = new ArrayList<>();
        if(digits.length() == 0)
            return res;

        backtracking2(digits, res, new StringBuilder(), 0);

        return res;
    }

    private void backtracking2(String digits, List<String> res, StringBuilder curr, int start) {
        if(start == digits.length()) {
            res.add(curr.toString());
            return;
        }
        if(start > digits.length())
            return;

        String digitS = map.get(digits.charAt(start));
        for(char ch : digitS.toCharArray()) {
            curr.append(ch);
            backtracking2(digits, res, curr, start + 1);
            curr.setLength(curr.length() - 1); // remove last character
        }
    }
}
