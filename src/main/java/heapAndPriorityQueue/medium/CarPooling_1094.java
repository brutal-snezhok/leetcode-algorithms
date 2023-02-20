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

    public boolean carPooling3(int[][] trips, int capacity) {
        // Solution3: use sorting + arr
        // time O(nlogn)
        // space O(n)

        // can have arr[1001] numOfPassengers that take off from car on particular km
        // sort trips by from i
        // go through trips and check if some pass take off from car on particular km
        // then put into car numPass on particular km
        // check if numPass in car <= capacity, if no return false;

        int[] takeOffPass = new int[1001];
        Arrays.sort(trips, (t1, t2) -> t1[1] - t2[1]);
        int currPass = 0;
        int j = 0; // counter for trips
        // [3,2,8],[4,4,6],[10,8,9]
        for(int i = 0; i < 1001; i++) {
            // go out of car
            if(takeOffPass[i] != 0)
                currPass -= takeOffPass[i];

            // go into the car
            while(j < trips.length && i == trips[j][1]) {  // i = 0,4  j = 0,1
                int numPas = trips[j][0]; // 3, 4
                int from = trips[j][1]; // 2, 4
                int to = trips[j][2]; // 8, 6

                if(from != to)
                    takeOffPass[to] += numPas;// takeOffPass[8] = 3 takeOffPass[6] = 4
                else
                    continue;

                currPass += numPas; // 3, 7

                if(currPass > capacity)
                    return false;

                j++;   // 1, 2
            }
        }

        return true;
    }

    public boolean carPooling4(int[][] trips, int capacity) {
        // Solution4: just using arr
        // time O(n), n - number of trips
        // space O(m), m - number os stops

        // create arr stops[1001] where save how many people go in on each stop
        // After processing all trips, a positive value for the specific location tells
        // that we are getting more passengers;    negative - more empty seats.

        int[] stops = new int[1001];
        for(int[] trip : trips) {
            int numPas = trip[0];
            int from = trip[1];
            int to = trip[2];

            stops[from] += numPas;
            stops[to] -= numPas;
        }

        for(int i = 0; i < 1001; i++) {
            capacity -= stops[i];
            if(capacity < 0)
                return false;
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
