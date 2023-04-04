package arraysHashing.easy;

// https://leetcode.com/problems/detect-capital/description/
public class DetectCapital_520 {
    public boolean detectCapitalUse(String word) {
        // time O(n)
        // space O(1)

        // upper in range 65 to 90
        // lower in range 97 to 122

        // check if all are capitals
        boolean isAllCapitals = true;
        boolean isAllLower = true;
        for (char ch : word.toCharArray()) {
            if (ch < 65 || ch > 90) {
                isAllCapitals = false;
            }
            if (ch < 97 || ch > 122)
                isAllLower = false;
        }

        if (isAllCapitals || isAllLower)
            return true;

        // check if first is capital and rest in lower case
        char first = word.charAt(0);
        isAllLower = true;
        if (first >= 65 && first <= 90) {
            for (int i = 1; i < word.length(); i++) {
                char ch = word.charAt(i);
                if (ch < 97 || ch > 122) {
                    isAllLower = false;
                    break;
                }
            }

            return isAllLower;
        }

        return false;
    }
}
