package heap_and_priority_queue.medium;

import java.util.PriorityQueue;
import java.util.Queue;

// https://leetcode.com/problems/seat-reservation-manager/description/
public class SeatManager_1845 {
    Queue<Integer> pq; // min heap, unresoved seats

    // time O(n)
    // space O(n)
    public SeatManager_1845(int n) {
        pq = new PriorityQueue<>();
        for(int i = 1; i <= n; i++)
            pq.add(i);
    }

    // time O(logn)
    public int reserve() {
        return pq.poll();
    }

    // time O(logn)
    public void unreserve(int seatNumber) {
        pq.add(seatNumber);
    }
}

/**
 * Your SeatManager object will be instantiated and called as such:
 * SeatManager obj = new SeatManager(n);
 * int param_1 = obj.reserve();
 * obj.unreserve(seatNumber);
**/
