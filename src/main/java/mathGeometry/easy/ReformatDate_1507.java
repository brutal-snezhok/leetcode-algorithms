package mathGeometry.easy;

import java.util.HashMap;
import java.util.Map;

// https://leetcode.com/problems/reformat-date/
public class ReformatDate_1507 {
    public String reformatDate(String date) {
        // time O(1)
        // space O(1)

        Map<String, String> months = new HashMap<>();
        months.put("Jan","01");
        months.put("Feb","02");
        months.put("Mar","03");
        months.put("Apr","04");
        months.put("May","05");
        months.put("Jun","06");
        months.put("Jul","07");
        months.put("Aug","08");
        months.put("Sep","09");
        months.put("Oct","10");
        months.put("Nov","11");
        months.put("Dec","12");
        String[] parsed = date.split(" ");

        StringBuilder day = new StringBuilder(parsed[0]);
        day.setLength(day.length() - 2);
        if(day.length() == 1)
            day.insert(0, '0');

        StringBuilder res = new StringBuilder();
        res.append(parsed[2]).append('-').append(months.get(parsed[1])).append('-').append(day);

        return res.toString();
    }
}
