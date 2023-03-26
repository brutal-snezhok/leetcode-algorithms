package mathGeometry.medium;

// https://leetcode.com/problems/robot-bounded-in-circle/description/
public class RobotBoundedInCircle_1041 {
    public static boolean isRobotBounded(String ins) {
        // time O(n)
        // space O(1)

        int[][] directions = { {0, 1}, {-1, 0}, {0, -1}, {1, 0} }; // up, left, down, right
        int i = 0; // index of curr direction
        int x = 0;
        int y = 0;

        for(char ch : ins.toCharArray()) {
            if(ch == 'L')
                i = (i + 1) % 4;
            else if(ch == 'R')
                i = (i + 3) % 4;
            else {
                int[] point = directions[i];
                x += point[0];
                y += point[1];
            }
        }

        // if we came to start point then it is loop
        // if any of two direction changed then after some time we will came to the same point

        return (x == 0 && y == 0) || i != 0;
    }

    public static void main(String[] args) {
        isRobotBounded("GLGLGGLGL");
    }
}
