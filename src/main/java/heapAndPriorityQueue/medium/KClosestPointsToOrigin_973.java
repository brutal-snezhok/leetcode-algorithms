package heapAndPriorityQueue.medium;

import java.util.PriorityQueue;
import java.util.Queue;

// https://leetcode.com/problems/k-closest-points-to-origin/description/
public class KClosestPointsToOrigin_973 {
    public int[][] kClosest(int[][] points, int k) {
        // time O(nlogk)
        // space O(k), k - size of maxHeap

        Queue<int[]> maxHeap = new PriorityQueue<>((p1, p2) -> Integer.compare(distance(p2), distance(p1)));
        for(int[] point : points) {
            maxHeap.add(point);

            if(maxHeap.size() > k)
                maxHeap.poll();
        }

        int[][] res = new int[k][2];
        int i = 0;
        while(maxHeap.size() != 0)
            res[i++] = maxHeap.poll();

        return res;
    }

    private int distance(int[] p) {
        return p[0]*p[0] + p[1]*p[1];
    }
}
