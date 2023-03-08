package tree.medium;

import java.util.*;

// https://leetcode.com/problems/open-the-lock/
public class OpenTheLock_752 {
    public int openLock(String[] deadends, String target) {
        // time O(10_000)
        // space O(10_000)

        Set<String> dead = new HashSet<>(Arrays.asList(deadends));
        if (dead.contains("0000"))
            return -1;

        Queue<String> q = new LinkedList<>();
        Set<String> visited = new HashSet<>();
        visited.add("0000");
        q.add("0000");
        int res = 0;

        while (!q.isEmpty()) {
            int n = q.size();
            for (int i = 0; i < n; i++) {
                String currLock = q.poll();
                if (currLock.equals(target))
                    return res + 1;

                List<String> children = getChildren(currLock);
                for (String child : children) {
                    if (!visited.contains(child) && !dead.contains(child)) {
                        visited.add(child);
                        q.add(child);
                    }
                }
            }

            res++;
        }

        return -1;
    }

    private List<String> getChildren(String curr) {
        List<String> res = new ArrayList<>();

        for (int i = 0; i < 4; i++) {
            StringBuilder buildPlus = new StringBuilder(curr);
            StringBuilder buildMinus = new StringBuilder(curr);
            char ch = curr.charAt(i);
            int digit = Character.getNumericValue(ch);

            buildPlus.replace(i, i + 1, String.valueOf((digit + 1) % 10));
            buildMinus.replace(i, i + 1, String.valueOf((digit - 1 + 10) % 10));

            res.add(buildPlus.toString());
            res.add(buildMinus.toString());
        }

        return res;
    }

    public static void main(String[] args) {
        OpenTheLock_752 lock = new OpenTheLock_752();
        lock.openLock(new String[]{"0201", "0101", "0102", "1212", "2002"}, "0202");
    }
}
