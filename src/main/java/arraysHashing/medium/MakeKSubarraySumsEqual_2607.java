package arraysHashing.medium;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

// https://leetcode.com/problems/make-k-subarray-sums-equal/description/
public class MakeKSubarraySumsEqual_2607 {
    public long makeSubKSumEqual(int[] arr, int k) {
        // time O(nlogn), can be reduced to O(n) using quick select
        // space O(n)

        int n = arr.length;
        if(k == n)
            return 0;

        // vals on indexes should be equal: arr[i] = arr[(i + k) % n] = arr[(i + 2*k) % n] = ...
        // find median val for all group of indexes
        // difference between each value and the median add to the result

        boolean[] visited = new boolean[n];
        long res = 0;
        for(int i = 0; i < n; i++) {
            if (visited[i]) continue; // we already considered this set

            List<Integer> vals = new ArrayList<>();
            vals.add(arr[i]);
            int next = (i + k) % n;

            while(next != i) {
                visited[next] = true;
                vals.add(arr[next]);
                next = (next + k) % n;
            }

            Collections.sort(vals);
            int median = vals.get(vals.size() / 2);

            // add diff
            for(int val : vals)
                res += Math.abs(val - median);
        }

        return res;
    }
}
