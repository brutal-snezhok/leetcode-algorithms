package arraysHashing.matrix;

// https://leetcode.com/problems/find-winner-on-a-tic-tac-toe-game/description/
public class FindWinnerOnTicTacToeGame_1275 {
    public static String tictactoe(int[][] moves) {
        // time O(moves.length)
        // space O(r + c)

        int n = 3;
        int[] ar = new int[n];
        int[] ac = new int[n];
        int adiag1 = 0; // main
        int adiag2 = 0; // scew

        int[] br = new int[n];
        int[] bc = new int[n];
        int bdiag1 = 0; // main
        int bdiag2 = 0; // scew

        int i = 0;
        for (int[] move : moves) {
            int r = move[0];
            int c = move[1];

            if (i % 2 == 0) {
                // for a player
                if (++ar[r] == n || ++ac[c] == n || r == c && ++adiag1 == n || r + c == n - 1 && ++adiag2 == n)
                    return "A";
            } else {
                // for b player
                if (++br[r] == n || ++bc[c] == n || r == c && ++bdiag1 == n || r + c == n - 1 && ++bdiag2 == n)
                    return "B";
            }
            i++;
        }

        return moves.length == 9 ? "Draw" : "Pending";
    }

    public static void main(String[] args) {
        System.out.println(tictactoe(new int[][]{
                // [0,0],[1,1],[0,1],[0,2],[1,0],[2,0]
                {0, 0},
                {1, 1},
                {0, 1},
                {0, 2},
                {1, 0},
                {2, 0}
        }));
    }
}
