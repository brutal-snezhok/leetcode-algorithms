package arraysHashing.easy;

import java.util.ArrayList;
import java.util.List;

// https://leetcode.com/problems/pascals-triangle/description/
public class PascalsTriangle_118 {
    public List<List<Integer>> generate(int numRows) {
        // time O(n^2)
        // space O(n), if don't count res list

        List<List<Integer>> res = new ArrayList<>();
        List<Integer> list = new ArrayList<>();
        list.add(1);
        res.add(list);

        for(int i = 0; i < numRows - 1; i++) {
            List<Integer> temp = new ArrayList<>();
            temp.add(0);
            temp.addAll(res.get(i));
            temp.add(0);

            List<Integer> newList = new ArrayList<>();
            for(int j = 1; j < temp.size(); j++)
                newList.add(temp.get(j - 1) + temp.get(j));

            res.add(newList);
        }

        return res;
    }
}
