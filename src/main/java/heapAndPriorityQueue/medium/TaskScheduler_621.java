package heapAndPriorityQueue.medium;

import java.util.Comparator;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;

// https://leetcode.com/problems/task-scheduler/
public class TaskScheduler_621 {

    public static int leastInterval(char[] tasks, int n) {
        // time O(n*log26) = O(n)
        // space O(n)
        if(n == 0) return tasks.length;
        Queue<Integer> pq = new PriorityQueue<>(Comparator.reverseOrder());
        Queue<Pair<Integer, Integer>> q = new LinkedList<>();
        int[] arr = new int[26];
        for(char ch : tasks)
            arr[ch - 'A']++;
        for(int num : arr) {
            if(num > 0)
                pq.add(num);
        }

        int time = 0;
        while(!pq.isEmpty() || !q.isEmpty()) {
            time++;
            if(!pq.isEmpty()) {
                int val = pq.poll();
                val--;
                if(val > 0)
                    q.add(new Pair(val, time + n));
            }

            if(!q.isEmpty() && q.peek().value == time)
                pq.add(q.poll().key);
        }

        return time;
    }

    static class Pair<K, V> {
        K key;
        V value;

        public Pair(K key, V value) {
            this.key = key;
            this.value = value;
        }
    }

    public static void main(String[] args) {

    }
}
