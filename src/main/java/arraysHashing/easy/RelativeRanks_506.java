package arraysHashing.easy;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

// https://leetcode.com/problems/relative-ranks/
public class RelativeRanks_506 {
    public static String[] findRelativeRanks(int[] score) {
        // time O(nlogn)
        // space O(n)

        // sort array, and put to map score and place
        // go through origin arr and create res

        int n = score.length;
        int[] copy = Arrays.copyOf(score, n);
        Arrays.sort(copy);
        reverse(copy, 0, n - 1);
        Map<Integer, String> scoreToPlace = new HashMap<>(); // {score, place}
        String[] res = new String[n];
        int place = 1;
        for(int i = 0; i < n; i++) {
            if(place == 1)
                scoreToPlace.put(copy[i], "Gold Medal");
            else if(place == 2)
                scoreToPlace.put(copy[i], "Silver Medal");
            else if(place == 3)
                scoreToPlace.put(copy[i], "Bronze Medal");
            else
                scoreToPlace.put(copy[i], String.valueOf(place));

            place++;
        }

        int i = 0;
        for(int s : score)
            res[i++] = scoreToPlace.get(s);

        return res;
    }

    private static void reverse(int[] arr, int l, int r) {
        while(l < r) {
            int temp = arr[l];
            arr[l] = arr[r];
            arr[r] = temp;
            l++;
            r--;
        }
    }

    public static void main(String[] args) {
        findRelativeRanks(new int[]{10,3,8,9,4});
    }
}
