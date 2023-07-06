package trie;

// https://leetcode.com/problems/implement-trie-prefix-tree/description/
public class ImplementTrie_PrefixTree_208_ArraySolution {
    TreeNode root;

    public ImplementTrie_PrefixTree_208_ArraySolution() {
        root = new TreeNode();
    }

    public void insert(String word) {
        TreeNode curr = root;
        for(char ch : word.toCharArray()) {
            if(curr.children[ch - 'a'] == null)
                curr.children[ch - 'a'] = new TreeNode();

            curr = curr.children[ch - 'a'];
        }
        curr.isWord = true;
    }

    public boolean search(String word) {
        TreeNode curr = root;
        for(char ch : word.toCharArray()) {
            if(curr.children[ch - 'a'] == null)
                return false;

            curr = curr.children[ch - 'a'];
        }

        return curr.isWord;
    }

    public boolean startsWith(String prefix) {
        TreeNode curr = root;
        for(char ch : prefix.toCharArray()) {
            if(curr.children[ch - 'a'] == null)
                return false;

            curr = curr.children[ch - 'a'];
        }

        return true;
    }

    class TreeNode {
        public final static int N = 26;
        TreeNode[] children = new TreeNode[N];
        boolean isWord;
    }
}
