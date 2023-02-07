package trie.medium;

import java.util.HashMap;
import java.util.Map;

// https://leetcode.com/problems/implement-trie-prefix-tree/description/
public class ImplementTrie_PrefixTree_208_MapSolution {
    TreeNode root;

    public ImplementTrie_PrefixTree_208_MapSolution() {
        root = new TreeNode();
    }

    public void insert(String word) {
        TreeNode curr = root;
        for(char ch : word.toCharArray()) {
            if(!curr.children.containsKey(ch))
                curr.children.put(ch, new TreeNode());

            curr = curr.children.get(ch);
        }
        curr.isWord = true;
    }

    public TreeNode searchPrefix(String prefix) {
        TreeNode curr = root;
        for(char ch : prefix.toCharArray()) {
            if(!curr.children.containsKey(ch))
                return null;

            curr = curr.children.get(ch);
        }

        return curr;
    }

    public boolean search(String word) {
        TreeNode node = searchPrefix(word);
        return node != null && node.isWord;
    }

    public boolean startsWith(String prefix) {
        TreeNode node = searchPrefix(prefix);
        return node != null;
    }

    class TreeNode {
        Map<Character, TreeNode> children = new HashMap<>();
        boolean isWord;
    }
}
