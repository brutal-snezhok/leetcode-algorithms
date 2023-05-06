package slidingWindow.medium;

import java.util.HashMap;
import java.util.Map;

// https://leetcode.com/problems/fruit-into-baskets/
public class FruitIntoBaskets_904 {
    // solution1
    public int totalFruit(int[] fruits) {
        // time O(n)
        // space O(1)

        // [0,1,2,2]
        // {fruit, count}
        // {0, 1}, {1, 1}
        // if next == fruit from first backet or from second then update it count
        // else 1. res = max(res, countFirstBacket + countSecondBacket)
        // 2. remove pair with fruits[l] and add new pair fruits[r] with count = 1

        int l = 0;
        int res = 0;
        Map<Integer, Integer> map = new HashMap<>(); // {fruit, count}

        for(int r = 0; r < fruits.length; r++) {
            if(map.containsKey(fruits[r]) || map.size() < 2)
                map.put(fruits[r], map.getOrDefault(fruits[r], 0) + 1);
            else {
                int currCount = map.values().stream().mapToInt(i -> i.intValue()).sum();
                res = Math.max(res, currCount);

                int fruitToDelete = fruits[l];
                while(l <= r && map.containsKey(fruitToDelete)) {
                    int count = map.get(fruitToDelete);
                    count -= 1;
                    if(count > 0)
                        map.put(fruitToDelete, count);
                    else
                        map.remove(fruitToDelete);
                    l++;
                }
            }
        }

        int currCount = map.values().stream().mapToInt(i -> i).sum();
        res = Math.max(res, currCount);

        return res;
    }

    public static void main(String[] args) {
        FruitIntoBaskets_904 fruit = new FruitIntoBaskets_904();
        fruit.totalFruit(new int[]{1,0,0,0,1,0,4,0,4});
    }
}
