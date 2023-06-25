package heapAndPriorityQueue.medium;

import java.util.PriorityQueue;
import java.util.Queue;

// https://leetcode.com/problems/longest-happy-string/
public class LongestHappyString_1405 {

//    public static String longestDiverseString(int a, int b, int c) {
//        // time O(1), because only 3 characters
//        // space O(1), we do not count return values space
//        Queue<Pair> pq = new PriorityQueue<>((p1, p2) -> p2.count - p1.count);
//        if(a > 0)
//            pq.add(new Pair('a', a));
//        if(b > 0)
//            pq.add(new Pair('b', b));
//        if(c > 0)
//            pq.add(new Pair('c', c));
//
//        StringBuilder res = new StringBuilder();
//        while(!pq.isEmpty()) {
//            Pair p = pq.poll(); // take first character
//            int n = res.length();
//            if(n > 1 && p.ch == res.charAt(n - 1) && p.ch == res.charAt(n - 2)) {
//                if(pq.isEmpty())
//                    break;
//
//                Pair next = pq.poll(); // take another character if before it two chars are the same
//                res.append(next.ch);
//                next.count--;
//                if(next.count > 0)
//                    pq.add(next);
//            } else {
//                res.append(p.ch);
//                p.count--;
//            }
//
//            if(p.count > 0)
//                pq.add(p);
//        }
//
//        return res.toString();
//    }
//
//    static class Pair {
//        char ch;
//        int count;
//
//        public Pair(char ch, int count) {
//            this.ch = ch;
//            this.count = count;
//        }
//    }

    public int longestString(int x, int y, int z) {

        Queue<Pair> maxHeap = new PriorityQueue<>((p1, p2) -> p2.count - p1.count);
        maxHeap.add(new Pair(x, "AA"));
        maxHeap.add(new Pair(y, "BB"));
        maxHeap.add(new Pair(z, "AB"));

        StringBuilder res = new StringBuilder();
        while(!maxHeap.isEmpty()) {
            Pair curr = maxHeap.poll();
            int n = res.length();

            if(n > 0 && ((curr.s.charAt(0) == 'A' && res.charAt(n - 1) == 'A') || (curr.s.charAt(0) == 'B' && res.charAt(n - 1) == 'B'))) {
                if(maxHeap.isEmpty())
                    break;

                Pair next = maxHeap.poll();
                if((next.s.charAt(0) == 'A' && res.charAt(n - 1) == 'A') || (next.s.charAt(0) == 'B' && res.charAt(n - 1) == 'B')) {
                    if(maxHeap.isEmpty())
                        break;

                    Pair nextNext = maxHeap.poll();
                    res.append(nextNext.s);
                    nextNext.count -= 1;
                    if(nextNext.count > 0)
                        maxHeap.add(nextNext);

                } else {
                    res.append(next.s);
                    next.count -= 1;
                }

                if(next.count > 0)
                    maxHeap.add(next);


            } else {
                res.append(curr.s);
                curr.count -= 1;
            }

            if(curr.count > 0)
                maxHeap.add(curr);
        }


        return res.length();
    }

    class Pair {
        int count;
        String s;

        public Pair(int count, String s) {
            this.count = count;
            this.s = s;
        }
    }


    public static void main(String[] args) {
        LongestHappyString_1405 longest = new LongestHappyString_1405();
        longest.longestString(3, 2, 2);
    }
}
