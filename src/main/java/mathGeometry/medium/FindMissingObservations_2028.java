package mathGeometry.medium;

// https://leetcode.com/problems/find-missing-observations/description/
public class FindMissingObservations_2028 {
    public int[] missingRolls(int[] rolls, int mean, int n) {
        // time O(m), m - length of rolls arr
        // space O(1)

        // count = n + rolls.length
        // rolls sum + missing n = mean * count ->
        // missing n = 6*mean - rolls sum
        // 6 * n >= missing n >= n ->
        // 6 * n >= 6*mean - rolls sum >= n

        int count = n + rolls.length;
        int rollsSum = 0;
        for(int roll : rolls)
            rollsSum += roll;

        int missN = count * mean - rollsSum;
        if(missN < n || missN > 6 * n)
            return new int[0];

        int[] res = new int[n];
        // go through res add every val by 1
        for(int i = 0; i < res.length; i++)
            res[i] += 1;

        missN -= n;
        for(int i = 0; i < res.length; i++) {
            if(missN >= 5) {
                res[i] += 5;
                missN -= 5;
            } else {
                res[i] += missN;
                break;
            }
        }

        return res;
    }
}
