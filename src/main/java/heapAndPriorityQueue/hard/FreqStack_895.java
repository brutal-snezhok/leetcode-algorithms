package heapAndPriorityQueue.hard;

import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;

// https://leetcode.com/problems/maximum-frequency-stack/
public class FreqStack_895 {
    Map<Integer, Integer> map;
    Queue<Element> maxHeap;
    int seq;

    public FreqStack_895() {
        map = new HashMap<>(); // {val, freq}
        maxHeap = new PriorityQueue<>((el1, el2) -> el2.freq - el1.freq != 0 ? el2.freq - el1.freq : el2.seq - el1.seq);
        seq = 0;
    }

    public void push(int val) {
        map.put(val, map.getOrDefault(val, 0) + 1);
        maxHeap.add(new Element(val, map.get(val), seq++));
    }

    public int pop() {
        int val = maxHeap.poll().val;

        if(map.get(val) > 1)
            map.put(val, map.get(val) - 1);
        else
            map.remove(val);

        return val;
    }

    class Element {
        int val;
        int freq;
        int seq;

        public Element(int val, int freq, int seq) {
            this.val = val;
            this.freq = freq;
            this.seq = seq;
        }
    }
}
