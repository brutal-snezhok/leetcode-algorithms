package arraysHashing.easy;

public class LengthOfLastWord_58 {
    public int lengthOfLastWord(String s) {
        // time O(n)
        // space O(1)

        // go from end to the beginning and skip spaces
        // then count letters till 0 index or till meet space

        int ind = s.length() - 1;
        while(s.charAt(ind) == ' ' && ind >= 0) {
            ind--;
        }

        int res = 0;
        while(ind >= 0 && s.charAt(ind) != ' ') {
            res++;
            ind--;
        }

        return res;
    }
}
