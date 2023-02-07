package trie.medium;

import java.util.HashMap;
import java.util.Map;

// https://leetcode.com/problems/map-sum-pairs/description/
public class MapSumPairs_677 {
    // Time Complexity: Every insert operation is O(K), where K is the length of the key. Every sum operation is O(K).
    // Space Complexity: The space used is linear in the size of the total input.
    Node root;
    Map<String, Integer> map; // for rewriting values if the same key

    public MapSumPairs_677() {
        root = new Node();
        map = new HashMap<>();
    }

    public void insert(String key, int val) {
        Node curr = root;
        for(char ch : key.toCharArray()) {
            if(curr.children[ch - 'a'] == null)
                curr.children[ch - 'a'] = new Node();

            curr.children[ch - 'a'].val += val;

            if(map.containsKey(key))
                curr.children[ch - 'a'].val -= map.get(key);

            curr = curr.children[ch - 'a'];
        }
        map.put(key, val);
    }

    public int sum(String prefix) {
        Node curr = root;
        for(char ch : prefix.toCharArray()) {
            if(curr.children[ch - 'a'] == null)
                return 0;

            curr = curr.children[ch - 'a'];
        }

        return curr.val;
    }

    class Node {
        Node[] children = new Node[26];
        int val;
    }
}
