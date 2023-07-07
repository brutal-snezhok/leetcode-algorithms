package trie;

// https://leetcode.com/problems/longest-word-in-dictionary/description/
public class LongestWordInDictionary_720 {
    // solution1
    // TODO: rewrite through DFS
    // here is good example of DFS for trie: https://leetcode.com/problems/longest-word-in-dictionary/solutions/634793/java-the-easiest-to-understand-trie-solution-100-explanation/
    public String longestWord(String[] words) {
        // time O(n*k), n - num of words, k - avg length of word
        // space O(n*k)
        Trie trie = new Trie();
        for(String word : words)
            trie.insert(word);


        String res = "";
        for(String word : words) {
            if(trie.isCanBuilt(word)) {
                if(word.length() == res.length())
                    res = word.compareTo(res) < 0 ? word : res; // compare lexicographically
                else
                    res = word.length() > res.length() ? word : res; // compare by length
            }
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

            curr.isWord = true;
        }

        public boolean isCanBuilt(String word) {
            Node curr = root;
            for(char ch : word.toCharArray()) {
                curr = curr.children[ch - 'a'];
                if(!curr.isWord)
                    return false;
            }

            return true;
        }

        class Node {
            Node[] children = new Node[26];
            boolean isWord;
        }
    }
}
