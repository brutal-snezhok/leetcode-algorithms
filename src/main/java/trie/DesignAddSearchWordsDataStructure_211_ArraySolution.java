package trie;

// https://leetcode.com/problems/design-add-and-search-words-data-structure/
public class DesignAddSearchWordsDataStructure_211_ArraySolution {
    Node root;

    public DesignAddSearchWordsDataStructure_211_ArraySolution() {
        root = new Node();
    }

    public void addWord(String word) {
        Node curr = root;
        for(char ch : word.toCharArray()) {
            if(curr.children[ch - 'a'] == null)
                curr.children[ch - 'a'] = new Node();

            curr = curr.children[ch - 'a'];
        }
        curr.isWord = true;
    }

    public boolean search(String word) {
        return searchHelper(word, root, 0);
    }

    private boolean searchHelper(String word, Node curr, int ind) {
        for(int i = ind; i < word.length(); i++) {
            char ch = word.charAt(i);
            if(ch == '.') {
                // check all children instead of .
                for(Node node : curr.children) {
                    if(node != null && searchHelper(word, node, i + 1))
                        return true;
                }

                return false;
            }

            if(curr.children[ch - 'a'] == null)
                return false;
            curr = curr.children[ch - 'a'];
        }

        return curr.isWord;
    }

    class Node {
        Node[] children = new Node[26];
        boolean isWord;
    }
}
