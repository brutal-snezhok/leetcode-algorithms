package arraysHashing;

import java.util.ArrayList;
import java.util.List;

// https://leetcode.com/problems/summary-ranges/
public class SummaryRanges_228 {
    public List<String> summaryRanges(int[] nums) {
        // time O(n)
        // space O(1), if don't count res list

        // if nums[i] == nums[i - 1] + 1 then continue;
        // if nums[i] != nums[i - 1] + 1 then
        // check if startRange == nums[i - 1] -> add "startRange"
        // if no add to res list string "startRange -> nums[i - 1]"

        List<String> res = new ArrayList<>();
        int n = nums.length;
        if(n == 0)
            return res;
        if(n == 1) {
            res.add(String.valueOf(nums[0]));
            return res;
        }

        int startRange = nums[0];
        for(int i = 1; i < n; i++) {
            if(nums[i - 1] + 1 == nums[i])
                continue;

            String val = startRange == nums[i - 1] ? String.valueOf(startRange)
                                                   : startRange + "->" + nums[i - 1];
            res.add(val);
            startRange = nums[i];
        }

        // add last range
        String val = startRange == nums[n - 1] ? String.valueOf(startRange)
                                               : startRange + "->" + nums[n - 1];
        res.add(val);

        return res;
    }
}
