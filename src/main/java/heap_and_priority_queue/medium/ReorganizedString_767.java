package heap_and_priority_queue.medium;

import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;

// https://leetcode.com/problems/reorganize-string/description/
public class ReorganizedString_767 {
    public String reorganizeString(String s) {
        // time O(26n)
        // space O(26)
        if(s == null || s.length() == 0)
            return "";

        // 1. go through string, count how many times each char appears
        // 2. create map: {char, count}
        // 3. create priority queue & add pairs from map with comparator by count
        // 4. go throw queue and create res string

        // [aaab]
        // map = {a - 3, b - 1}
        // step 1:
        // res =
        Map<Character, Integer> counts = new HashMap<>();
        for(char ch : s.toCharArray())
            counts.put(ch, counts.getOrDefault(ch, 0) + 1);

        Queue<Map.Entry<Character, Integer>> pq = new PriorityQueue<>((p1, p2) -> p2.getValue() - p1.getValue());
        pq.addAll(counts.entrySet());
        StringBuilder res = new StringBuilder();
        while(!pq.isEmpty()) {
            Map.Entry<Character, Integer> p = pq.poll(); // a - 3, a - 2, a - 2, a - 1
            int n = res.length(); // 0, 1, 2, 3
            if(n > 0 && res.charAt(n - 1) == p.getKey()) {
                if(pq.isEmpty())
                    return "";

                Map.Entry<Character, Integer> next = pq.poll(); // b - 1,
                res.append(next.getKey());  // res = ab
                next.setValue(next.getValue() - 1); // b - 0
                if(next.getValue() > 0) // pq: a - 2
                    pq.add(next);
            } else {
                res.append(p.getKey()); // res = a, aba
                p.setValue(p.getValue() - 1); // a - 2, a- 1
            }

            if(p.getValue() > 0)
                pq.add(p); // pq: a - 2, b - 1, a - 2, a - 1
        }
        // res = ab
        return res.toString();
    }
}
