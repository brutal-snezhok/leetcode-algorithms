package mathGeometry.easy;

// https://leetcode.com/problems/day-of-the-week/
public class DayOfTheWeek_1185 {
    public String dayOfTheWeek(int day, int month, int year) {
        // time O(1)
        // space O(1)

        // count num of days started from 1 Jan 1971 year included till given date
        // then find ind = numOfDays % 7 - 1

        // start from Friday since 1 Jan 1971 is Friday
        String[] vals = new String[]{"Friday", "Saturday", "Sunday", "Monday", "Tuesday", "Wednesday", "Thursday"};
        int[] days = new int[]{31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
        int numOfDays = 0;
        boolean isLeap = isLeapYear(year);

        for(int i = 1971; i < year; i++)
            numOfDays += isLeapYear(i) ? 366 : 365;

        for(int i = 0; i < month - 1; i++) {
            numOfDays += days[i];

            if(i == 1 && isLeap)
                numOfDays += 1;
        }

        numOfDays += day - 1; // do minus 1 since arr is 0 indexed

        return vals[numOfDays % 7];
    }

    private boolean isLeapYear(int year) {
        if(year % 400 == 0)
            return true;

        if(year % 100 == 0)
            return false;

        if(year % 4 == 0)
            return true;

        return false;
    }

    public static void main(String[] args) {
        DayOfTheWeek_1185 week = new DayOfTheWeek_1185();
        week.dayOfTheWeek(31, 8, 2019);
    }
}
