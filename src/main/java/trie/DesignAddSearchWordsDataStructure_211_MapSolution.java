package trie;

import java.util.HashMap;
import java.util.Map;

// https://leetcode.com/problems/design-add-and-search-words-data-structure/description/
public class DesignAddSearchWordsDataStructure_211_MapSolution {
    Node root;

    public DesignAddSearchWordsDataStructure_211_MapSolution() {
        root = new Node();
    }

    public void addWord(String word) {
        Node curr = root;
        for (char ch : word.toCharArray()) {
            Map<Character, Node> children = curr.children;
            if (!children.containsKey(ch))
                children.put(ch, new Node());

            curr = children.get(ch);
        }
        curr.isEnd = true;
    }

    public boolean search(String word) {
        return searchHelper(word, root, 0);
    }

    private boolean searchHelper(String word, Node curr, int ind) {
        for (int i = ind; i < word.length(); i++) {
            char ch = word.charAt(i);
            if (ch == '.') {
                for (Node node : curr.children.values()) {
                    if (node != null && searchHelper(word, node, i + 1)) {
                        return true;
                    }
                }

                return false;
            }

            Map<Character, Node> children = curr.children;
            if (!children.containsKey(ch))
                return false;

            curr = children.get(ch);
        }

        return curr.isEnd;
    }


    class Node {
        Map<Character, Node> children = new HashMap<>();
        boolean isEnd;
    }
}
