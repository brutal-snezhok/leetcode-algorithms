package tree.medium;

import java.util.*;

// https://leetcode.com/problems/operations-on-tree/
public class OperationsOnTree_1993 {
    Map<Integer, Integer> lockedNums; // {num, userId}
    int[] parent;
    Map<Integer, List<Integer>> descendants; // {num, [descendants]}

    public OperationsOnTree_1993(int[] input) {
        lockedNums = new HashMap<>();
        parent = new int[input.length];
        descendants = new HashMap<>();

        for (int i = 0; i < input.length; i++) {
            parent[i] = input[i];

            if (i != 0) {
                int par = input[i];
                if (!descendants.containsKey(par))
                    descendants.put(par, new ArrayList<>());

                List<Integer> list = descendants.get(par);
                list.add(i);
            }
        }
    }

    public boolean lock(int num, int user) {
        if (lockedNums.containsKey(num))
            return false;

        lockedNums.put(num, user);
        return true;
    }

    public boolean unlock(int num, int user) {
        if (!lockedNums.containsKey(num) || lockedNums.get(num) != user)
            return false;

        lockedNums.remove(num);
        return true;
    }

    public boolean upgrade(int num, int user) {
        // check if doesn't have any locked ancestors
        if (isHasLockedAncestor(num))
            return false;

        // check if has at least one locked descendant
        int lockedCount = 0;
        Deque<Integer> q = new ArrayDeque<>();
        q.add(num);
        while (!q.isEmpty()) {
            int val = q.removeFirst();
            if (lockedNums.containsKey(val)) {
                lockedNums.remove(val);
                lockedCount++;
            }
            List<Integer> cc = descendants.get(val);
            if (cc != null) {
                for (int c : cc) q.addLast(c);
            }
        }

        if (lockedCount > 0)
            lockedNums.put(num, user);

        return lockedCount > 0;
    }

    private boolean isHasLockedAncestor(int num) {
        int ancestor = num;
        while (ancestor != -1) {
            if (lockedNums.containsKey(ancestor))
                return true;

            ancestor = parent[ancestor];
        }

        return false;
    }

    public static void main(String[] args) {
        // ["LockingTree","upgrade","upgrade","unlock","lock","upgrade"]
        // [[[-1,0,3,1,0]],[4,5],[3,8],[0,7],[2,7],[4,6]]
        OperationsOnTree_1993 op = new OperationsOnTree_1993(new int[]{-1, 0, 3, 1, 0});
        op.upgrade(4, 5);
        op.upgrade(3, 8);
        op.unlock(0, 7);
        op.lock(2, 7);
        op.upgrade(4, 6);
    }
}
