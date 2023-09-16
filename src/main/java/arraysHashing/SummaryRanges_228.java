package arraysHashing;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

// https://leetcode.com/problems/summary-ranges/
public class SummaryRanges_228 {
    public List<String> summaryRanges1(int[] nums) {
        // solution for sorted values
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

    public List<String> summaryRanges2(int[] nums) {
        // this solution can be used for unsorted values in nums also
        // time O(n)
        // space O(n)
        List<String> res = new ArrayList<>();
        Set<Integer> set = new HashSet<>();
        for(int num : nums)
            set.add(num);

        for(int num : nums) {
            if(!set.contains(num))
                continue;

            set.remove(num);

            int l = num;
            int r = num;
            // find right border
            while(l != Integer.MIN_VALUE && set.contains(l - 1)) {
                set.remove(l - 1);
                l--;
            }

            // find left border
            while(r != Integer.MAX_VALUE && set.contains(r + 1)) {
                set.remove(r + 1);
                r++;
            }

            if(l == r)
                res.add(String.valueOf(l));
            else
                res.add(l + "->" + r);
        }

        return res;
    }
}
