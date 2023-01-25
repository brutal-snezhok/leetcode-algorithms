package heap_and_priority_queue.medium;

import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Queue;

// https://leetcode.com/problems/process-tasks-using-servers/description/
public class ProcessTasksUsingServers_1882 {
    public int[] assignTasks(int[] servers, int[] tasks) {
        // https://leetcode.com/problems/process-tasks-using-servers/solutions/1239780/java-two-heaps-time-o-n-m-logn-detailed-explanation/comments/1131230
        // time O((n + m)*logn)
        // space O(n)

        // two min heap, one for available other for unavailable servers
        Comparator<Server> comp = (s1, s2) -> s1.weight == s2.weight ? s1.index - s2.index : s1.weight - s2.weight;
        Queue<Server> freeServers = new PriorityQueue<>(comp);
        Queue<Server> usedServers = new PriorityQueue<>((s1, s2) -> s1.avail - s2.avail);
        for (int i = 0; i < servers.length; i++)
            freeServers.add(new Server(i, servers[i]));

        int[] res = new int[tasks.length];
        for(int i = 0; i < tasks.length; i++) {
            // if there is any server completed at time i
            while(!usedServers.isEmpty() && usedServers.peek().avail <= i)
                freeServers.add(usedServers.poll());

            //if no free servers, find servers for next available time
            if(freeServers.isEmpty()) {
                int minAvail = usedServers.peek().avail;
                while(!usedServers.isEmpty() && usedServers.peek().avail == minAvail)
                    freeServers.add(usedServers.poll());
            }

            // used firstServer from free server
            Server server = freeServers.poll();
            res[i] = server.index;
            server.avail = Math.max(i, server.avail) + tasks[i];
            usedServers.add(server);
        }

        return res;
    }

    class Server {
        int index;
        int weight;
        int avail;

        public Server(int index, int weight) {
            this.index = index;
            this.weight = weight;
            this.avail = 0;
        }
    }
}
