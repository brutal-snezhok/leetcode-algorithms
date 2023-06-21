package arraysHashing;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

// https://leetcode.com/problems/is-subsequence/description/
public class IsSubsequence_392 {
    public boolean isSubsequence(String s, String t) {
        // time O(n)
        // space O(1)

        if("".equals(s))
            return true;

        if(s.length() > t.length())
            return false;

        int pointerS = 0;
        for(int pointerT = 0; pointerT < t.length(); pointerT++) {
            if(t.charAt(pointerT) == s.charAt(pointerS))
                pointerS++;

            if(pointerS == s.length())
                return true;
        }

        return false;
    }


//    Follow up: Suppose there are lots of incoming s, say s1, s2, ..., sk where k >= 10^9, and you want to check one by one to see if t has its subsequence.
//    In this scenario, how would you change your code?

//    Our current solution is O(n), where n is the length of t. If there are lots of incoming s, out solution won't be satisfactory because n is very large.
//    Binary search. For each character from a - z, create a list of indices where the character occurs in t. Iterate through s.
//    For each character in s, go to its corresponding list, and binary search for the earliest index where the character occurs in t.
//    Use a variable "previous" to keep track of that index.
//    If in any iteration, binary search returns an index that's out of range of the list, return false, because that means a character that appears later in s has no match in t.

//    For k strings s will have time complexity O(n + k*m*logn), so if n much bigger than m then this solution can help
    public boolean isSubsequence2(String s, String t) {
        List<Integer>[] positions = new List[26];

        for (int i = 0; i < t.length(); i++) {
            char c = t.charAt(i);
            int index = c - 'a';
            if (positions[index] == null) {
                positions[index] = new ArrayList<>();
            }
            positions[index].add(i);
        }

        int last = 0;
        for (char c : s.toCharArray()) {
            int index = c - 'a';
            if (positions[index] == null) {
                return false;
            }
            int insertion = Collections.binarySearch(positions[index], last);
            if (insertion < 0) {
                insertion = - (insertion + 1);
            }
            if (insertion == positions[index].size()) {
                return false;
            }
            last = positions[index].get(insertion) + 1;
        }

        return true;
    }
}
