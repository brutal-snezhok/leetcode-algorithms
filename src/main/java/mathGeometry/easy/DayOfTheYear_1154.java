package mathGeometry.easy;

// https://leetcode.com/problems/day-of-the-year/description/
public class DayOfTheYear_1154 {
    public int dayOfYear(String date) {
        // time O(1)
        // space O(1)

        String[] strs = date.split("-");
        int year = Integer.parseInt(strs[0]);
        int month = Integer.parseInt(strs[1]);
        int day = Integer.parseInt(strs[2]);
        int[] days = {31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
        boolean isLeap = isLeapYear(year);
        int res = 0;

        for (int i = 0; i < month - 1; i++) {
            res += days[i];

            if (i == 1 && isLeap) {
                res += 1;
            }
        }

        res += day;

        return res;
    }

    private boolean isLeapYear(int year) {
        if (year % 400 == 0)
            return true;

        if (year % 100 == 0)
            return false;

        if (year % 4 == 0)
            return true;

        return false;
    }
}
