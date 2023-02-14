package heapAndPriorityQueue.medium;

import java.util.Comparator;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;

// https://leetcode.com/problems/task-scheduler/
public class TaskScheduler_621 {

    public int leastInterval(char[] tasks, int n) {
        // time O(l * log26), l - length of arr tasks
        // space O(26)

        // use maxHeap
        // count in arr {char -> count}
        // add all count to maxHeap
        // create queue to wait there idle time
        // while maxHeap != null || q != null
        // take val from maxHeap time++ check whether we can take val from q and add it to maxHeap

        if(n == 0) return tasks.length;

        int[] arr = new int[26];
        for(char ch : tasks)
            arr[ch - 'A']++;

        Queue<Integer> maxHeap = new PriorityQueue<>(Comparator.reverseOrder());
        Queue<Pair> q = new LinkedList<>();
        for(int task : arr) {
            if(task != 0)
                maxHeap.add(task);
        }

        int time = 0;
        while(!maxHeap.isEmpty() || !q.isEmpty()) {
            time++;
            if(!maxHeap.isEmpty()) {
                int val = maxHeap.poll();
                val--;
                if(val > 0)
                    q.add(new Pair(val, time + n));
            }

            if(!q.isEmpty() && q.peek().time <= time)
                maxHeap.add(q.remove().val);
        }

        return time;
    }

    class Pair{
        int val;
        int time;

        public Pair(int val, int time) {
            this.val = val;
            this.time = time;
        }
    }
}
