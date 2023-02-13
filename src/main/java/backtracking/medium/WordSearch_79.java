package backtracking.medium;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

// https://leetcode.com/problems/word-search/description/
public class WordSearch_79 {

    public boolean exist(char[][] board, String word) {
        // time O(m*n*4^l), l - length of string
        // space O(l), l - length of string

        Set<Pair> haveSeen = new HashSet<>();
        for(int i = 0; i < board.length; i++) {
            for(int j = 0; j < board[0].length; j++) {
                if(dfs(board, word, new Pair(i, j), haveSeen, 0))
                    return true;
            }
        }

        return false;
    }

    private boolean dfs(char[][] board, String word, Pair p, Set<Pair> haveSeen, int ind) {
        if(ind == word.length()) return true;
        if(p.x < 0 || p.y < 0 || p.x >= board.length || p.y >= board[0].length) return false;
        if(word.charAt(ind) != board[p.x][p.y] || haveSeen.contains(p)) return false;

        haveSeen.add(p);
        boolean res = dfs(board, word, new Pair(p.x + 1, p.y), haveSeen, ind + 1) ||
                dfs(board, word, new Pair(p.x - 1, p.y), haveSeen, ind + 1) ||
                dfs(board, word, new Pair(p.x, p.y + 1), haveSeen, ind + 1) ||
                dfs(board, word, new Pair(p.x, p.y - 1), haveSeen, ind + 1);
        haveSeen.remove(p);

        return res;
    }

    class Pair {
        int x;
        int y;

        public Pair(int x, int y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Pair pair = (Pair) o;
            return x == pair.x && y == pair.y;
        }

        @Override
        public int hashCode() {
            return Objects.hash(x, y);
        }
    }

// --------------------------------------------------------------------------------------------
    // more optimal solution
    public boolean exist2(char[][] board, String word) {
        // time O(m*n*4^l), l - length of string
        // space O(l), l - length of string

        boolean[][] visited = new boolean[board.length][board[0].length];
        for(int r = 0; r < board.length; r++) {
            for(int c = 0; c < board[0].length; c++) {
                if(dfs2(board, word, visited, r, c, 0))
                    return true;
            }
        }

        return false;
    }

    private boolean dfs2(char[][] board, String word, boolean[][] visited, int r, int c, int ind) {
        if(ind == word.length()) return true;
        if(r < 0 || c < 0 || r >= board.length || c >= board[0].length) return false;
        if(visited[r][c] || word.charAt(ind) != board[r][c]) return false;

        visited[r][c] = true;
        boolean res = dfs2(board, word, visited, r + 1, c, ind + 1) ||
                dfs2(board, word, visited, r - 1, c, ind + 1) ||
                dfs2(board, word, visited, r, c + 1, ind + 1) ||
                dfs2(board, word, visited, r, c - 1, ind + 1);
        visited[r][c] = false;

        return res;
    }
}
