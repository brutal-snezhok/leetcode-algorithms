package pramp;

public class ShiftedArraySearch {
    /*
    * A sorted array of distinct integers shiftArr is shifted to the left by an unknown offset
    * and you don’t have a pre-shifted copy of it.
    * For instance, the sequence 1, 2, 3, 4, 5 becomes 3, 4, 5, 1, 2, after shifting it twice to the left.

      Given shiftArr and an integer num, implement a function shiftedArrSearch that finds and returns
      the index of num in shiftArr. If num isn’t in shiftArr, return -1.
      Assume that the offset can be any value between 0 and arr.length - 1.

      Explain your solution and analyze its time and space complexities.
      *
      * Example:
        input:  shiftArr = [9, 12, 17, 2, 4, 5], num = 2 # shiftArr is the
                                                 # outcome of shifting
                                                 # [2, 4, 5, 9, 12, 17]
                                                 # three times to the left

        output: 3 # since it’s the index of 2 in arr
    * */

    public int search(int[] arr, int target) {
        // time O(logn)
        // space O(1)

        /*
         1. find pivot - the smallest value in arr
         2. do binary search from 0 to pivot - on first part of arr
         3. do binary search from pivot + 1 to n - 1 - on second part of arr
        */

        int n = arr.length;
        int pivot = findPivot(arr);
        int res1 = binarySearch(arr, 0, pivot - 1, target);
        if (res1 != -1)
            return res1;

        return binarySearch(arr, pivot, n - 1, target);
    }

    private int findPivot(int[] arr) {
        int l = 0;
        int r = arr.length - 1;

        while (l < r) {
            int mid = l + (r - l) / 2;

            if (arr[mid] <= arr[r])
                r = mid;
            else
                l = mid + 1;
        }

        return l;
    }

    private int binarySearch(int[] arr, int l, int r, int target) {
        while (l < r) {
            int mid = l + (r - l) / 2;

            if (arr[mid] >= target)
                r = mid;
            else
                l = mid + 1;
        }

        return arr[l] == target ? l : -1;
    }

    public static void main(String[] args) {

    }
}
