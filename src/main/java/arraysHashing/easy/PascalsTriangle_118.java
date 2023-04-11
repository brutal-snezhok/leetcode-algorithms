package arraysHashing.easy;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

// https://leetcode.com/problems/pascals-triangle/description/
public class PascalsTriangle_118 {
    // solution1
    public List<List<Integer>> generate1(int numRows) {
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

    // solution2
    public List<List<Integer>> generate(int numRows) {
        // time O(n^2)
        // space O(n^2), if count res list

        List<List<Integer>> res = new ArrayList<>();
        res.add(List.of(1));
        if(numRows == 1)
            return res;

        res.add(Arrays.asList(1, 1));
        if(numRows == 2)
            return res;

        for(int i = 2; i < numRows; i++) {
            List<Integer> list = res.get(i - 1);
            List<Integer> temp = new ArrayList<>();
            temp.add(1);
            for(int j = 1; j < list.size(); j++)
                temp.add(list.get(j - 1) + list.get(j));
            temp.add(1);
            res.add(temp);
        }

        return res;
    }
}
