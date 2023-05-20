package dp.medium.fibonacci_numbers;

import java.util.Arrays;

// https://leetcode.com/problems/minimum-cost-for-tickets/description/
public class MinimumCostForTickets_983 {
    // solution1
    public int mincostTickets1(int[] days, int[] costs) {
        // brute force
        // time O(3^n)
        // space O(n)

        // startDay = startDay + 1/7/30 - 1;
        // if(startDay > days[n - 1])
        //  return 0;
        // days = [1,4,6,7,8,20]
        // for(by days)
        //     if(startDay > day)
        //      for(by ways)
        //          startDay = startDay + 1/7/30;
        //          cost = dfs( , , startDay) + cost[i]

        return dfs(days, costs, 0);
    }

    private int dfs(int[] days, int[] costs, int startDayInd) {
        if(startDayInd == days.length)
            return 0;

        int min = Integer.MAX_VALUE;
        min = Math.min(min, costs[0] + dfs(days, costs, getStartNextInd(days, startDayInd, 1)));
        min = Math.min(min, costs[1] + dfs(days, costs, getStartNextInd(days, startDayInd, 7)));
        min = Math.min(min, costs[2] + dfs(days, costs, getStartNextInd(days, startDayInd, 30)));

        return min;
    }

    // solution2
    public int mincostTickets2(int[] days, int[] costs) {
        // top down, dp
        // time O(n)
        // space O(n)

        int[] memo = new int[days.length];
        Arrays.fill(memo, -1);

        return dfs(days, costs, 0, memo);
    }

    private int dfs(int[] days, int[] costs, int startDayInd, int[] memo) {
        if(startDayInd == days.length)
            return 0;
        if(memo[startDayInd] != -1)
            return memo[startDayInd];

        int min = Integer.MAX_VALUE;
        min = Math.min(min, costs[0] + dfs(days, costs, getStartNextInd(days, startDayInd, 1), memo));
        min = Math.min(min, costs[1] + dfs(days, costs, getStartNextInd(days, startDayInd, 7), memo));
        min = Math.min(min, costs[2] + dfs(days, costs, getStartNextInd(days, startDayInd, 30), memo));

        memo[startDayInd] = min;
        return memo[startDayInd];
    }

    private int getStartNextInd(int[] days, int startInd, int duration) {
        // can replace with binary search since days[] sorted
        int endDay = days[startInd] + duration;

        int endInd = startInd;
        while(endInd < days.length) {
            if(days[endInd] >= endDay)
                break;
            endInd++;
        }

        return endInd;
    }

    // solution3
    public int mincostTickets(int[] days, int[] costs) {
        // https://leetcode.com/problems/minimum-cost-for-tickets/solutions/227130/java-dp-solution-with-detailed-comment-and-explanation/
        // bottom up, dp
        // time O(n)
        // space O(n)

        // dp[i] = cost[] + dp[i - severalDays]; - minimum number of dollars need to travel i days
        int lastDay = days[days.length - 1];
        int[] dp = new int[lastDay + 1];

        boolean[] isTravelDays = new boolean[lastDay + 1];
        // mark the travel days
        for(int day : days) isTravelDays[day] = true;

        dp[0] = 0;
        for(int i = 1; i <= lastDay; i++) {
            if(!isTravelDays[i]) { // no need to buy ticket if it is not a travel day
                dp[i] = dp[i - 1];
                continue;
            }

            dp[i] = costs[0] + dp[i - 1];
            dp[i] = Math.min(dp[i], costs[1] + dp[Math.max(i - 7, 0)]);
            dp[i] = Math.min(dp[i], costs[2] + dp[Math.max(i - 30, 0)]);
        }

        return dp[lastDay];
    }
}
