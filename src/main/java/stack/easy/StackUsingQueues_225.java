package stack.easy;

import java.util.LinkedList;
import java.util.Queue;

// https://leetcode.com/problems/implement-stack-using-queues/description/
public class StackUsingQueues_225 {
    // first solution: two stacks, push: O(1), pop: O(n)
    private Queue<Integer> q1;
    private Queue<Integer> q2;

    public StackUsingQueues_225() {
        q1 = new LinkedList<>();
        q2 = new LinkedList<>();
    }

    public void push(int x) {
        q1.add(x);
    }

    public int pop() {
        while(q1.size() > 1)
            q2.add(q1.remove());

        int res = q1.remove();
        while(!q2.isEmpty())
            q1.add(q2.remove());

        return res;
    }

    public int top() {
        while(q1.size() > 1)
            q2.add(q1.remove());

        int res = q1.peek();
        q2.add(q1.remove());
        while(!q2.isEmpty())
            q1.add(q2.remove());

        return res;
    }

    public boolean empty() {
        return q1.isEmpty();
    }



    // second solution: one stack, push: O(1), pop: O(n)
//    private Queue<Integer> q;
//
//    public StackUsingQueues_225() {
//        q = new LinkedList<>();
//    }
//
//    public void push(int x) {
//        q.add(x);
//    }
//
//    public int pop() {
//        int count = q.size();
//        while(count > 1) {
//            q.add(q.remove());
//            count--;
//        }
//
//        int res = q.remove();
//        count = q.size();
//        while(count > 0) {
//            q.add(q.remove());
//            count--;
//        }
//
//        return res;
//    }
//
//    public int top() {
//        int count = q.size();
//        while(count > 1) {
//            q.add(q.remove());
//            count--;
//        }
//
//        int res = q.peek();
//        q.add(q.remove());
//        count = q.size();
//        while(count > 0) {
//            q.add(q.remove());
//            count--;
//        }
//
//        return res;
//    }
//
//    public boolean empty() {
//        return q.isEmpty();
//    }
}
