package dp;

import java.util.*;

// https://leetcode.com/problems/word-break/
public class WordBreak_139 {

    // solution1
    public boolean wordBreak1(String s, List<String> wordDict) {
        // time O(2^n), TLE
        // space O(n)

        Set<String> dict = new HashSet<>(wordDict);
        return dfs(s, dict, 0);
    }

    private boolean dfs(String s, Set<String> dict, int startInd) {
        if(startInd == s.length())
            return true;

        for(int i = startInd + 1; i <= s.length(); i++) {
            if(dict.contains(s.substring(startInd, i)) && dfs(s, dict, i))
                return true;
        }

        return false;
    }

    // solution2
    public boolean wordBreak2(String s, List<String> wordDict) {
        // top down, memo
        // time O(n^3)
        // space O(n)

        Set<String> dict = new HashSet<>(wordDict);
        Boolean[] memo = new Boolean[s.length()];

        return dfs(s, dict, 0, memo);
    }

    private boolean dfs(String s, Set<String> dict, int startInd, Boolean[] memo) {
        if(startInd == s.length())
            return true;
        if(memo[startInd] != null)
            return memo[startInd];

        for(int i = startInd + 1; i <= s.length(); i++) {
            if(dict.contains(s.substring(startInd, i)) && dfs(s, dict, i, memo))
                return memo[startInd] = true;
        }

        return memo[startInd] = false;
    }

    // solution3
    public boolean wordBreak3(String s, List<String> wordDict) {
        // bottom up
        // time O(n^3)
        // space O(n)

        Set<String> dict = new HashSet<>(wordDict);
        boolean[] dp = new boolean[s.length() + 1];
        dp[0] = true;

        for(int i = 1; i <= s.length(); i++) {
            for(int j = 0; j < i; j++) {
                String sub = s.substring(j, i);
                if(dp[j] && dict.contains(sub)) {
                    dp[i] = true;
                    break;
                }
            }
        }

        return dp[s.length()];
    }

    // solution4
    public boolean wordBreak4(String s, List<String> wordDict) {
        // time O(n*n*m), n - length of s, m - number of words in dic
        // space O(n)

        int n = s.length();
        boolean[] dp = new boolean[n + 1];
        dp[n] = true;

        for(int i = n - 1; i >= 0; i--) {
            for(String word : wordDict) {
                int l = word.length();
                if(i + l <= n && s.substring(i, i + l).equals(word))
                    dp[i] = dp[i + l];

                if(dp[i])
                    break;
            }
        }

        return dp[0];
    }

    // solution5
    // https://leetcode.com/problems/word-break/solutions/1023786/trie-dfs-solution/
    public boolean wordBreak5(String s, List<String> wordDict) {
        // solution: trie + dfs
        // time O(n^2 + m*k), n - length of s, m - length of wordDict, k - avg length of wordDict
        // space O(n + m*k)

        Trie trie = new Trie(s.length());
        for(String word : wordDict)
            trie.insert(word);

        return trie.dfs(s, 0);
        // return trie.isWordBreak(s, 0);
    }

    class Trie {

        Node root;
        boolean[] visited;

        public Trie(int n) {
            root = new Node();
            visited = new boolean[n];
        }

        public void insert(String word) {
            Node curr = root;
            for(char ch : word.toCharArray()) {
                if(!curr.children.containsKey(ch))
                    curr.children.put(ch, new Node());

                curr = curr.children.get(ch);
            }

            curr.isWord = true;
        }

        // recursive
         public boolean dfs(String s, int ind) {
             Node curr = root;
             if(ind == s.length())
                 return true;

             if(visited[ind])
                 return false;
             visited[ind] = true;

             while(ind < s.length() && curr.children.containsKey(s.charAt(ind))) {
                 curr = curr.children.get(s.charAt(ind));

                 if(curr.isWord && dfs(s, ind + 1))
                     return true;
                 ind++;
             }

             return false; // not found, backtrack
         }

         // iterative
        public boolean isWordBreak(String s, int ind) {
            for (int i = 0; i < s.length(); i++) {
                if (i == 0 || visited[i - 1]) {
                    Node curr = root;
                    for (int j = i; j < s.length(); j++) {
                        char c = s.charAt(j);
                        if (!curr.children.containsKey(c)) {
                            // No words exist
                            break;
                        }

                        curr = curr.children.get(c);
                        if (curr.isWord) {
                            visited[j] = true;
                        }
                    }
                }
            }

            return visited[s.length() - 1];
        }

        class Node {
            Map<Character, Node> children = new HashMap<>();
            boolean isWord;
        }
    }

    public static void main(String[] args) {
        WordBreak_139 word = new WordBreak_139();
        word.wordBreak3("leetcode", Arrays.asList("leet","code"));
    }
}
