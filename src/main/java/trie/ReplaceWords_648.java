package trie;

import java.util.List;

// https://leetcode.com/problems/replace-words/description/
public class ReplaceWords_648 {
    // solution1
    public String replaceWords1(List<String> dictionary, String sentence) {
        // time O(n*k), n - number of strings, k - avg length of dictionary string
        // space O(k)

        Trie t = new Trie();
        StringBuilder res = new StringBuilder();
        // insert dictionary values into trie
        for(String s : dictionary)
            t.insert(s);

        // search root values in trie
        String[] splitted = sentence.split(" ");
        for(String s : splitted) {
            if(res.length() > 0)
                res.append(" ");

            String fromTrie = t.search(s);
            res.append(fromTrie == null ? s : fromTrie);
        }

        return res.toString();
    }

    class Trie {
        Node root;

        public Trie() {
            root = new Node();
        }

        public void insert(String str) {
            Node curr = root;
            for(char ch : str.toCharArray()) {
                if(curr.children[ch - 'a'] == null)
                    curr.children[ch - 'a'] = new Node();

                curr = curr.children[ch - 'a'];
            }
            curr.val = str;
        }

        public String search(String str) {
            Node curr = root;
            for(int i = 0; i < str.length(); i++) {
                char ch = str.charAt(i);
                if(curr.children[ch - 'a'] == null || curr.val != null)
                    break;

                curr = curr.children[ch - 'a'];
            }

            return curr.val;
        }
    }

    class Node {
        Node[] children = new Node[26];
        String val;
    }

    // solution2
    public String replaceWords2(List<String> dictionary, String sentence) {
        // time O(dict.length * avg word l + sentence.length * avg word l)
        // space O(dict.length * avg word l + sentence.length * avg word l) = size of trie + size of words arr
        Trie2 trie = new Trie2();
        for(String w : dictionary)
            trie.insert(w);

        String[] words = sentence.split(" ");
        StringBuilder res = new StringBuilder();
        for(String word : words) {
            String root = trie.searchRoot(word);
            if(root == null)
                res.append(word);
            else
                res.append(root);
            res.append(" ");
        }

        res.setLength(res.length() - 1); // remove last space
        return res.toString();
    }

    class Trie2 {

        Node root;

        public Trie2() {
            root = new Node();
        }

        public void insert(String word) {
            Node curr = root;
            for(char ch : word.toCharArray()) {
                if(curr.children[ch - 'a'] == null)
                    curr.children[ch - 'a'] = new Node();

                curr = curr.children[ch - 'a'];
            }

            curr.isRoot = true;
        }

        public String searchRoot(String word) {
            Node curr = root;
            for(int i = 0; i < word.length(); i++) {
                char ch = word.charAt(i);

                if(curr.isRoot)
                    return word.substring(0, i);

                if(curr.children[ch - 'a'] == null)
                    return null;

                curr = curr.children[ch - 'a'];
            }

            return word;
        }

        class Node {
            Node[] children = new Node[26];
            boolean isRoot;
        }
    }
}
