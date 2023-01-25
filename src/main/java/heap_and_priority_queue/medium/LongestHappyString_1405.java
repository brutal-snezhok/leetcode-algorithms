package heap_and_priority_queue.medium;

import java.util.PriorityQueue;
import java.util.Queue;

// https://leetcode.com/problems/longest-happy-string/
public class LongestHappyString_1405 {

    public static String longestDiverseString(int a, int b, int c) {
        // time O(1), because only 3 characters
        // space O(1), we do not count return values space
        Queue<Pair> pq = new PriorityQueue<>((p1, p2) -> p2.count - p1.count);
        if(a > 0)
            pq.add(new Pair('a', a));
        if(b > 0)
            pq.add(new Pair('b', b));
        if(c > 0)
            pq.add(new Pair('c', c));

        StringBuilder res = new StringBuilder();
        while(!pq.isEmpty()) {
            Pair p = pq.poll(); // take first character
            int n = res.length();
            if(n > 1 && p.ch == res.charAt(n - 1) && p.ch == res.charAt(n - 2)) {
                if(pq.isEmpty())
                    break;

                Pair next = pq.poll(); // take another character if before it two chars are the same
                res.append(next.ch);
                next.count--;
                if(next.count > 0)
                    pq.add(next);
            } else {
                res.append(p.ch);
                p.count--;
            }

            if(p.count > 0)
                pq.add(p);
        }

        return res.toString();
    }

    static class Pair {
        char ch;
        int count;

        public Pair(char ch, int count) {
            this.ch = ch;
            this.count = count;
        }
    }


    public static void main(String[] args) {
        System.out.println(longestDiverseString(0, 8, 11));
    }
}
