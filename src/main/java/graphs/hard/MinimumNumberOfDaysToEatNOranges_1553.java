package graphs.hard;

import java.util.*;

// https://leetcode.com/problems/minimum-number-of-days-to-eat-n-oranges/
public class MinimumNumberOfDaysToEatNOranges_1553 {
    // solution1
    public int minDays(int n) {
        // bfs
        // time O(3^logn)
        // space O(3^logn)

        if (n == 1)
            return 1;

        Queue<Integer> q = new LinkedList<>();
        q.add(n);
        int days = 0;
        Set<Integer> seen = new HashSet<>();

        while (!q.isEmpty()) {
            int size = q.size();
            for (int i = 0; i < size; i++) {
                int curr = q.poll();

                if (curr == 1)
                    return days + 1;

                // if num is already seen, continue to next iteration
                if (seen.contains(curr)) {
                    continue;
                }
                seen.add(curr);

                // below is 3 variants
                if (curr % 2 == 0)
                    q.add(curr / 2);

                if (curr % 3 == 0)
                    q.add(curr / 3);

                q.add(curr - 1);
            }

            days++;
        }

        return days;
    }

    // solution2
    public int minDays2(int n) {
        // dfs
        // need to check time & space complexity
        // time O(log^2(N)
        // space O(log^2(N)

        Map<Integer, Integer> dp = new HashMap<>();
        dp.put(0, 0);
        dp.put(1, 1);

        return dfs(n, dp);
    }

    private int dfs(int n, Map<Integer, Integer> dp) {
        if(dp.containsKey(n))
            return dp.get(n);

        int one = 1 + (n % 2) + dfs(n / 2, dp);
        int two = 1 + (n % 3) + dfs(n / 3, dp);
        dp.put(n, Math.min(one, two));

        return dp.get(n);
    }
}
