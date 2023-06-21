package arraysHashing;

import java.util.HashSet;
import java.util.Set;

// https://leetcode.com/problems/distribute-candies/description/
public class DistributeCandies_575 {
    public int distributeCandies(int[] candyType) {
        // time O(n)
        // space O(n)

        // count how many types of candies are = numOfTypes
        // count how many candies Alice can eat canEat = n / 2
        // if numOfTypes <= canEat -> numOfTypes
        // if numOfTypes > canEat -> canEat

        int n = candyType.length;
        Set<Integer> candyTypes = new HashSet<>();
        for(int candy : candyType)
            candyTypes.add(candy);

        int numOfTypes = candyTypes.size();
        int canEat = n / 2;

        return Math.min(numOfTypes, canEat);
    }
}
