package greedy.medium;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

// https://leetcode.com/problems/partition-labels/
public class PartitionLabels_763 {
    public List<Integer> partitionLabels(String s) {
        // time O(n)
        // space O(1), since only 26 chars will be saved in map

        Map<Character, Integer> lastInds = new HashMap<>();  // {char, lastInd}
        for (int i = 0; i < s.length(); i++)
            lastInds.put(s.charAt(i), i);

        int startInd = 0;
        int currLastInd = 0;
        List<Integer> res = new ArrayList<>();
        for (int i = 0; i < s.length(); i++) { //i = 0, 1, 2, 3
            char currChar = s.charAt(i); // a, b, b, a
            int lastInd = lastInds.get(currChar); // 3, 2, 2, 3
            currLastInd = Math.max(lastInd, currLastInd); // 3

            if (i == currLastInd) {
                res.add(currLastInd - startInd + 1); // 4
                startInd = i + 1; // 4
                currLastInd = i + 1; // 4
            }
        }

        return res;
    }
}
