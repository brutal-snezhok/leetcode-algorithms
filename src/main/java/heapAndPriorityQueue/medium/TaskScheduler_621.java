package heapAndPriorityQueue.medium;

import java.util.*;

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

        if (n == 0) return tasks.length;

        int[] arr = new int[26];
        for (char ch : tasks)
            arr[ch - 'A']++;

        Queue<Integer> maxHeap = new PriorityQueue<>(Comparator.reverseOrder());
        Queue<Pair> q = new LinkedList<>();
        for (int task : arr) {
            if (task != 0)
                maxHeap.add(task);
        }

        int time = 0;
        while (!maxHeap.isEmpty() || !q.isEmpty()) {
            time++;
            if (!maxHeap.isEmpty()) {
                int val = maxHeap.poll();
                val--;
                if (val > 0)
                    q.add(new Pair(val, time + n));
            }

            if (!q.isEmpty() && q.peek().time <= time)
                maxHeap.add(q.remove().val);
        }

        return time;
    }

    class Pair {
        int val;
        int time;

        public Pair(int val, int time) {
            this.val = val;
            this.time = time;
        }
    }

    public int leastInterval2(char[] tasks, int n) {
        // time O(nlogn), n - number of tasks
        // space O(n)

        // map: {char, count}
        // maxHeap: {char, count} comparing by count

        // take element with the highest count, put  in queue {char, count, time} time - when can we take from queue
        if (n == 0)
            return tasks.length;

        Map<Character, Integer> map = new HashMap<>();
        for (char ch : tasks)
            map.put(ch, map.getOrDefault(ch, 0) + 1);

        Queue<Element> maxHeap = new PriorityQueue<>((p1, p2) -> p2.count - p1.count);
        for (Map.Entry<Character, Integer> entry : map.entrySet())
            maxHeap.add(new Element(entry.getKey(), entry.getValue()));

        int res = 0;
        while (!maxHeap.isEmpty()) {
            Queue<Element> q = new LinkedList<>();
            int k = n + 1;

            while (k > 0 && !maxHeap.isEmpty()) {
                res++;
                Element el = maxHeap.poll();
                el.count -= 1;
                if (el.count > 0)
                    q.add(el);
                k--;
            }

            maxHeap.addAll(q);
            if (!maxHeap.isEmpty())
                res += k;
        }

        return res;
    }

    class Element {
        char ch;
        int count;

        public Element(char ch, int count) {
            this.ch = ch;
            this.count = count;
        }
    }

    // solution3
    public int leastInterval3(char[] tasks, int n) {
        // time O(nlogn)
        // space O(n)

        /**
         1. count all chars to map
         2. put all values from map into maxHeap
         3. take max val from heap and put to waitList(queue) with currTime + n
         4. check on each step if time from queue can be taken
         5. if maxHeap.isEmpty then take val from waitList and update currTime to time from waitList
         */

        Map<Character, Integer> freq = new HashMap<>();
        for (char ch : tasks)
            freq.put(ch, freq.getOrDefault(ch, 0) + 1);

        Queue<Integer> maxHeap = new PriorityQueue<>((v1, v2) -> v2 - v1);
        maxHeap.addAll(freq.values());
        Queue<Pair> waitList = new LinkedList<>();

        int currTime = 0;
        while (!maxHeap.isEmpty() || !waitList.isEmpty()) {
            if (!maxHeap.isEmpty()) {
                Integer val = maxHeap.poll();
                val--;
                if (val > 0)
                    waitList.add(new Pair(val, currTime + n));
            } else {
                currTime = waitList.peek().time;
            }

            while (!waitList.isEmpty() && waitList.peek().time <= currTime)
                maxHeap.add(waitList.remove().val);

            currTime++;
        }

        return currTime;
    }

    // solution4
    public int leastInterval4(char[] tasks, int n) {
        // greedy
        // time O(1)
        // space O(1)

        int[] counter = new int[26];
        int maxCount = 0;
        int freqOfMaxCount = 0;
        for (char task : tasks) {
            counter[task - 'A']++;
            if (maxCount == counter[task - 'A']) {
                freqOfMaxCount++;
            } else if (maxCount < counter[task - 'A']) {
                maxCount = counter[task - 'A'];
                freqOfMaxCount = 1;
            }
        }

        int partCount = maxCount - 1;
        int partLength = n - (freqOfMaxCount - 1);
        int emptySlots = partCount * partLength;
        int availableTasks = tasks.length - maxCount * freqOfMaxCount;
        int countOfIdles = Math.max(0, emptySlots - availableTasks);

        return tasks.length + countOfIdles;
    }

    public static void main(String[] args) {
        TaskScheduler_621 scheduler_621 = new TaskScheduler_621();
        // scheduler_621.leastInterval2(new char[]{'A','A','A','A','A','A','B','C','D','E','F','G'}, 2);
        scheduler_621.leastInterval3(new char[]{'A', 'A', 'A', 'B', 'B', 'B' }, 2);

    }
}
