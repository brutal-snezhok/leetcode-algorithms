package arraysHashing;

import java.util.HashMap;
import java.util.Map;

// https://leetcode.com/problems/robot-return-to-origin/description/
public class RobotReturnToOrigin_657 {
    public boolean judgeCircle(String moves) {
        // time O(n)
        // space O(1), since map contains only 4 pairs

        // (0, 0) - starting point
        // up - (0, 1), down - (0, -1), left - (-1, 0), right - (1, 0)
        // "UD" -> (0, 0) + (0, 1) + (0, -1) = (0, 0)
        // "LL" -> (0, 0) + (-1, 0) + (-1, 0) = (-2, 0)

        Map<Character, int[]> map = new HashMap<>();
        map.put('U', new int[]{0, 1});
        map.put('D', new int[]{0, -1});
        map.put('L', new int[]{-1, 0});
        map.put('R', new int[]{1, 0});

        int[] curr = new int[]{0, 0};
        for(char ch : moves.toCharArray()) {
            int[] pair = map.get(ch);
            curr[0] += pair[0];
            curr[1] += pair[1];
        }

        return curr[0] == 0 && curr[1] == 0;
    }
}
