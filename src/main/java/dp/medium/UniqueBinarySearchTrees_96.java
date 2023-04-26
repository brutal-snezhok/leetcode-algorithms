package dp.medium;

// https://leetcode.com/problems/unique-binary-search-trees/
// article: https://leetcode.com/problems/unique-binary-search-trees/discuss/1565543/C%2B%2BPython-5-Easy-Solutions-w-Explanation-or-Optimization-from-Brute-Force-to-DP-to-Catalan-O(N)
public class UniqueBinarySearchTrees_96 {
    // solution1
    public int numTrees1(int n) {
        // time O(3^n)
        // space O(n)

        if(n <= 1)
            return 1;

        // i node is the root
        // left could be from 1 to i - 1
        // right could be from n - 1 to 0

        int res = 0;
        for(int i = 1; i <= n; i++) {
            int left = numTrees1(i - 1); // number of combinations in left subtree
            int right = numTrees1(n - i); // in right subtree
            res +=  left * right;
        }

        return res;
    }

    // solution2
    int[] dp = new int[20];

    {
        dp[0] = 1;
        dp[1] = 1;
    }

    public int numTrees2(int n) {
        // dp, memo
        // time O(n^2)
        // space O(n)

        if(dp[n] != 0)
            return dp[n];

        int res = 0;
        for(int i = 1; i <= n; i++)
            res += numTrees2(i - 1) * numTrees2(n - i);

        return dp[n] = res;
    }

    // solution3
    public int numTrees3(int n) {
        // dp, tabulation
        // time O(n^2)
        // space O(n)

        int[] dp = new int[20];
        dp[0] = 1;
        dp[1] = 1;

        for(int i = 2; i <= n; i++) // i - total num of nodes
            for(int j = 1; j <= i; j++) // j - curr root node
                dp[i] += dp[j - 1] * dp[i - j];

        return dp[n];
    }

    public static void main(String[] args) {
        UniqueBinarySearchTrees_96 unique = new UniqueBinarySearchTrees_96();
        System.out.println(unique.numTrees1(3));
    }
}
