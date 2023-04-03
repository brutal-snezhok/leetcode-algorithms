package arraysHashing.easy;

// https://leetcode.com/problems/number-of-segments-in-a-string/
public class NumberOfSegmentsInString_434 {
    public int countSegments1(String s) {
        // time O(n)
        // space O(1)

        if(s.length() == 0)
            return 0;

        int i = 0;
        int res = 0;
        while(i < s.length()) {
            // skip whitespaces
            while(i < s.length() && s.charAt(i) == ' ')
                i++;

            if(i < s.length() && s.charAt(i) != ' ') {
                res++;
                // skip segment
                while(i < s.length() && s.charAt(i) != ' ')
                    i++;
            }
        }

        return res;
    }

    public int countSegments2(String s) {
        // time O(n)
        // space O(n)

        String trimmed = s.trim(); // otherwise split operation will return empty string in res [""], if their several whitespaces in the beginning
        if(trimmed.length() == 0)
            return 0;

        return trimmed.split("\\s+").length;
    }

    public static void main(String[] args) {
      //  System.out.println(countSegments("    foo    bar"));
    }
}
