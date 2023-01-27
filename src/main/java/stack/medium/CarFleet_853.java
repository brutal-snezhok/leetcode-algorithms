package stack.medium;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;

// https://leetcode.com/problems/car-fleet/description/
public class CarFleet_853 {
    public int carFleet(int target, int[] position, int[] speed) {
        // time O(nlogn)
        // space O(n)

        Triple[] triples = new Triple[position.length];
        for(int i = 0; i < position.length; i++)
            triples[i] = new Triple(position[i], speed[i], ((double)target - position[i]) / speed[i]);

        Arrays.sort(triples, (t1, t2) -> t2.pos - t1.pos);
        Deque<Double> stack = new ArrayDeque<>();
        for(Triple t : triples) {
            if(!stack.isEmpty() && stack.peek() >= t.time)
                continue;

            stack.addFirst(t.time);
        }

        return stack.size();
    }

    class Triple {
        int pos;
        int speed;
        double time;

        public Triple(int pos, int speed, double time) {
            this.pos = pos;
            this.speed = speed;
            this.time = time;
        }
    }
}
