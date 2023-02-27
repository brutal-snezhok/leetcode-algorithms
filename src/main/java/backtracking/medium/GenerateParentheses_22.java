package backtracking.medium;

import java.util.ArrayList;
import java.util.List;

// https://leetcode.com/problems/generate-parentheses/description/
public class GenerateParentheses_22 {
    public static List<String> generateParenthesis(int n) {
        // time O(2^2n) = O(4^n)
        // space O(2^2n) = O(4^n)

        List<String> res = new ArrayList<>();
        backtracking(res, new StringBuilder(), 0, 0, n);

        return res;
    }

    private static void backtracking(List<String> res, StringBuilder curr, int opened, int closed, int n) {
        if(curr.length() == 2 * n) {
            res.add(curr.toString());
            return;
        }

        if(opened < n) {
            curr.append("(");
            backtracking(res, curr, opened + 1, closed, n);
            curr.deleteCharAt(curr.length() - 1);
        }

        if(closed < opened) {
            curr.append(")");
            backtracking(res, curr, opened, closed + 1, n);
            curr.deleteCharAt(curr.length() - 1);
        }
    }

    public static void main(String[] args) {
        // n = 1 [()]
        // n = 2 [(()), ()()]
        // n = 3 [((())), (()()), (())(), ()(()), ()()()]
        // System.out.println(generateParenthesis(1));
        // System.out.println(generateParenthesis(2));
         System.out.println(generateParenthesis(3));
    }
}
