package arraysHashing;

// https://leetcode.com/problems/repeated-substring-pattern/description/
public class RepeatedSubstringPattern_459 {
    public boolean repeatedSubstringPattern(String s) {
        // time O(n^2)
        // space O(n)

        int len = s.length();
        for (int i = len / 2; i >= 1; i--) {
            if (len % i != 0)
                continue;

            int numToRepeat = len / i;
            String str = s.substring(0, i);
            StringBuilder b = new StringBuilder();
            for (int j = 0; j < numToRepeat; j++)
                b.append(str);

            if (s.equals(b.toString())) return true;
        }

        return false;
    }
}
