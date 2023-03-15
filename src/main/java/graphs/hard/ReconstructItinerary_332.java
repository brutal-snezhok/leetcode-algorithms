package graphs.hard;

import java.util.*;

// https://leetcode.com/problems/reconstruct-itinerary/
public class ReconstructItinerary_332 {
    public List<String> findItinerary(List<List<String>> tickets) {
        // time O(V + E)^2
        // space O(E)

        Map<String, PriorityQueue<String>> adjMap = new HashMap<>();
        for(List<String> ticket : tickets) {
            PriorityQueue<String> ticketQ = adjMap.getOrDefault(ticket.get(0), new PriorityQueue<>());
            ticketQ.add(ticket.get(1));
            adjMap.put(ticket.get(0), ticketQ);
        }

        LinkedList<String> res = new LinkedList<>();
        Deque<String> stack = new ArrayDeque<>();
        stack.addFirst("JFK");
        while(!stack.isEmpty()) {
            String from = stack.peekFirst();

            if(adjMap.containsKey(from) && !adjMap.get(from).isEmpty()) {
                stack.addFirst(adjMap.get(from).poll());
            } else
                res.addFirst(stack.removeFirst());
        }

        return res;
    }


}
