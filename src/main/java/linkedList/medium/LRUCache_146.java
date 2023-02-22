package linkedList.medium;

import java.util.HashMap;
import java.util.Map;

// https://leetcode.com/problems/lru-cache/
public class LRUCache_146 {
    // use map {key, node} and double linked list to track lru element
    Map<Integer, Node> map;
    Node lru;
    Node mfu;
    int size;
    int cap;

    public LRUCache_146(int capacity) {
        map = new HashMap<>();
        lru = new Node(null, -1, -1, null);
        mfu = new Node(lru, -2, -2, null);
        lru.next = mfu;
        cap = capacity;
        size = 0;
    }

    // time O(1)
    public int get(int key) {
        if (!map.containsKey(key))
            return -1;

        Node node = map.get(key);
        updateLinks(node);

        return node.val;
    }

    // time avg O(1)
    public void put(int key, int value) {
        if (map.containsKey(key)) {
            Node node = map.get(key);
            node.val = value;
            updateLinks(node);
            return;
        }

        if (size < cap) {
            Node node = addToList(key, value);
            map.put(key, node);
            size++;
            return;
        }

        // case when size == cap (size can't be bigger than cap)
        // need to remove from list lru element
        // add new element to list and map
        int keyOfRemovedEl = removeFromList();
        map.remove(keyOfRemovedEl);
        Node node = addToList(key, value);
        map.put(key, node);
    }

    private void updateLinks(Node node) {
        Node nextNode = node.next;
        Node prevNode = node.prev;
        nextNode.prev = prevNode;
        prevNode.next = nextNode;

        Node prevMfu = mfu.prev;
        node.next = mfu;
        node.prev = prevMfu;
        prevMfu.next = node;
        mfu.prev = node;
    }


    private Node addToList(int key, int val) { // return added node
        if (size == 0) {
            Node node = new Node(lru, key, val, mfu);
            lru.next = node;
            mfu.prev = node;
            return node;
        }

        Node prevMfu = mfu.prev;
        Node node = new Node(prevMfu, key, val, mfu);
        prevMfu.next = node;
        mfu.prev = node;

        return node;
    }

    private int removeFromList() { // return key of remove lru element
        Node removeNode = lru.next;
        lru.next = removeNode.next;
        removeNode.next.prev = lru;

        return removeNode.key;
    }


    class Node {
        Node prev;
        int key;
        int val;
        Node next;

        public Node(Node prev, int key, int val, Node next) {
            this.key = key;
            this.val = val;
            this.prev = prev;
            this.next = next;
        }
    }

    public static void main(String[] args) {
        LRUCache_146 lRUCache = new LRUCache_146(2);
        lRUCache.put(1, 1); // cache is {1=1}
        lRUCache.put(2, 2); // cache is {1=1, 2=2}
        lRUCache.get(1);    // return 1
        lRUCache.put(3, 3); // LRU key was 2, evicts key 2, cache is {1=1, 3=3}
        lRUCache.get(2);    // returns -1 (not found)
        lRUCache.put(4, 4); // LRU key was 1, evicts key 1, cache is {4=4, 3=3}
        lRUCache.get(1);    // return -1 (not found)
        lRUCache.get(3);    // return 3
        lRUCache.get(4);    // return 4
    }
}
