package heapAndPriorityQueue.medium;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

// https://leetcode.com/problems/find-unique-binary-string/description/
public class FindUniqueBinaryString_1980 {
    public String findDifferentBinaryString(String[] nums) {
        // time O(2^n)
        // space O(n)

        // put all values from nums into set for quick check
        // generate binary value of length n and check whether it present in set, if not -> return this val
        // if yes -> continue generating values

        Set<String> set = new HashSet<>(Arrays.asList(nums));

        return dfs(set, new StringBuilder());
    }

    private String dfs(Set<String> set, StringBuilder curr) {
        if(curr.length() == set.size()) {
            if(set.contains(curr.toString()))
                return null;
            else
                return curr.toString();
        }

        for(int i = 0; i < 2; i++) {
            int length = curr.length();
            curr.append(i);
            String val = dfs(set, curr);
            if(val != null)
                return val;

            curr.setLength(length);
        }

        return null;
    }
}
