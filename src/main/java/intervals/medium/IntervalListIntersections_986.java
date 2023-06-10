package intervals.medium;

import java.util.ArrayList;
import java.util.List;

// https://leetcode.com/problems/interval-list-intersections/description/
public class IntervalListIntersections_986 {
    public int[][] intervalIntersection(int[][] firstList, int[][] secondList) {
        // time O(n), n - total num of els in both lists
        // space O(n)

        /**
         i
         [0,2],[5,10],[13,23],[24,25]

         j
         [1,5],[8,12],[15,24],[25,26]

         res: [1,2],[5,5],[8,10],[15,23],[24,24],[25,25]
         */

        List<int[]> intersect = new ArrayList<>();
        int i = 0;
        int j = 0;
        while(i < firstList.length && j < secondList.length) {
            int[] first = firstList[i];
            int[] second = secondList[j];

            int[] inter = intersect(first[0], first[1], second[0], second[1]);
            if(inter != null)
                intersect.add(inter);

            if(first[1] > second[1])
                j++;
            else
                i++;
        }

        return intersect.toArray(new int[intersect.size()][2]);
    }

    private int[] intersect(int f1, int t1, int f2, int t2) {
        int f = Math.max(f1, f2);
        int t = Math.min(t1, t2);

        if(f <= t)
            return new int[]{f, t};
        else
            return null;
    }
}
