package binarySearch.medium;

// https://leetcode.com/problems/peak-index-in-a-mountain-array/description/
public class PeakIndexInMountainArray_852 {
    public int peakIndexInMountainArray1(int[] arr) {
        // time O(logn)
        // space O(1)

        int l = 0;
        int r = arr.length - 1;
        while(l < r) {
            int mid = l + (r - l) / 2;

            if(arr[mid] > arr[mid + 1])
                r = mid;
            else
                l = mid + 1;
        }

        return l;
    }

    public int peakIndexInMountainArray2(int[] arr) {
        // time O(logn)
        // space O(1)

        int l = 0;
        int r = arr.length - 1;
        while(l < r) {
            int mid = l + (r - l) / 2;

            if(arr[mid] < arr[mid + 1])
                l = mid + 1;
            else
                r = mid;
        }

        return l;
    }
}
