package trie;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

// https://leetcode.com/problems/word-search-ii/description/
public class WordSearchII_212 {
    int[][] directions = { {1, 0}, {0, 1}, {-1, 0}, {0, -1}};

    public List<String> findWords(char[][] board, String[] words) {
        // time O(m*n*wl*4^wl), wl - avg length of words
        // space O(m*n + words.length)

        // idea: at the same time traverse trie and do dfs on board

        Trie trie = new Trie();
        for(String w : words)
            trie.insert(w);

        final int ROWS = board.length;
        final int COLS = board[0].length;
        boolean[][] visited = new boolean[ROWS][COLS];
        Set<String> res = new HashSet<>();

        for(int r = 0; r < ROWS; r++) {
            for(int c = 0; c < COLS; c++) {
                dfs(board, r, c, visited, trie.root, res);
            }
        }

        return new ArrayList<String>(res);
    }

    private void dfs(char[][] board, int r, int c, boolean[][] visited, Node curr, Set<String> res) {
        final int ROWS = board.length;
        final int COLS = board[0].length;

        if(r < 0 || r >= ROWS || c < 0 || c >= COLS || visited[r][c] || curr.children[board[r][c] - 'a'] == null)
            return;

        char currCh = board[r][c];
        curr = curr.children[currCh - 'a'];
        visited[r][c] = true;

        if(curr.word != null)
            res.add(curr.word);

        for(int[] dir : directions)
            dfs(board, r + dir[0], c + dir[1], visited, curr, res);

        visited[r][c] = false;
    }

    public class Trie {
        Node root;

        public Trie() {
            root = new Node();
        }

        public void insert(String word) {
            Node curr = root;
            for(char ch : word.toCharArray()) {
                if(curr.children[ch - 'a'] == null)
                    curr.children[ch - 'a'] = new Node();

                curr = curr.children[ch - 'a'];
            }

            curr.word = word;
        }
    }

    public class Node {
        Node[] children = new Node[26];
        String word;
    }
}
