package heapAndPriorityQueue.medium;

import java.util.*;

// https://leetcode.com/problems/single-threaded-cpu/
public class SingleThreadedCPU_1834 {
    public int[] getOrder(int[][] tasks) {
        // time O(nlogn)
        // space O(n)

        // sort tasks by enqueueTime, then by processingTime
        // create minHeap with comparator by processingTime, then by index
        // while minHeap is not empty or we have some tasks to process do algo

        List<Task> tasksList = new ArrayList<>();
        int[] res = new int[tasks.length];
        int ind = 0;
        for (int[] task : tasks)
            tasksList.add(new Task(task[0], task[1], ind++));

        Collections.sort(tasksList, (t1, t2) -> t1.enq - t2.enq == 0 ? t1.proc - t2.proc : t1.enq - t2.enq);
        Queue<Task> minHeap = new PriorityQueue<>((t1, t2) -> t1.proc - t2.proc == 0 ? t1.ind - t2.ind : t1.proc - t2.proc);

        int i = 0; // counter for tasks
        int j = 0; // counter for res
        int currTime = 0;
        while (!minHeap.isEmpty() || i != tasksList.size()) {
            // if minHeap is empty and currTime < first enq time from list, update currTime
            if (minHeap.isEmpty() && currTime < tasksList.get(i).enq)
                currTime = tasksList.get(i).enq;

            // add all tasks to heap with enq time <= currTime
            while (i < tasksList.size() && tasksList.get(i).enq <= currTime)
                minHeap.add(tasksList.get(i++));

            Task task = minHeap.poll();
            res[j++] = task.ind;
            currTime += task.proc;
        }

        return res;
    }

    class Task {
        int enq;
        int proc;
        int ind;

        public Task(int enq, int proc, int ind) {
            this.enq = enq;
            this.proc = proc;
            this.ind = ind;
        }
    }

}
