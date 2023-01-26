package heapAndPriorityQueue.medium;

import java.util.*;

//https://leetcode.com/problems/car-pooling/
public class CarPooling_1094 {

    public boolean carPooling1(int[][] trips, int capacity) {
        // Solution1: we can just figure out how many people get it and out in each location.
        // time O(nlogn)
        // space O(n)

        Arrays.sort(trips, (arr1, arr2) -> arr1[1] - arr2[1]);
        Map<Integer, Integer> map = new HashMap<>(); // {point of trip, changed value of passengers}
        for(int[] trip : trips) {
            map.put(trip[1], map.getOrDefault(trip[1], 0) + trip[0]); // for from point
            map.put(trip[2], map.getOrDefault(trip[2], 0) - trip[0]); // for to point
        }

        int n = trips.length;
        int currNumOfPassengers = 0;
        for(int i = 0; i < trips[n - 1][2]; i++) {
            if(map.containsKey(i))
                currNumOfPassengers += map.get(i);

            if(currNumOfPassengers > capacity)
                return false;
        }

        return true;
    }

    public boolean carPooling2(int[][] trips, int capacity) {
        // Solution2: use priorityQueue
        // time O(nlogn)
        // space O(n)

        Arrays.sort(trips, (arr1, arr2) -> arr1[1] - arr2[1]);
        Queue<Pair> pq = new PriorityQueue<>((p1, p2) -> p1.end - p2.end);
        int currNumberOfPas = 0;
        for(int[] trip : trips) {
            int numPas = trip[0];
            int start = trip[1];
            int end = trip[2];

            while(!pq.isEmpty() && pq.peek().end <= start)
                currNumberOfPas -= pq.poll().numPas;

            currNumberOfPas += numPas;
            if(currNumberOfPas > capacity)
                return false;

            pq.add(new Pair(end, numPas));
        }

        return true;
    }

    class Pair {
        int end;
        int numPas;

        public Pair(int end, int numPas) {
            this.end = end;
            this.numPas = numPas;
        }
    }
}
