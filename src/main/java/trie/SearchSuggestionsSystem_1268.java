package trie;

import java.util.ArrayList;
import java.util.List;

// https://leetcode.com/problems/search-suggestions-system/description/
public class SearchSuggestionsSystem_1268 {
    // TODO: check solution with binarySearch
    // solution1
    public List<List<String>> suggestedProducts(String[] products, String searchWord) {
        // trie + dfs
        // time O(l + m), l - number of all chars in products, m - length of searchWord
        // space O(n), n - number of nodes in trie

        /*
            1. add to trie all products
            2. go in trie till end of curr prefix
            3. then start dfs from this node
        */

        Trie trie = new Trie();
        for(String product : products)
            trie.insert(product);

        List<List<String>> res = new ArrayList<>();
        for(int i = 1; i <= searchWord.length(); i++) {
            List<String> curr = trie.getWordsStartingWith(searchWord.substring(0, i));
            res.add(curr);
        }

        return res;
    }

    class Trie {

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

        public List<String> getWordsStartingWith(String prefix) {
            List<String> res = new ArrayList<>();
            Node curr = root;
            // go to node in trie where prefix will end
            // and start from this node dfs to find 3 word
            for(char ch : prefix.toCharArray()) {
                if(curr.children[ch - 'a'] == null)
                    return res;

                curr = curr.children[ch - 'a'];
            }
            dfs(curr, res);

            return res;
        }

        private void dfs(Node curr, List<String> res) {
            if(res.size() == 3)
                return;

            if(curr.word != null)
                res.add(curr.word);

            for(int i = 0; i < 26; i++) {
                if(curr.children[i] != null)
                    dfs(curr.children[i], res);
            }
        }

        class Node {
            Node[] children = new Node[26];
            String word;
        }
    }
}
