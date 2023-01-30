package stack.hard;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FreqStack_895 {
    // create map to handle count of numbers {val, count}
    // create map to handle groups of numbs by count {count, [nums]}
    // int this case push and pop operations will be O(1)

    Map<Integer, Integer> counts;
    Map<Integer, List<Integer>> groups; // group numbers by count
    int maxCount = 0;

    public FreqStack_895() {
        counts = new HashMap<>();
        groups = new HashMap<>();
    }

    public void push(int val) {
        int newCount = counts.getOrDefault(val, 0) + 1;
        counts.put(val, newCount);
        if(maxCount < newCount) {
            maxCount = newCount;
            //groups.put(newCount, new ArrayList<>());
        }
        groups.computeIfAbsent(newCount, l -> new ArrayList<>()).add(val);
    }

    public int pop() {
        List<Integer> list = groups.get(maxCount);
        int res = list.remove(list.size() - 1);
        counts.put(res, counts.get(res) - 1);
        if(groups.get(maxCount).size() == 0)
            maxCount--;

        return res;
    }
}
