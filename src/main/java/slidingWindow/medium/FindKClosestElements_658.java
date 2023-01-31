package slidingWindow.medium;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

// https://leetcode.com/problems/find-k-closest-elements/description/
public class FindKClosestElements_658 {
    public List<Integer> findClosestElements(int[] arr, int k, int x) {
        // time O(log(n - k)) for binary search
        // space O(k) to create res value

        int l = 0;
        int r = arr.length - k;
        while(l < r) {
            int mid = (l + r) / 2;
            if(x - arr[mid] > arr[mid + k] - x)
                l = mid + 1;
            else
                r = mid;
        }

        return Arrays.stream(arr, l, l + k).boxed().collect(Collectors.toList());
    }

    public List<Integer> findClosestElements2(int[] arr, int k, int x) {
        // solution2 (two pointers)
        // time O(n)
        // space O(k) to create res value

        int l = 0;
        int r = arr.length - 1;
        while(r - l >= k) {
            if(Math.abs(arr[l] - x ) > Math.abs(arr[r] - x))
                l++;
            else
                r--;
        }

        return Arrays.stream(arr, l, l + k).boxed().collect(Collectors.toList());
    }
}
