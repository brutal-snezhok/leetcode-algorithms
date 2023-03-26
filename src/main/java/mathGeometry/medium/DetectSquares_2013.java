package mathGeometry.medium;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

// https://leetcode.com/problems/detect-squares/
public class DetectSquares_2013 {
    Map<Pair, Integer> map;

    public DetectSquares_2013() {
        map = new HashMap<>();
    }

    public void add(int[] point) {
        Pair p = new Pair(point[0], point[1]);
        map.put(p, map.getOrDefault(p, 0) + 1);
    }

    public int count(int[] point) {
        int res = 0;

        for (Map.Entry<Pair, Integer> entry : map.entrySet()) {
            Pair p = entry.getKey();
            int x = p.x;
            int y = p.y;
            int count1 = entry.getValue();

            // suppose that Pair p and point are two diagonal points,and find for them other two in map
            if (Math.abs(point[0] - x) != Math.abs(point[1] - y) || point[0] == x || point[1] == y)
                continue;

            int count2 = map.getOrDefault(new Pair(x, point[1]), 0);
            int count3 = map.getOrDefault(new Pair(point[0], y), 0);
            res += count1 * count2 * count3;
        }

        return res;
    }

    class Pair {
        int x;
        int y;

        public Pair(int x, int y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Pair pair = (Pair) o;
            return x == pair.x && y == pair.y;
        }

        @Override
        public int hashCode() {
            return Objects.hash(x, y);
        }
    }
}
