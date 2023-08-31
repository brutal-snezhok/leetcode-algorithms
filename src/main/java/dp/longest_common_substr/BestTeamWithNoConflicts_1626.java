package dp.longest_common_substr;

import java.util.Arrays;

// https://leetcode.com/problems/best-team-with-no-conflicts/description/
public class BestTeamWithNoConflicts_1626 {
    // solution1
    public int bestTeamScore1(int[] scores, int[] ages) {
        // top down with memo
        // time O(n^2)
        // space O(n^2)

        int n = scores.length;
        Pair[] pairs = new Pair[n];

        for(int i = 0; i < n; i++)
            pairs[i] = new Pair(scores[i], ages[i]);

        Arrays.sort(pairs, (p1, p2) -> p1.age - p2.age == 0 ? p1.score - p2.score : p1.age - p2.age);
        Integer[][] memo = new Integer[n][n];

        return dfs(memo, pairs, -1, 0);
    }

    private int dfs(Integer[][] memo, Pair[] pairs, int prevInd, int ind) {
        if(ind == pairs.length)
            return 0;
        if(memo[prevInd + 1][ind] != null)
            return memo[prevInd + 1][ind];

        if(prevInd == -1 || pairs[ind].score >= pairs[prevInd].score)
            return memo[prevInd + 1][ind] = Math.max(dfs(memo, pairs, prevInd, ind + 1),
                                                     pairs[ind].score + dfs(memo, pairs, ind, ind + 1));

        return memo[prevInd + 1][ind] = dfs(memo, pairs, prevInd, ind + 1);
    }

    // solution2
    public int bestTeamScore2(int[] scores, int[] ages) {
        // bottom up, dp
        // time O(n^2)
        // space O(n)

        // create arr of pairs
        // sort arr by age
        // max sum of increasing(not strikly) subseq

        int n = scores.length;
        Pair[] pairs = new Pair[n];

        for(int i = 0; i < n; i++)
            pairs[i] = new Pair(scores[i], ages[i]);

        Arrays.sort(pairs, (p1, p2) -> p1.age - p2.age == 0 ? p1.score - p2.score : p1.age - p2.age);
        int teamScore = 0;

        int[] dp = new int[n];
        for(int i = 0; i < n; i++) {
            dp[i] = pairs[i].score;
            for(int j = 0; j < i; j++) {
                if(pairs[i].score >= pairs[j].score)
                    dp[i] = Math.max(dp[i], dp[j] + pairs[i].score);
            }
            teamScore = Math.max(teamScore, dp[i]);
        }

        return teamScore;
    }

    class Pair {
        int score;
        int age;

        public Pair(int score, int age) {
            this.score = score;
            this.age = age;
        }
    }

    public static void main(String[] args) {
        BestTeamWithNoConflicts_1626 team = new BestTeamWithNoConflicts_1626();
        team.bestTeamScore2(new int[]{4,5,6,5}, new int[]{2,1,2,1});
    }
}
